package org.forten.zuoye.action;

import org.forten.zuoye.bo.StudentBo;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.course.Course4StuShowRo;
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
        //      listOtherCourse(@RequestBody int id)
        //        从session获得学员id
        int id=1;
        return bo.doSelectByIdH(id);
    }

    @RequestMapping("showCompletedCourse")
    public @ResponseBody
    List<Course4StuShowRo> listCompletedCourse(){
        //      listOtherCourse(@RequestBody int id)
        //        从session获得学员id
        int id=1;
        return bo.listCompletedCourse(id);
    }

    @RequestMapping("showOtherCourse")
    public @ResponseBody
    List<Course4StuShowRo> listOtherCourse(){
        //      listOtherCourse(@RequestBody int id)
        //      从session获得学员id
        int id=1;
        return bo.listOtherCourse(id);
    }

    @RequestMapping("deleteCompletedCourse")
    public @ResponseBody Message
    deleteSelectedCourse(@RequestBody Integer...ids){
        //      listOtherCourse(@RequestBody int id)
        //      从session获得学员id
        int id=1;
        return bo.deleteCourseList(id,ids);
    }

    @RequestMapping("chooseCourse")
    public @ResponseBody int chooseCourse(@RequestBody int[] ids){
        int stuId = ids[0];
        int coId = ids[1];
        return bo.chooseCourse(stuId,coId);
    }

    @RequestMapping("getInLine")
    public @ResponseBody int getInLine(@RequestBody int[] ids){
        int stuId = ids[0];
        int coId = ids[1];
        return bo.getInLine(stuId,coId);
    }

}
