package org.forten.zuoye.dto.course;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by student5 on 2017/8/21.
 */
public class CourseChangeDto {
    private int id;
    private String name;
    private String teacher;
    private String classRoom;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date courseStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date courseEndTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date classStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date classEndTime;
    private int classCapacity;
    private int score;

    public CourseChangeDto() {
    }

    public CourseChangeDto(int id, String name, String teacher, String classRoom, Date courseStartTime, Date courseEndTime, Date classStartTime, Date classEndTime, int classCapacity, int score) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.classRoom = classRoom;
        this.courseStartTime = courseStartTime;
        this.courseEndTime = courseEndTime;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.classCapacity = classCapacity;
        this.score = score;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
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

    public int getClassCapacity() {
        return classCapacity;
    }

    public void setClassCapacity(int classCapacity) {
        this.classCapacity = classCapacity;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "CourseChangeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", courseStartTime=" + courseStartTime +
                ", courseEndTime=" + courseEndTime +
                ", classStartTime=" + classStartTime +
                ", classEndTime=" + classEndTime +
                ", classCapacity=" + classCapacity +
                ", score=" + score +
                '}';
    }
}
