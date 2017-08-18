package org.forten.zuoye.dto;

/**
 * Created by student5 on 2017/8/16.
 */
public class LoginQo {
    private  String name;
    private String password;

    public LoginQo() {
    }

    public LoginQo(String name, String password) {
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
        return "LoginQo{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
