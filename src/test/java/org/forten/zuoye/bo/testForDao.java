package org.forten.zuoye.bo;

import org.forten.zuoye.dto.course.Course4StuShowRo;
import org.forten.zuoye.dto.student.Student4SaveDto;
import org.forten.zuoye.dto.student.Student4ShowRo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/**/app-*.xml"})
public class testForDao {
    @Resource
    private StudentBo Bo;

    @Test
    public void testDoSelectByIdH(){
        Student4ShowRo student = Bo.doSelectByIdH(2);
        System.out.print(student);
    }

    @Test
    public void testListCompletedCourse(){
        List<Course4StuShowRo> list= Bo.listCompletedCourse(5);
        for (Course4StuShowRo courseInfo : list) {
            System.out.println(courseInfo);
        }
    }
    @Test
    public void testListOtherCourse(){
        List<Course4StuShowRo> list= Bo.listOtherCourse(5);
        for (Course4StuShowRo courseInfo : list) {
            System.out.println(courseInfo);
        }
    }


}
