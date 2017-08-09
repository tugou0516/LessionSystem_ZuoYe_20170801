package org.forten.zuoye.bo;

import org.forten.utils.common.StringUtil;
import org.forten.utils.system.PageInfo;
import org.forten.zuoye.dao.HibernateDao;
import org.forten.zuoye.dao.MyBatisDao;
import org.forten.zuoye.dto.*;
import org.forten.zuoye.dto.common.Message;
import org.forten.zuoye.dto.student.Student4SaveDto;
import org.forten.zuoye.dto.student.Student4ShowRo;
import org.forten.zuoye.mapper.StudentMapper;
import org.forten.zuoye.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.forten.utils.system.BeanPropertyUtil;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2017/8/7.
 */
@Service("studentBo")
public class StudentBo {
    @Resource
    private MyBatisDao<StudentMapper> mDao;
    @Resource
    private HibernateDao hDao;


    @Transactional
    public Student4ShowRo doSelectByIdH(int id){
        Student stu = hDao.getById(id,Student.class);
        Student4ShowRo student =new Student4ShowRo();
        BeanPropertyUtil.copy(student, stu);
        return student;
    }

}
