package org.forten.zuoye.action;

import org.forten.zuoye.bo.LoginResetBo;
import org.forten.zuoye.dto.LoginQo;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.student.LoginStudent;
import org.forten.zuoye.dto.student.LoginedStudent;
import org.forten.zuoye.dto.student.RenShiAdmin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

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
    Message login(@RequestBody LoginQo qo,HttpSession session){
        LoginedStudent dto = bo.login(qo.getName(), qo.getPassword());
        session.setAttribute("logined",dto);
        LoginedStudent d=(LoginedStudent) session.getAttribute("logined");
        System.out.println(d);
        if (dto == null) {
            return new Message("0");
        } else {
            return new Message("1");
        }
    }
    @RequestMapping("login01")
    public @ResponseBody Message login01(@RequestBody LoginQo qo , HttpSession session) {
        RenShiAdmin rs= new RenShiAdmin("admin","123456");
        session.setAttribute("renShiAdmin",rs);
        if (qo.getName().equals(rs.getName()) && qo.getPassword().equals(rs.getPassword())) {
            return new Message("0");
        } else {
            return new Message("1");
        }
    }
    @RequestMapping("login02")
    public @ResponseBody Message login02(@RequestBody LoginQo qo, HttpSession session) {
        RenShiAdmin jj= new RenShiAdmin("admin","456789");
        session.setAttribute("jiJiaoAdmin",jj);
        if (qo.getName().equals(jj.getName()) && qo.getPassword().equals(jj.getPassword())) {
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
