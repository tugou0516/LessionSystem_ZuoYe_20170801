package org.forten.zuoye.dto.student;


/**
 * Created by student5 on 2017/8/16.
 */
public class LoginStudent {
    private int id;
    private String name;

    public LoginStudent() {
    }

    public LoginStudent(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "LoginStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
