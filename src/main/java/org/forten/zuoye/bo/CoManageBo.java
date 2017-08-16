package org.forten.zuoye.bo;

import org.forten.utils.common.StringUtil;
import org.forten.utils.system.PageInfo;
import org.forten.zuoye.dao.HibernateDao;
import org.forten.zuoye.dto.common.RoWithPage;
import org.forten.zuoye.dto.course.Course4CoManageRo;
import org.forten.zuoye.dto.course.CourseQo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
            map.put("t","%"+qo.getTeacher()+"%");
        }

        System.out.println(qo.getClassStartTime());
        System.out.println(qo.getClassEndTime());
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
        /*if(qo.getClassStartTime()!=null && qo.getClassEndTime()==null){
            countHql = countHql + "AND classStartTime>=:st ";
            hql = hql + "AND classStartTime>=:st ";
            map.put("st",qo.getClassStartTime());
        }
        if(qo.getClassStartTime()==null && qo.getClassEndTime()!=null){
            countHql = countHql + "AND classStartTime<=:st ";
            hql = hql + "AND classStartTime<=:st ";
            map.put("st",qo.getClassEndTime());
        }*/
        System.out.println(qo.isFinished());
        if(qo.isFinished() == false){
            Date now = new Date();
            countHql = countHql + "AND classEndTime>:et ";
            hql = hql + "AND classEndTime>:et ";
            map.put("et",now);
        }
        hql = hql + "ORDER BY classStartTime DESC";
        long count = hDao.findOneBy(countHql,map);
        if(count == 0){
            return RoWithPage.EMPTY_RO;
        }

        PageInfo page = PageInfo.getInstance(qo.getPageNo(),qo.getPageSize(),count);
        List<Course4CoManageRo> list = hDao.findBy(hql,map,(int)page.getFirstResultNum(),page.getPageSize());

        return new RoWithPage<>(list,page);
    }
}
