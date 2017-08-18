package org.forten.zuoye.bo;

import org.forten.utils.common.NumberUtil;
import org.forten.utils.system.PageInfo;
import org.forten.utils.system.PropertiesFileReader;
import org.forten.zuoye.dao.HibernateDao;
import org.forten.zuoye.dao.MyBatisDao;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.common.RoWithPage;
import org.forten.zuoye.dto.course.Course4ShowDto;
import org.forten.zuoye.dto.course.Course4StuShowRo;
import org.forten.zuoye.dto.course.CourseIdDto;
import org.forten.zuoye.dto.course.CourseQo4Stu;
import org.forten.zuoye.dto.student.Student4ShowRo;
import org.forten.zuoye.mapper.StudentMapper;
import org.forten.zuoye.model.Course;
import org.forten.zuoye.model.LinedCS;
import org.forten.zuoye.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.forten.utils.system.BeanPropertyUtil;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2017/8/7.
 */
@Service("studentBo")
public class StudentBo {
    @Resource
    private MyBatisDao<StudentMapper> mDao;
    @Resource
    private HibernateDao hDao;


    @Transactional(readOnly = true)
    public Student4ShowRo doSelectByIdH(int id){
        Student stu = hDao.getById(id,Student.class);
        Student4ShowRo student =new Student4ShowRo();
        BeanPropertyUtil.copy(student, stu);
        return student;
    }

    @Transactional(readOnly = true)
    public List<Course4StuShowRo> listCompletedCourse(int id){
        String hql="SELECT new org.forten.zuoye.dto.course.Course4StuShowRo(c.id,c.name, c.teacher, c.classRoom, c.courseEndTime, c.classStartTime, c.classEndTime, c.score, l.chooseStatus, l.attendanceStatus, l.chooseCourseTime, l.updateTime) " +
                "FROM Course c RIGHT JOIN LinedCS l ON (c.id=l.courseId) RIGHT JOIN Student s ON (l.studentId=s.id ) WHERE l.chooseStatus=4 AND s.id=:id ";
        Map<String ,Object> params= new HashMap<>(1);
        params.put("id",id);
        return hDao.findBy(hql,params);
    }

    @Transactional(readOnly = true)
    public List<Course4StuShowRo> listOtherCourse(int id){
        String hql="SELECT new org.forten.zuoye.dto.course.Course4StuShowRo(c.id,c.name, c.teacher, c.classRoom, c.courseEndTime, c.classStartTime, c.classEndTime, c.score, l.chooseStatus, l.attendanceStatus, l.chooseCourseTime, l.updateTime) " +
                "FROM Course c RIGHT JOIN LinedCS l ON (c.id=l.courseId) RIGHT JOIN Student s ON (l.studentId=s.id ) WHERE l.chooseStatus<3 AND s.id=:id ";
        Map<String ,Object> params= new HashMap<>(1);
        params.put("id",id);
        return hDao.findBy(hql,params);
    }

    @Transactional
    // 回传1代表选课成功，2代表课容量已满，3代表学分已超过上限，0代表后台操作失败
    public int chooseCourse(int stuId, int coId){

        Date nowDate = new Date();
        Date pastDate = getPastDate();

        String hql01 = "SELECT courseId FROM org.forten.zuoye.model.LinedCS WHERE studentId=:stuId AND chooseStatus!=3 AND chooseCourseTime BETWEEN :past AND :now ";
        Map<String,Object> map01 = new HashMap<>();
        map01.put("stuId",stuId);
        map01.put("past",pastDate);
        map01.put("now",nowDate);
        List<Integer> list = hDao.findBy(hql01,map01);

        int sumScore = 0;
        //hql01和hql02不能合并为一句查询语句，因为list可能为空。如果list为空，语句就会报错
        if(list.size()>0) {
            String hql02 = "SELECT sum(score) FROM org.forten.zuoye.model.Course WHERE id IN (:arr) ";
            Map<String, Object> map02 = new HashMap<>();
            map02.put("arr", list);
            List<Integer> resultList01 = hDao.findBy(hql02, map02);
            sumScore = Integer.parseInt(String.valueOf(resultList01.get(0)));
        }

        String hql03 = "SELECT count(id) FROM org.forten.zuoye.model.LinedCS WHERE courseId=:coId AND chooseStatus=1";
        Map<String,Object> map03 = new HashMap<>();
        map03.put("coId",coId);
        List<?> resultList02 = hDao.findBy(hql03,map03);
        int chosenNum = Integer.parseInt(String.valueOf(resultList02.get(0)));

        Course course = hDao.getById(coId,Course.class);

        if(chosenNum < course.getClassCapacity()){
            int limitScore = NumberUtil.parseNumber(PropertiesFileReader.getValue("properties/settings","SCORE_LIMIT"),Integer.class);
            if((course.getScore() + sumScore)>limitScore){
                return 3;
            }else {
                LinedCS cs = new LinedCS(stuId, new Date(), new Date(), coId, 1, 0);
                return saveCS(cs);
            }
        }else {
            return 2;
        }
    }

    //退课后排队学生自动选中
    @Transactional
        private int updateCourse(LinedCS cs, int coId){

        int stuId =cs.getStudentId();
        Date nowDate = new Date();
        Date pastDate = getPastDate();

        String hql01 = "SELECT courseId FROM org.forten.zuoye.model.LinedCS WHERE studentId=:stuId AND chooseStatus!=3 AND chooseCourseTime BETWEEN :past AND :now ";
        Map<String,Object> map01 = new HashMap<>();
        map01.put("stuId",stuId);
        map01.put("past",pastDate);
        map01.put("now",nowDate);
        List<Integer> list = hDao.findBy(hql01,map01);

        int sumScore = 0;
        //hql01和hql02不能合并为一句查询语句，因为list可能为空。如果list为空，语句就会报错
        if(list.size()>0) {
            String hql02 = "SELECT sum(score) FROM org.forten.zuoye.model.Course WHERE id IN (:arr) ";
            Map<String, Object> map02 = new HashMap<>();
            map02.put("arr", list);
            List<Integer> resultList01 = hDao.findBy(hql02, map02);
            sumScore = Integer.parseInt(String.valueOf(resultList01.get(0)));
        }

        String hql03 = "SELECT count(id) FROM org.forten.zuoye.model.LinedCS WHERE courseId=:coId AND chooseStatus=1";
        Map<String,Object> map03 = new HashMap<>();
        map03.put("coId",coId);
        List<?> resultList02 = hDao.findBy(hql03,map03);
        int chosenNum = Integer.parseInt(String.valueOf(resultList02.get(0)));

        Course course = hDao.getById(coId,Course.class);

        if(chosenNum < course.getClassCapacity()){
            int limitScore = NumberUtil.parseNumber(PropertiesFileReader.getValue("properties/settings","SCORE_LIMIT"),Integer.class);
            if((course.getScore() + sumScore)>limitScore){
                return 3;
            }else {
                cs.setChooseStatus(1);
                cs.setUpadteTime(new Date());
                return updateCS(cs);
            }
        }else {
            return 2;
        }
    }

    @Transactional
    //回传1代表排队成功，0代表后台操作失败
    public int getInLine(int stuId, int coId){
        LinedCS cs = new LinedCS(stuId, new Date(), new Date(), coId, 2, 0);
        return saveCS(cs);
    }


    private int saveCS(LinedCS cs){
        try {
            hDao.save(cs);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int updateCS(LinedCS cs){
        try {
            hDao.update(cs);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }



    private Date getPastDate(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        int month = NumberUtil.parseNumber(PropertiesFileReader.getValue("properties/settings","MONTH"),Integer.class);
        c.add(Calendar.MONTH,-month);
        Date pastDate = c.getTime();
        return pastDate;
    }

    @Transactional
    public Message deleteCourseList(int id, Integer ...ids){
        String hql="DELETE From LinedCS WHERE studentId=:id AND chooseStatus!=4 AND courseId IN (:ids) ";
        Map<String ,Object> params= new HashMap<>(2);
        params.put("id",id);
        params.put("ids", Arrays.asList(ids));
        int tag=hDao.executeUpdate(hql,params);
        if(tag!=0){
            changeStatus(ids);
            return new Message("退课成功");
        }
        else
            return new Message("退课失败");
    }

    @Transactional
    public RoWithPage<Course4ShowDto> doListAll(CourseQo4Stu qo ,int id){
        //选出未修过课程的ID
        String noSelectHql = "SELECT courseId FROM LinedCS WHERE chooseStatus=4 AND studentId=:id ";
        Map<String, Object> param1 = new HashMap<>(1);
        param1.put("id",id);
        List<Integer> idList =hDao.findBy(noSelectHql,param1);
        Integer[] ids=new Integer[idList.size()];
        idList.toArray(ids);

        Map<String, Object> param2 = new HashMap<>(1);
        param2.put("ids",idList);

        //选出未修课程总数目
        String countHql="SELECT count(id) FROM Course WHERE 1=1 AND id NOT IN (:ids)";
        long count= hDao.findOneBy(countHql,param2);

        //无符合条件数据
        if(count==0)
            return RoWithPage.EMPTY_RO;
        else {
            //分页信息
            PageInfo page = PageInfo.getInstance(qo.getPageNo(), qo.getPageSize(), count);
            //选出单页排除已修并无选择状态的课程
            String hql = "SELECT new org.forten.zuoye.dto.course.Course4ShowDto(c.id, c.name, c.classRoom, c.teacher, c.courseStartTime, c.courseEndTime, c.classStartTime, c.classEndTime, c.score, c.classCapacity) " +
                    "FROM Course c WHERE c.id NOT IN (:ids)";
            List<Course4ShowDto> courseList = hDao.findBy(hql, param2, (int) page.getFirstResultNum(), page.getPageSize());

            //选出课程状态
            String selectedHql = "SELECT new org.forten.zuoye.dto.course.CourseIdDto(courseId,chooseStatus) FROM LinedCS WHERE studentId= :id ";

            List<CourseIdDto> selectedCourse = hDao.findBy(selectedHql, param1);

            for (Course4ShowDto x : courseList) {
                for (CourseIdDto i : selectedCourse) {
                    if (i.getCourseId() == x.getId()) {
                        x.setChooseStatus(i.getChooseStatus());
                    }
                }
            }
            return new RoWithPage<>(courseList, page);
        }
    }

    @Transactional
    public void changeStatus (Integer ...ids) {
        for (int i = 0; i < ids.length; i++) {
            String hql = "SELECT new org.forten.zuoye.model.LinedCS(id,studentId,chooseCourseTime,updateTime,courseId,chooseStatus,attendanceStatus) FROM LinedCS WHERE chooseStatus=2 AND courseId=:couId ";
            Map<String, Object> param = new HashMap<>(1);
            param.put("couId", ids[i]);
            List<LinedCS> students=hDao.findBy(hql, param);

            for (LinedCS cs : students) {
                System.out.println(cs);
                updateCourse(cs,ids[i]);
            }
        }
    }

}
