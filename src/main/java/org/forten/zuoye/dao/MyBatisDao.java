package org.forten.zuoye.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by student1 on 2017/7/27.
 */
@Repository("myBatisDao")
public class MyBatisDao<T> {
    @Resource
    private SqlSessionFactory ssf;

    public T getMapper(Class<T> clazz){
        return ssf.openSession().getMapper(clazz);
    }
}
