package org.forten.zuoye.dto.student;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/24.
 */
public class Student4ExcelShow {
    private int stuId;
    private int courseId;
    private String loginName;
    private String name;
    private String gender;
    private String position;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String tel;
    private String email;
    private int attendStatus;

    public Student4ExcelShow() {
    }

    public Student4ExcelShow(int stuId, int courseId, String loginName,  String name, String gender, String position, Date birthday, String tel, String email, int attendStatus) {
        this.stuId = stuId;
        this.courseId = courseId;
        this.loginName = loginName;
        this.name = name;
        this.gender = gender;
        this.position = position;
        this.birthday = birthday;
        this.tel = tel;
        this.email = email;
        this.attendStatus = attendStatus;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(int attendStatus) {
        this.attendStatus = attendStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student4ExcelShow that = (Student4ExcelShow) o;

        return stuId == that.stuId;
    }

    @Override
    public int hashCode() {
        return stuId;
    }

    @Override
    public String toString() {
        return "Student4ExcelShow{" +
                "stuId=" + stuId +
                ", courseId=" + courseId +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", position='" + position + '\'' +
                ", birthday=" + birthday +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", attendStatus=" + attendStatus +
                '}';
    }
}
