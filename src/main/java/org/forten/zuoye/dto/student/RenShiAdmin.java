package org.forten.zuoye.dto.student;

/**
 * Created by student5 on 2017/8/21.
 */
public class RenShiAdmin {
    private String name;
    private String password;

    public RenShiAdmin() {
    }

    public RenShiAdmin(String name, String password) {
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
        return "RenShiAdmin{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
