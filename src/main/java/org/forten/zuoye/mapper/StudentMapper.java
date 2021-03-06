package org.forten.zuoye.mapper;

import org.apache.ibatis.annotations.*;
import org.forten.zuoye.dto.student.StudentDto4Mybatis;
import org.forten.zuoye.model.Student;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
public interface StudentMapper {
    @Results(id="studentMap",value={
            @Result(property="loginName",column="login_name")
    })
    @Insert("INSERT INTO test_student (login_name,password,name,gender,position,birthday,tel,email) " +
            "VALUES (#{loginName},#{password},#{name},#{gender},#{position},#{birthday},#{tel},#{email})")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Student stu);

    @Results(id="studentMap",value={
            @Result(property="loginName",column="login_name")
    })

    @ResultMap("studentMap")
    @Select("SELECT login_name,password,name,gender,position,birthday,tel,email,score FROM test_student  ")
     List<StudentDto4Mybatis> listAll() ;
}
