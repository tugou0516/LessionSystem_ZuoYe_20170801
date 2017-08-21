package org.forten.zuoye.bo;

import org.forten.utils.common.StringUtil;
import org.forten.utils.system.BeanPropertyUtil;
import org.forten.utils.system.PageInfo;
import org.forten.zuoye.dao.HibernateDao;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.common.RoWithPage;
import org.forten.zuoye.dto.course.Course4CoManageRo;
import org.forten.zuoye.dto.course.CourseQo;
import org.forten.zuoye.dto.course.Dto4SaveCourse;
import org.forten.zuoye.dto.course.Dto4UpdateCourse;
import org.forten.zuoye.dto.student.CourseStudentQo;
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
    public RoWithPage<Student4ShowRo> doListStudentByCourse(CourseStudentQo qo, int id){
        String countHql="SELECT count(s.id) " +
                "FROM Student s RIGHT JOIN LinedCS l ON (s.id=l.studentId) WHERE l.courseId=:id ";
        String hql="SELECT new org.forten.zuoye.dto.student.Student4ShowRo(s.id, s.loginName, s.name, s.gender, s.position, s.birthday, s.tel, s.email) " +
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

        long count=hDao.findOneBy(countHql,param);
        if (count==0)
            return RoWithPage.EMPTY_RO;
        else{
            PageInfo page =PageInfo.getInstance(qo.getPageNo(),qo.getPageSize(),count);
            List<Student4ShowRo> studentList =hDao.findBy(hql,param,(int)page.getFirstResultNum(),page.getPageSize());
            return new RoWithPage<>(studentList,page);
        }
    }

}
