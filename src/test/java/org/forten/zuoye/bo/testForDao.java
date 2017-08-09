package org.forten.zuoye.bo;

import org.forten.zuoye.dto.Student4SaveDto;
import org.forten.zuoye.dto.Student4ShowRo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/**/app-*.xml"})
public class testForDao {
    @Resource
    private StudentBo Bo;

    @Test
    public void testDoSaveH(){
        Student4SaveDto stu = new Student4SaveDto("0010","123456","小明","男","处长",new Date(),"13245896327","123456@qq.com",100);
        Bo.doSaveH(stu);
    }

    @Test
    public void testDoDeleteH(){
        Bo.doDeleteH(6,5,4);
    }

    @Test
    public void testDoUpdateH(){
        Student4SaveDto stu =new Student4SaveDto("0010","123456","小明","男","处长",new Date(),"13245896327","123456@qq.com",100);
        Bo.doUpdateH(1,stu);
    }

    @Test
    public void testDoSelectByIdH(){
        Student4ShowRo student = Bo.doSelectByIdH(2);
        System.out.print(student);
    }

    @Test
    public void testDoListAllH(){
        System.out.print(Bo.doListAllH());
    }
}
