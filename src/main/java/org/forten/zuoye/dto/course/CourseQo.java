package org.forten.zuoye.dto.course;

import org.forten.utils.system.PropertiesFileReader;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by student1 on 2017/8/14.
 */
public class CourseQo {
    private String name;
    private String teacher;
    private Date classStartTime;
    private Date classEndTime;
    private int pageNo;
    private int pageSize;
    private boolean isFinished;

    public CourseQo() {
        this.name = "";
        this.teacher = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(1900,0,1);
        this.classStartTime = calendar.getTime();
        calendar.set(2500,12,31);
        this.classEndTime = calendar.getTime();
        this.isFinished = true;//TODO
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Date getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(Date classStartTime) {
        this.classStartTime = classStartTime;
    }

    public Date getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(Date classEndTime) {
        this.classEndTime = classEndTime;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public String toString() {
        return "CourseQo{" +
                "name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", classStartTime=" + classStartTime +
                ", classEndTime=" + classEndTime +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", isFinished=" + isFinished +
                '}';
    }
}
