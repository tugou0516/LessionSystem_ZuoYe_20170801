package org.forten.zuoye.bo;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.forten.utils.common.StringUtil;
import org.forten.utils.system.BeanPropertyUtil;
import org.forten.utils.system.PageInfo;
import org.forten.zuoye.dao.HibernateDao;
import org.forten.zuoye.dto.EmailDto;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.common.RoWithPage;
import org.forten.zuoye.dto.course.*;
import org.forten.zuoye.dto.student.CourseStudentQo;
import org.forten.zuoye.dto.student.Student4ExcelShow;
import org.forten.zuoye.dto.student.Student4ShowRo;
import org.forten.zuoye.model.Course;
import org.forten.zuoye.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by student1 on 2017/8/15.
 */
@Service("coManageBo")
public class CoManageBo {
    @Resource
    private HibernateDao hDao;

    @Transactional
    public RoWithPage<Course4CoManageRo> selectBy(CourseQo qo){
        System.out.println(qo);
        String countHql = "SELECT count(id) FROM Course WHERE 1=1 ";
        String hql = "SELECT new org.forten.zuoye.dto.course.Course4CoManageRo(id, name, classRoom, teacher, courseStartTime, courseEndTime, classStartTime, classEndTime, score, classCapacity) FROM Course WHERE 1=1 ";
        Map<String,Object> map = new HashMap<>();

        if(StringUtil.hasText(qo.getName())){
            countHql = countHql + "AND name LIKE :name ";
            hql = hql + "AND name LIKE :name ";
            map.put("name","%"+qo.getName()+"%");
        }
        if(StringUtil.hasText(qo.getTeacher())){
            countHql = countHql + "AND teacher LIKE :t ";
            hql = hql + "AND teacher LIKE :t ";
            map.put("t","%"+qo.getTeacher()+"%");
        }

        if(qo.getClassStartTime()!=null && qo.getClassEndTime()!=null){
            countHql = countHql + "AND classStartTime BETWEEN :start AND :end ";
            hql = hql + "AND classStartTime BETWEEN :start AND :end ";
            if(qo.getClassStartTime().before(qo.getClassEndTime())){
                map.put("start",qo.getClassStartTime());
                map.put("end",qo.getClassEndTime());
            }else {
                map.put("start",qo.getClassEndTime());
                map.put("end",qo.getClassStartTime());
            }
        }
        if(!qo.isFinished()){
            Date now = new Date();
            countHql = countHql + "AND classEndTime>:et ";
            hql = hql + "AND classEndTime>:et ";
            map.put("et",now);
        }
        hql = hql + "ORDER BY classStartTime DESC";
        long count = hDao.findOneBy(countHql,map);
        if(count == 0){
            return RoWithPage.EMPTY_RO;
        }

        PageInfo page = PageInfo.getInstance(qo.getPageNo(),qo.getPageSize(),count);
        List<Course4CoManageRo> list = hDao.findBy(hql,map,(int)page.getFirstResultNum(),page.getPageSize());

        return new RoWithPage<>(list,page);
    }

    //添加课程
    @Transactional
    public Message doSaveCourse(Dto4SaveCourse cou){
        Course course = new Course();
        BeanPropertyUtil.copy(course,cou);
        try {
            hDao.save(course);
            return new Message("课程添加成功");
        }
        catch (Exception e){
            return new Message("课程添加失败");
        }
    }

    //删除课程
    @Transactional
    public Message doDeleteCourse(List<Integer> list){
        try{
            String hql = "DELETE FROM org.forten.zuoye.model.Course WHERE id IN (:arr) ";
            Map<String,Object> map = new HashMap<>();
            map.put("arr",list);
            int n = hDao.executeUpdate(hql,map);
            return new Message("成功删除"+n+"个课程");
        }
        catch (Exception e){
            return new Message("课程删除失败");
        }
    }

    //更新课程信息
    @Transactional
    public Message doUpdateCourse(int id, Dto4UpdateCourse cou) {
        Course course = hDao.getById(id, Course.class);
        BeanPropertyUtil.copy(course, cou);
        try {
            hDao.update(course);
            return new Message("课程更新成功");
        } catch (Exception e) {
            return new Message("课程更新失败");
        }
    }

    //分页查询课程学生 可按登录名和姓名模糊查询
    @Transactional
    public RoWithPage<Student4ExcelShow> doListStudentByCourse(CourseStudentQo qo, int id){
        String countHql="SELECT count(s.id) " +
                "FROM Student s RIGHT JOIN LinedCS l ON (s.id=l.studentId) WHERE l.courseId=:id ";
        String hql="SELECT new org.forten.zuoye.dto.student.Student4ExcelShow(s.id, l.courseId, s.loginName,  s.name, s.gender, s.position, s.birthday,s.tel, s.email, l.attendanceStatus) " +
                "FROM Student s RIGHT JOIN LinedCS l ON (s.id=l.studentId) WHERE l.courseId=:id ";
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);

        if(StringUtil.hasText(qo.getLoginName())){
            countHql = countHql + "AND s.loginName LIKE :loginName ";
            hql = hql + "AND s.loginName LIKE :loginName ";
            param.put("loginName","%"+qo.getLoginName()+"%");
        }

        if(StringUtil.hasText(qo.getName())){
            countHql = countHql + "AND name LIKE :name ";
            hql = hql + "AND name LIKE :name ";
            param.put("name","%"+qo.getName()+"%");
        }

        //1已选，2排队，3退课，4已修

        if(qo.getFlag()==1){
            countHql = countHql + "AND l.chooseStatus=1 ";
            hql = hql + "AND l.chooseStatus=1 ";
        }
        if(qo.getFlag()==2){
            countHql = countHql + "AND l.chooseStatus=2 ";
            hql = hql + "AND l.chooseStatus=2 ";
        }
        if(qo.getFlag()==3){
            countHql = countHql + "AND l.chooseStatus=3 ";
            hql = hql + "AND l.chooseStatus=3 ";
        }
        if(qo.getFlag()==4){
            countHql = countHql + "AND l.chooseStatus=4 ";
            hql = hql + "AND l.chooseStatus=4 ";
        }

        hql = hql + "ORDER BY s.loginName ";

        long count=hDao.findOneBy(countHql,param);
        if (count==0)
            return RoWithPage.EMPTY_RO;
        else{
            PageInfo page =PageInfo.getInstance(qo.getPageNo(),qo.getPageSize(),count);
            List<Student4ExcelShow> studentList =hDao.findBy(hql,param,(int)page.getFirstResultNum(),page.getPageSize());
            return new RoWithPage<>(studentList,page);
        }
    }

    @Transactional(readOnly = true)
    public Workbook exportData(int id) {
        String hql="SELECT new org.forten.zuoye.dto.student.Student4ShowRo(s.id, s.loginName, s.name, s.gender, s.position, s.birthday, s.tel, s.email) " +
                "FROM Student s RIGHT JOIN LinedCS l ON (s.id=l.studentId) WHERE l.courseId=:id ";
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);

        List<Student4ShowRo> dtoList = hDao.findBy(hql);

        // 2003格式的Excel工作簿模型
        Workbook wb = new HSSFWorkbook();
        // 2007格式的Excel工作簿模型
        // Workbook wb = new XSSFWorkbook();

        // 在Workbook中创建一个sheet
        Sheet sheet = wb.createSheet("课程签到信息");

        // 在sheet中创建表头行
        Row header = sheet.createRow(0);

        // 创建表头行中的单元格及表头标题
        Cell c1 = header.createCell(0);
        c1.setCellValue("序号");
        header.createCell(1).setCellValue("学号");
        header.createCell(2).setCellValue("姓名");
        header.createCell(3).setCellValue("性别");
        header.createCell(4).setCellValue("电话");
        header.createCell(5).setCellValue("出勤");


        // 生成数据行
        for (Student4ShowRo dto : dtoList) {
            Row row = sheet.createRow(sheet.getLastRowNum()+1);
            row.createCell(0).setCellValue(sheet.getLastRowNum());
            row.createCell(1).setCellValue(dto.getLoginName());
            row.createCell(2).setCellValue(dto.getName());
            row.createCell(3).setCellValue(dto.getGender());
            row.createCell(4).setCellValue(dto.getTel());
        }

        return wb;
    }
    @Transactional
    public Message doUpdate(CourseChangeDto dto){
        String hql= "UPDATE Course SET name=:name,teacher=:teacher,classRoom=:classRoom,courseStartTime=:courseStartTime,courseEndTime=:courseEndTime,classStartTime=:classStartTime,classEndTime=:classEndTime,score=:score WHERE id=:id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", dto.getId());
        params.put("name", dto.getName());
        params.put("teacher", dto.getTeacher());
        params.put("classRoom", dto.getClassRoom());
        params.put("courseStartTime", dto.getCourseStartTime());
        params.put("courseEndTime", dto.getCourseEndTime());
        params.put("classStateTime", dto.getClassStartTime());
        params.put("classEndTime()", dto.getClassEndTime());
        params.put("score", dto.getScore());
        try {
            hDao.executeUpdate(hql, params);
            return new Message("数据更新成功");
        }catch(Exception e){
            e.printStackTrace();
            return new Message("数据更新失败");
        }
    }
    @Transactional
    public Message sendEmail(EmailDto dto) {
        String hql = "SELECT count(studentId) FROM LinedCS WHERE courseId=:id ";
        Map<String, Object> params = new HashMap<>();
        int id = dto.getId();
        params.put("id", id);
        long count = hDao.findOneBy(hql, params);
        if (count == 0) {
            return new Message("暂无学生选择此课");
        } else {
            String hql03 = "SELECT (s.email) " +
                    "FROM Student s RIGHT JOIN LinedCS l ON (s.id=l.studentId) WHERE l.courseId=:id ";
            Map<String, Object> params02 = new HashMap<>();
            params02.put("id", id);
            List<String> list = hDao.findBy(hql03, params02);
            StringBuilder sd = new StringBuilder();
            for (String email : list) {
                sd.append(email);
                sd.append(",");
            }
            sd.replace(sd.length() - 1, sd.length(), "");
            new Thread(() -> {
                try {
                    HtmlEmail email = new HtmlEmail();
                    email.setHostName("smtp.126.com");
                    email.setCharset("UTF-8");
                    email.setSmtpPort(465);
                    email.setAuthenticator(new DefaultAuthenticator("thcic_test", "a123456"));
                    email.setSSLOnConnect(true);
                    email.addTo(sd.toString());
                    email.setFrom("thcic_test@126.com", "System");
                    email.setSubject(dto.getTitle());
                    email.setHtmlMsg(dto.getTest());
                    email.send();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            return new Message("课程更改的消息已经发送给已经选择此课程的学员Email中");
        }
    }
}
