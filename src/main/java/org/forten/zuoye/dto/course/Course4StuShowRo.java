package org.forten.zuoye.dto.course;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/9.
 */
public class Course4StuShowRo {
    private String courseName;
    private String teacher;
    private String classRoom;
    private Date courseEndTime;
    private Date classStartTime;
    private Date classEndTime;
    private int score;
    private int courseStatus;
    private int attendStatus;
    private Date chooseTime;
    private Date updateTime;

    public Course4StuShowRo() {
    }

    public Course4StuShowRo(String courseName, String teacher, String classRoom, Date courseEndTime, Date classStartTime, Date classEndTime, int score, int courseStatus, int attendStatus, Date chooseTime, Date updateTime) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.classRoom = classRoom;
        this.courseEndTime = courseEndTime;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.score = score;
        this.courseStatus = courseStatus;
        this.attendStatus = attendStatus;
        this.chooseTime = chooseTime;
        this.updateTime = updateTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public int getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(int courseStatus) {
        this.courseStatus = courseStatus;
    }

    public int getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(int attendStatus) {
        this.attendStatus = attendStatus;
    }

    public Date getChooseTime() {
        return chooseTime;
    }

    public void setChooseTime(Date chooseTime) {
        this.chooseTime = chooseTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Course4StuShowRo{" +
                "courseName='" + courseName + '\'' +
                ", teacher='" + teacher + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", courseEndTime=" + courseEndTime +
                ", classStartTime=" + classStartTime +
                ", classEndTime=" + classEndTime +
                ", score=" + score +
                ", courseStatus='" + courseStatus + '\'' +
                ", attendStatus='" + attendStatus + '\'' +
                ", chooseTime=" + chooseTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
