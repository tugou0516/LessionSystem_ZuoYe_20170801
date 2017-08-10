package org.forten.zuoye.dto;

import org.forten.utils.common.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by student3 on 2017/8/9.
 */
public class Course4ShowDto {
    private int id;
    private String name;
    private String classRoom;
    private String teacher;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date courseStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date courseEndTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date classStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date classEndTime;
    private int score;
    private int classCapacity;

    public Course4ShowDto() {
    }

    public Course4ShowDto(int id, String name, String classRoom, String teacher, Date courseStartTime, Date courseEndTime, Date classStartTime, Date classEndTime, int score, int classCapacity) {
        this.id = id;
        this.name = name;
        this.classRoom = classRoom;
        this.teacher = teacher;
        this.courseStartTime = courseStartTime;
        this.courseEndTime = courseEndTime;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.score = score;
        this.classCapacity = classCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Date getCourseStartTime() {
        return courseStartTime;
    }

    public void setCourseStartTime(Date courseStartTime) {
        this.courseStartTime = courseStartTime;
    }

    public Date getCourseEndTime() {
        return courseEndTime;
    }

    public void setCourseEndTime(Date courseEndTime) {
        this.courseEndTime = courseEndTime;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getClassCapacity() {
        return classCapacity;
    }

    public void setClassCapacity(int classCapacity) {
        this.classCapacity = classCapacity;
    }

    public String getCourseStartTimeStr() {
        return DateUtil.convertDateToString(courseStartTime,"yyyy年MM月dd日 HH:mm:ss");
    }
    public String getCourseEndTimeStr() {
        return DateUtil.convertDateToString(courseStartTime,"yyyy年MM月dd日 HH:mm:ss");
    }
    public String getClassStartTimeStr() {
        return DateUtil.convertDateToString(courseStartTime,"yyyy年MM月dd日 HH:mm:ss");
    }
    public String getClassEndTimeStr() {
        return DateUtil.convertDateToString(courseStartTime,"yyyy年MM月dd日 HH:mm:ss");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course4ShowDto that = (Course4ShowDto) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Course4ShowDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", teacher='" + teacher + '\'' +
                ", courseStartTime=" + courseStartTime +
                ", courseEndTime=" + courseEndTime +
                ", classStartTime=" + classStartTime +
                ", classEndTime=" + classEndTime +
                ", score=" + score +
                ", classCapacity=" + classCapacity +
                '}';
    }
}
