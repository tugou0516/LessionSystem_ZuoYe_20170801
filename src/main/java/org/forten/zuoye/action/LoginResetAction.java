package org.forten.zuoye.action;

import org.forten.zuoye.bo.LoginResetBo;
import org.forten.zuoye.dto.LoginQo;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.student.LoginStudent;
import org.forten.zuoye.dto.student.LoginedStudent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/8/8.
 */
@Controller
@RequestMapping("/student")
public class LoginResetAction {
    @Resource
    private LoginResetBo bo;
    @RequestMapping("resetPassword")
    public @ResponseBody Message resetPassword(String oldPwd, String newPwd, HttpSession session){
        LoginedStudent stu = (LoginedStudent) session.getAttribute("logined");
        int id = stu.getId();
        return bo.doResetPassword(id, oldPwd, newPwd);
    }
    @RequestMapping("login")
    public @ResponseBody
    Message login(@RequestBody LoginQo qo){
        LoginStudent dto = bo.login(qo.getName(), qo.getPassword());
        if (dto == null) {
            return new Message("0");
        } else {
            return new Message("1");
        }
    }
    @RequestMapping("login01")
    public @ResponseBody Message login01(@RequestBody LoginQo qo) {
        if (qo.getName().equals("admin") && qo.getPassword().equals("123456")) {
            return new Message("0");
        } else {
            return new Message("1");
        }
    }
    @RequestMapping("login02")
    public @ResponseBody Message login02(@RequestBody LoginQo qo) {
        if (qo.getName().equals("admin") && qo.getPassword().equals("456789")) {
            return new Message("0");
        } else {
            return new Message("1");
        }
    }
    @RequestMapping("forgetPwd")
    public @ResponseBody Message forgetPwd(String name){
        return bo.forgetPwd(name);
    }
}
