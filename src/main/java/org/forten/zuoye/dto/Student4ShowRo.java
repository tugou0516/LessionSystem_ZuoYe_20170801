package org.forten.zuoye.dto;

import org.forten.utils.common.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/8.
 */
public class Student4ShowRo {
    private int id;
    private String loginName;
    private String name;
    private String gender;
    private String position;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String tel;
    private String email;
    private double score;

    public Student4ShowRo() {
    }

    public Student4ShowRo(int id, String loginName, String name, String gender, String position, Date birthday, String tel, String email, double score) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.gender = gender;
        this.position = position;
        this.birthday = birthday;
        this.tel = tel;
        this.email = email;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public String getBirthdayStr() {
        return birthday == null ? "" : DateUtil.convertDateToString(birthday, "yyyy-MM-dd");
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student4ShowRo that = (Student4ShowRo) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Student4ShowRo{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", position='" + position + '\'' +
                ", birthday=" + birthday +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", score=" + score +
                '}';
    }
}
