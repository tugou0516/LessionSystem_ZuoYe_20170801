package org.forten.zuoye.bo;

import org.forten.zuoye.dao.HibernateDao;
import org.forten.zuoye.dto.course.Course4ShowDto;
import org.forten.zuoye.dto.course.CourseId;
import org.forten.zuoye.model.Course;
import org.forten.zuoye.model.LinedCS;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by student3 on 2017/8/9.
 */
@Service("courseBo")
public class CourseBo {

    @Resource
    private HibernateDao dao;

    @Transactional(readOnly = true)
    public List<Course4ShowDto> doListAll(int id) {
        String hql01 = "SELECT new org.forten.zuoye.dto.course.CourseId(courseId,chooseStatus) FROM LinedCS WHERE studentId= :id ";
        Map<String, Object> params = new HashMap<>();
        System.out.println(id);
        params.put("id", id);
        List<CourseId> selectedCourse = dao.findBy(hql01, params);

        String hql02 = "SELECT new org.forten.zuoye.dto.course.Course4ShowDto(id, name, classRoom, teacher, courseStartTime, courseEndTime, classStartTime, classEndTime, score, classCapacity) " +
                "FROM Course ";
        List<Course4ShowDto> allCourse = dao.findBy(hql02);

        for (Course4ShowDto x : allCourse) {
            for (CourseId i : selectedCourse) {
                if (i.getCourseId() == x.getCourseId()) {
                    x.setChooseStatus(i.getChooseStatus());
                    if(i.getChooseStatus()==1) {
                        x.setChoose(false);
                    }else {
                        x.setChoose(true);
                    }
                }
            }
        }
        return allCourse;
    }
}
