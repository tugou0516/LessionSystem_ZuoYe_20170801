package org.forten.zuoye.action;

import org.forten.zuoye.bo.CoManageBo;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.common.RoWithPage;
import org.forten.zuoye.dto.course.Course4CoManageRo;
import org.forten.zuoye.dto.course.CourseQo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by student1 on 2017/8/15.
 */
@Controller
@RequestMapping("/comanage/")
public class CoManageAction {
    @Resource
    private CoManageBo bo;

    @RequestMapping("selectBy")
    public @ResponseBody RoWithPage<Course4CoManageRo> selectBy(@RequestBody CourseQo qo){
        return bo.selectBy(qo);
    }

    @RequestMapping("delByIds")
    public @ResponseBody Message delByIds(@RequestBody int[] ids){
        return bo.doDeleteCourse(ids);
    }
}
