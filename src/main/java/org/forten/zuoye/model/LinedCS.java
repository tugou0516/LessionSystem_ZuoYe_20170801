package org.forten.zuoye.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by student1 on 2017/8/2.
 */
@Entity
@Table(name="test_collection")
public class LinedCS {
    @Id
    private int id;
    @Column(name="student_id")
    private int studentId;
    @Column(name="choose_course_time")
    private Date chooseCourseTime;
    @Column(name="update_time")
    private Date updateTime;
    @Column(name="course_id")
    private int courseId;
    @Column(name = "choose_status")
    private int chooseStatus;
    @Column(name="attendance_status")
    private int attendanceStatus;

    public LinedCS() {
    }

    public LinedCS(int studentId, Date chooseCourseTime, Date upadteTime, int courseId, int chooseStatus, int attendanceStatus) {
        this.studentId = studentId;
        this.chooseCourseTime = chooseCourseTime;
        this.updateTime = upadteTime;
        this.courseId = courseId;
        this.chooseStatus = chooseStatus;
        this.attendanceStatus = attendanceStatus;
    }

    public LinedCS(int id, int studentId, Date chooseCourseTime, Date updateTime, int courseId, int chooseStatus, int attendanceStatus) {
        this.id = id;
        this.studentId = studentId;
        this.chooseCourseTime = chooseCourseTime;
        this.updateTime = updateTime;
        this.courseId = courseId;
        this.chooseStatus = chooseStatus;
        this.attendanceStatus = attendanceStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Date getChooseCourseTime() {
        return chooseCourseTime;
    }

    public void setChooseCourseTime(Date chooseCourseTime) {
        this.chooseCourseTime = chooseCourseTime;
    }

    public Date getUpadteTime() {
        return updateTime;
    }

    public void setUpadteTime(Date upadteTime) {
        this.updateTime = upadteTime;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getChooseStatus() {
        return chooseStatus;
    }

    public void setChooseStatus(int chooseStatus) {
        this.chooseStatus = chooseStatus;
    }

    public int getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(int attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinedCS linedCS = (LinedCS) o;

        return id == linedCS.id;
    }

    @Override
    public String toString() {
        return "LinedCS{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", chooseCourseTime=" + chooseCourseTime +
                ", upadteTime=" + updateTime +
                ", courseId=" + courseId +
                ", chooseStatus='" + chooseStatus + '\'' +
                ", attendanceStatus='" + attendanceStatus + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return id;
    }
}