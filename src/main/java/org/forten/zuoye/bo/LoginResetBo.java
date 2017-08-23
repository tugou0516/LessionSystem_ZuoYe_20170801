package org.forten.zuoye.bo;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.forten.utils.common.NumberUtil;
import org.forten.utils.security.SHA1Util;
import org.forten.utils.system.BeanPropertyUtil;
import org.forten.zuoye.dao.HibernateDao;
import org.forten.zuoye.dao.MyBatisDao;
import org.forten.zuoye.dto.*;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.student.LoginStudent;
import org.forten.zuoye.dto.student.LoginedStudent;
import org.forten.zuoye.dto.student.Student4SaveDto;
import org.forten.zuoye.dto.student.Student4ShowRo;
import org.forten.zuoye.mapper.StudentMapper;
import org.forten.zuoye.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2017/8/7.
 */
@Service("LoginResetBo")
public class LoginResetBo {
    @Resource
    private MyBatisDao<StudentMapper> mDao;
    @Resource
    private HibernateDao hDao;
    @Transactional
    public Message doResetPassword(int id,String newPwd,String oldPwd){
        String hql="SELECT count(id) FROM Student WHERE id=:id AND password=:oldpwd";
        Map<String,Object> params= new HashMap<>();
        params.put("id",id);
        params.put("oldPwd",oldPwd);
        long count = hDao.findOneBy(hql,params);
        if (count == 0 ){
            return new Message("旧密码不正确，请再次确认你的旧密码");
        }
        hql = "UPDATE Student SET password=:pwd WHERE id=:id";
        params.clear();
        params.put("id", id);
        params.put("pwd", newPwd);

        try {
            hDao.executeUpdate(hql, params);
            return new Message("密码修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("密码修改失败");
        }
    }
    @Transactional(readOnly = true)
        public LoginedStudent login(String name, String password) {
            String hql = "SELECT new org.forten.zuoye.dto.student.LoginedStudent(id,name,email) FROM Student WHERE loginName=:xm AND password=:pwd";
            Map<String, Object> params = new HashMap<>();
            params.put("xm", name);
            params.put("pwd", password);
            LoginedStudent dto = hDao.findOneBy(hql, params);
            return dto;
    }
    @Transactional
    public Message forgetPwd(String name){
        String hql = "SELECT new org.forten.zuoye.dto.student.Student4ShowRo(id, loginName, password, name, gender, position, birthday, tel, email) FROM Student WHERE loginName=:n";
        Map<String ,Object> params = new HashMap<>(1);
        params.put("n",name);
        Student4ShowRo ro = hDao.findOneBy(hql,params);
        if (ro != null) {
            String pwdCode = getRandomStr();
            hql = "UPDATE Student SET password=:fp WHERE id=:id";
            params.clear();
            params.put("id", ro.getId());
            params.put("fp", pwdCode);
            hDao.executeUpdate(hql, params);
            new Thread(() -> {
                try {
                    HtmlEmail email = new HtmlEmail();
                    email.setHostName("smtp.126.com");
                    email.setCharset("UTF-8");
                    email.setSmtpPort(465);
                    email.setAuthenticator(new DefaultAuthenticator("thcic_test", "a123456"));
                    email.setSSLOnConnect(true);
                    email.addTo(ro.getEmail(), ro.getName());
                    email.setFrom("thcic_test@126.com", "System");
                    email.setSubject("重置密码");
                    email.setHtmlMsg("<p>系统已经为你重置密码，新密码为：<strong>" + pwdCode + "</strong></p><p>请尽快<a href='http://localhost:8081/login.html'>登录</a>系统修改密码！</p>");
                    email.send();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            return new Message("已经向" + ro.getEmail() + "发送了密码重置邮件，请查收");
        } else {
            return new Message("用户名不存在");
        }
    }
    private String getRandomStr(){
        String s = "";
        for(int i = 0;i<6;i++){
            s = s+ (char) NumberUtil.getRandomInteger(97,(97+25));
        }
        return s;
    }
    @Transactional
    public Message sendEmail(String email ){
        new Thread(() -> {
            try {
                HtmlEmail email02 = new HtmlEmail();
                email02.setHostName("smtp.126.com");
                email02.setCharset("UTF-8");
                email02.setSmtpPort(465);
                email02.setAuthenticator(new DefaultAuthenticator("thcic_test", "a123456"));
                email02.setSSLOnConnect(true);
                email02.addTo(email);
                email02.setFrom("thcic_test@126.com", "System");
                email02.setSubject("重置密码");
                email02.setHtmlMsg("<p>系统已经为你重置密码，新密码为：<strong>" + "" + "</strong></p><p>请尽快<a href='http://localhost:8081/login.html'>登录</a>系统修改密码！</p>");
                email02.send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return new Message("已经向" + email + "发送了邮件，请查收");
    }
}
