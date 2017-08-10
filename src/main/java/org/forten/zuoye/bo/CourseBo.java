package org.forten.zuoye.bo;

import org.forten.utils.system.BeanPropertyUtil;
import org.forten.zuoye.dao.HibernateDao;
import org.forten.zuoye.dto.Course4ShowDto;
import org.forten.zuoye.model.Course;
import org.forten.zuoye.model.Student;
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
@Service("course")
public class CourseBo {

    @Resource
    private HibernateDao dao;

    @Transactional(readOnly = true)
    public List<Course4ShowDto> doListAll() {
        String hql = "SELECT new org.forten.zuoye.dto.Course4ShowDto(id, name, classRoom, teacher, courseStartTime, courseEndTime, classStartTime, classEndTime, score, classCapacity) FROM Course";
        Map<String, Object> params = new HashMap<>();
        List<Course4ShowDto> list = dao.findBy(hql, params);
        return list;
    }
}
