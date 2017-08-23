package org.forten.zuoye.action;

import org.forten.zuoye.bo.CoManageBo;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.common.RoWithPage;
import org.forten.zuoye.dto.course.Course4CoManageRo;
import org.forten.zuoye.dto.course.CourseQo;
import org.forten.zuoye.dto.course.Dto4SaveCourse;
import org.forten.zuoye.dto.student.CourseStudentQo;
import org.forten.zuoye.dto.student.Student4ShowRo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * Created by student1 on 2017/8/15.
 */
@Controller
@RequestMapping("/comanage/")
@SessionAttributes({"courseId"})
public class CoManageAction {
    @Resource
    private CoManageBo bo;

    @RequestMapping("selectBy")
    public @ResponseBody RoWithPage<Course4CoManageRo> selectBy(@RequestBody CourseQo qo){
        return bo.selectBy(qo);
    }

    @RequestMapping("delByIds")
    public @ResponseBody Message delByIds(@RequestBody Integer... ids){
        System.out.println(ids);
        List<Integer> list = Arrays.asList(ids);
        return bo.doDeleteCourse(list);
    }

    @RequestMapping("saveCourse")
    public @ResponseBody Message saveCourse(@RequestBody Dto4SaveCourse course){
        return bo.doSaveCourse(course);
    }

    //重定向到课程学生信息页面
    //课程ID放到session中
    @RequestMapping("courseStudent")
    public @ResponseBody int RedirectCourseStudent(int id,HttpSession session){
        session.setAttribute("courseId",id);
        return 1;
    }

    //列出已选该门课程的学生
    //课程ID从session中获得
    @RequestMapping("courseStudentList")
    public @ResponseBody RoWithPage<Student4ShowRo> listCourseStudent(CourseStudentQo qo, HttpSession session){
        int courseId = (Integer) session.getAttribute("courseId");
        return bo.doListStudentByCourse(qo,courseId);
    }
}
