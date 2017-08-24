package org.forten.zuoye.action;

import org.apache.commons.mail.Email;
import org.forten.zuoye.bo.CourseChangeBo;
import org.forten.zuoye.dto.EmailDto;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.course.CourseChangeDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by student5 on 2017/8/21.
 */
@RestController
@RequestMapping("")
public class CourseChange {
    @Resource
    private CourseChangeBo bo;
    @RequestMapping("update")
    public Message update(@RequestBody CourseChangeDto dto){
        return bo.doUpdate(dto);
    }
    @RequestMapping("sendEmail")
    public Message sendEmail(@RequestBody EmailDto dto){
        return bo.sendEmail(dto);
    }
}
