package org.forten.zuoye.action;

import org.forten.zuoye.bo.StudentBo;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.common.RoWithPage;
import org.forten.zuoye.dto.course.Course4ShowDto;
import org.forten.zuoye.dto.course.Course4StuShowRo;
import org.forten.zuoye.dto.course.CourseQo4Stu;
import org.forten.zuoye.dto.student.Student4SaveDto;
import org.forten.zuoye.dto.student.Student4ShowRo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    Student4ShowRo getOne(){
        //        从session获得学员id
        int id=1;
        return bo.doSelectByIdH(id);
    }

    @RequestMapping("showCompletedCourse")
    public @ResponseBody
    List<Course4StuShowRo> listCompletedCourse(){
        //        从session获得学员id
        int id=1;
        return bo.listCompletedCourse(id);
    }

    @RequestMapping("showOtherCourse")
    public @ResponseBody
    List<Course4StuShowRo> listOtherCourse(){
        //      从session获得学员id
        int id=1;
        return bo.listOtherCourse(id);
    }

    @RequestMapping("deleteCompletedCourse")
    public @ResponseBody Message
    deleteSelectedCourse(@RequestBody Integer...ids){
        //      从session获得学员id
        int id=1;
        return bo.deleteCourseList(id,ids);
    }

    @RequestMapping("chooseCourse")
    public @ResponseBody int chooseCourse(int id){
        //  从session获得学员id
        int stuId = 1;
        int coId = id;
        return bo.chooseCourse(stuId,coId);
    }

    @RequestMapping("getInLine")
    public @ResponseBody int getInLine(int id){
        int stuId = 1;
        int coId = id;
        return bo.getInLine(stuId,coId);
    }

    @RequestMapping("listAllCourse")
    public @ResponseBody
    RoWithPage<Course4ShowDto> list(@RequestBody CourseQo4Stu qo) {
        // 从session获得学员id
        int id = 1;
        return bo.doListAll(qo,id);
    }


}
