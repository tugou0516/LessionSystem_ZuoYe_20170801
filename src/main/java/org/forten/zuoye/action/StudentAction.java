package org.forten.zuoye.action;

import org.forten.zuoye.bo.StudentBo;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.common.RoWithPage;
import org.forten.zuoye.dto.course.Course4ShowDto;
import org.forten.zuoye.dto.course.Course4StuShowRo;
import org.forten.zuoye.dto.course.CourseQo4Stu;
import org.forten.zuoye.dto.student.LoginedStudent;
import org.forten.zuoye.dto.student.Student4SaveDto;
import org.forten.zuoye.dto.student.Student4ShowRo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */
@Controller
@RequestMapping("/student/")
public class StudentAction {
    @Resource
    private StudentBo bo;

    @RequestMapping("showPrivateInfo")
    public @ResponseBody
    Student4ShowRo getOne(HttpSession session){
        //        从session获得学员id
        LoginedStudent user = (LoginedStudent) session.getAttribute("logined");
        int id=user.getId();
        return bo.doSelectByIdH(id);
    }

    @RequestMapping("showCompletedCourse")
    public @ResponseBody
    List<Course4StuShowRo> listCompletedCourse(HttpSession session){
        //        从session获得学员id
        LoginedStudent user = (LoginedStudent) session.getAttribute("logined");
        int id=user.getId();
        return bo.listCompletedCourse(id);
    }

    @RequestMapping("showOtherCourse")
    public @ResponseBody
    List<Course4StuShowRo> listOtherCourse(HttpSession session){
        //      从session获得学员id
        LoginedStudent user = (LoginedStudent) session.getAttribute("logined");
        int id=user.getId();
        return bo.listOtherCourse(id);
    }

    @RequestMapping("deleteCompletedCourse")
    public @ResponseBody Message
    deleteSelectedCourse(HttpSession session,@RequestBody Integer...ids){
        //      从session获得学员id
        LoginedStudent user = (LoginedStudent) session.getAttribute("logined");
        int id=user.getId();
        return bo.deleteCourseList(id,ids);
    }

    @RequestMapping("chooseCourse")
    public @ResponseBody int chooseCourse(HttpSession session,int id){
        //  从session获得学员id
        LoginedStudent user = (LoginedStudent) session.getAttribute("logined");
        int stuId=user.getId();
        int coId = id;
        return bo.chooseCourse(stuId,coId);
    }

    @RequestMapping("getInLine")
    public @ResponseBody int getInLine(HttpSession session,int id){
        LoginedStudent user = (LoginedStudent) session.getAttribute("logined");
        int stuId=user.getId();
        int coId = id;
        return bo.getInLine(stuId,coId);
    }

    @RequestMapping("listAllCourse")
    public @ResponseBody
    RoWithPage<Course4ShowDto> list(HttpSession session,@RequestBody CourseQo4Stu qo) {
        LoginedStudent user = (LoginedStudent) session.getAttribute("logined");
        int id =user.getId();
        return bo.doListAll(qo,id);
    }


}
