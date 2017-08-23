package org.forten.zuoye.dto.student;

/**
 * Created by student5 on 2017/8/21.
 */
public class StudentLogin {
    private String name;
    private String password;

    public StudentLogin() {
    }

    public StudentLogin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "StudentLogin{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
