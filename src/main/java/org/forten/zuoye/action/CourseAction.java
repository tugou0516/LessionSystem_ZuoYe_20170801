package org.forten.zuoye.action;

import org.forten.zuoye.bo.CourseBo;
import org.forten.zuoye.dto.Course4ShowDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by student3 on 2017/8/9.
 */
@Controller
@RequestMapping("/course")
public class CourseAction {

    @Resource
    private CourseBo bo;

    @RequestMapping("list")
    public @ResponseBody
    List<Course4ShowDto> list() {
        return bo.doListAll();
    }
}
