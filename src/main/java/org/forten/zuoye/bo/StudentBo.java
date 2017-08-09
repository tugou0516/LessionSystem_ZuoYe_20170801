package org.forten.zuoye.bo;

import org.forten.utils.common.StringUtil;
import org.forten.utils.system.PageInfo;
import org.forten.zuoye.dao.HibernateDao;
import org.forten.zuoye.dao.MyBatisDao;
import org.forten.zuoye.dto.*;
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
    public void doSaveH(Student4SaveDto dto){
        Student stu =new Student();
        BeanPropertyUtil.copy(stu,dto);
        hDao.save(stu);
    }

    @Transactional
    public Message doDeleteH(Integer ...ids){
        String hql="DELETE FROM Student WHERE id IN (:ids) ";
        Map<String,Object> params=new HashMap<>(1);
        List idList = Arrays.asList(ids);
        params.put("ids",idList);
        try {
            int n = hDao.executeUpdate(hql, params);
            return new Message("成功删除了" + n + "条数据");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("删除时出现错误");
        }
    }

    @Transactional
    public Message doUpdateH(int  id,Student4SaveDto dto){
        Student s = hDao.loadById(id, Student.class);
        BeanPropertyUtil.copy(s, dto);
        try {
            hDao.update(s);
            return new Message("更新信息成功");
        } catch (Exception e) {
            return new Message("更新信息失败");
        }
    }

    @Transactional
    public Student4ShowRo doSelectByIdH(int id){
        Student stu = hDao.getById(id,Student.class);
        Student4ShowRo student =new Student4ShowRo();
        BeanPropertyUtil.copy(student, stu);
        return student;
    }

    @Transactional
    public List<Student4ShowRo> doListAllH(){
        String hql="SELECT new org.forten.zuoye.dto.Student4ShowRo(id,loginName,name,gender,position,birthday,tel,email,score) FROM Student ";
        List<Student4ShowRo> stuList = hDao.findBy(hql);
        return stuList;
    }

    @Transactional(readOnly = true)
    public RoWithPage<Student4ShowRo> queryByH(StudentQo qo) {
        String countHql = "SELECT count(id) FROM Student WHERE 1=1 ";
        String hql = "SELECT new org.forten.zuoye.dto.Student4ShowRo(id,loginName,name,gender,position,birthday,tel,email,score) FROM Student WHERE 1=1 ";
        Map<String, Object> params = new HashMap<>();

        if (StringUtil.hasText(qo.getName())) {
            countHql = countHql + "AND name LIKE :name ";
            hql = hql + "AND name LIKE :name ";
            params.put("name", "%" + qo.getName() + "%");
        }

        hql = hql + "ORDER BY loginName DESC";

        long count = hDao.findOneBy(countHql, params);

        if (count == 0) {
            // 如果没有符合查询条件的数据，则返回安全的空Ro对象
            return RoWithPage.EMPTY_RO;
        }

        PageInfo page = PageInfo.getInstance(qo.getPageNo(), qo.getPageSize(), count);

        List<Student4ShowRo> list = hDao.findBy(hql, params, (int) page.getFirstResultNum(), page.getPageSize());

        return new RoWithPage<>(list, page);
    }
}
