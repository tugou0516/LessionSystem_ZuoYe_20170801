package org.forten.zuoye.bo;

import org.forten.utils.common.StringUtil;
import org.forten.zuoye.dao.HibernateDao;
import org.forten.zuoye.dto.common.RoWithPage;
import org.forten.zuoye.dto.course.Course4CoManageRo;
import org.forten.zuoye.dto.course.CourseQo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by student1 on 2017/8/15.
 */
@Service("coManageBo")
public class CoManageBo {
    @Resource
    private HibernateDao hDao;

    @Transactional
    public RoWithPage<Course4CoManageRo> selectBy(CourseQo qo){
        String countHql = "SELECT count(id) FROM Course WHERE 1=1 ";
        String hql = "SELECT new org.forten.zuoye.dto.course.Course4CoManageRo(id, name, classRoom, teacher, courseStartTime, courseEndTime, classStartTime, classEndTime, score, classCapacity) FROM Course WHERE 1=1 ";
        Map<String,Object> map = new HashMap<>();

        if(StringUtil.hasText(qo.getName())){
            countHql = countHql + "AND name LIKE :name ";
            hql = hql + "AND name LIKE :name ";
            map.put("name","%"+qo.getName()+"%");
        }
        if(StringUtil.hasText(qo.getTeacher())){
            countHql = countHql + "AND teacher LIKE :t ";
            hql = hql + "AND teacher LIKE :t ";
            map.put("t","%"+qo.getTeacher()+"");
        }
        if(qo.getClassStartTime()!=null && qo.getClassEndTime()!=null){
            countHql = countHql + "AND classStartTime BETWEEN :start AND :end ";
            hql = hql + "AND classStartTime BETWEEN :start AND :end ";
            if(qo.getClassStartTime().before(qo.getClassEndTime())){
                map.put("start",qo.getClassStartTime());
                map.put("end",qo.getClassEndTime());
            }else {
                map.put("start",qo.getClassEndTime());
                map.put("end",qo.getClassStartTime());
            }
        }
        //TODO 1.开课起止时间只写其中一个的情况  2.isFinished

        return null;//完成后注掉
    }
}
