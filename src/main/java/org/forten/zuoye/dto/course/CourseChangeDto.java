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
    private String score;

    public CourseChangeDto() {
    }

    public CourseChangeDto(int id, String name, String teacher, String classRoom, Date courseStartTime, Date courseEndTime, Date classStartTime, Date classEndTime, String score) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.classRoom = classRoom;
        this.courseStartTime = courseStartTime;
        this.courseEndTime = courseEndTime;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
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

    public String  getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public Date getCourseStartTime() {
        return courseStartTime;
    }
    public void setCourseStartTime(Date courseStartTime) {
        if (this.courseStartTime.getTime() < new Date().getTime()) {
            this.courseStartTime =courseStartTime;
        }else {
            this.courseStartTime= this.courseStartTime;
        }
    }

    public Date getCourseEndTime() {
        return courseEndTime;
    }

    public void setCourseEndTime(Date courseEndTime) {
        this.courseEndTime = courseEndTime;
    }

    public Date getClassStateTime() {
        return classStartTime;
    }

    public void setClassStateTime(Date classStateTime) {
        this.classStartTime = classStateTime;
    }

    public Date getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(Date classEndTime) {
        this.classEndTime = classEndTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "CourseChangeDto{" +
                "name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", classRoom=" + classRoom +
                ", courseStartTime=" + courseStartTime +
                ", courseEndTime=" + courseEndTime +
                ", classStateTime=" + classStartTime +
                ", classEndTime=" + classEndTime +
                ", score='" + score + '\'' +
                '}';
    }
}
