package org.forten.zuoye.action;

import org.forten.zuoye.bo.StudentBo;
import org.forten.zuoye.dto.Message;
import org.forten.zuoye.dto.Student4SaveDto;
import org.forten.zuoye.dto.Student4ShowRo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/8/8.
 */
@Controller
@RequestMapping("/student")
public class StudentAction {
    @Resource
    private StudentBo bo;

    @RequestMapping("show")
    public @ResponseBody Student4ShowRo getOne(){
//        从session获得学员id
        int id=1;
        return bo.doSelectByIdH(id);
    }

//    @RequestMapping("update")
//    public @ResponseBody
//    Message update(@RequestBody Student4SaveDto stu){
//
//    }
}
