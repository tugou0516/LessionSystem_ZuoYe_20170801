package org.forten.zuoye.bo;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.forten.zuoye.dao.HibernateDao;
import org.forten.zuoye.dto.EmailDto;
import org.forten.zuoye.dto.EmailQo;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.course.CourseChangeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by student5 on 2017/8/21.
 */
@Service
public class CourseChangeBo {
    @Resource
    private HibernateDao dao;
    @Transactional
    public Message doUpdate(CourseChangeDto dto){
        String hql= "UPDATE Course SET name=:name,teacher=:teacher,classRoom=:classRoom,courseStartTime=:courseStartTime,courseEndTime=:courseEndTime,classStateTime=:classStateTime,classEndTime=:classEndTime,score=:score WHERE id=:id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", dto.getId());
        params.put("name", dto.getName());
        params.put("teacher", dto.getTeacher());
        params.put("classRoom", dto.getClassRoom());
        params.put("courseStartTime", dto.getCourseStartTime());
        params.put("courseEndTime", dto.getCourseEndTime());
        params.put("classStateTime", dto.getClassStateTime());
        params.put("classEndTime()", dto.getClassEndTime());
        params.put("score", dto.getScore());
        try {
            dao.executeUpdate(hql, params);
            return new Message("数据更新成功");
        }catch(Exception e){
            e.printStackTrace();
            return new Message("数据更新失败");
        }
    }
    @Transactional
    public Message sendEmail(EmailDto dto) {
        String hql = "SELECT count(studentId) FROM LinedCS WHERE courseId=:id ";
        Map<String, Object> params = new HashMap<>();
        int id = dto.getId();
        params.put("id", id);
        long count = dao.findOneBy(hql, params);
        if (count == 0) {
            return new Message("暂无学生选择此课");
        }
        String hql03 = "SELECT (s.email) " +
                "FROM Student s RIGHT JOIN LinedCS l ON (s.id=l.studentId) WHERE l.courseId=:id ";
        Map<String, Object> params02 = new HashMap<>();
        params02.put("id", id);
       List<String> list= dao.findBy(hql03,params02);
       StringBuilder sd= new StringBuilder();
        for (String email :list) {
           sd.append(email);
           sd.append(",");
        }
        sd.replace(sd.length()-1,sd.length(),"");
            new Thread(() -> {
                try {
                    HtmlEmail email = new HtmlEmail();
                    email.setHostName("smtp.126.com");
                    email.setCharset("UTF-8");
                    email.setSmtpPort(465);
                    email.setAuthenticator(new DefaultAuthenticator("thcic_test", "a123456"));
                    email.setSSLOnConnect(true);
                    email.addTo(sd.toString());
                    email.setFrom("thcic_test@126.com", "System");
                    email.setSubject("课程信息修改提醒");
                    email.setHtmlMsg(dto.getTest());
                    email.send();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        return new Message("课程更改的消息已经发送给已经选择此课程的学员Email中");
    }
}
