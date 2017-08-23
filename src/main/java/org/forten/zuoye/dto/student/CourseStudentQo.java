package org.forten.zuoye.dto.student;

import org.forten.utils.system.PropertiesFileReader;

/**
 * Created by Administrator on 2017/7/4.
 */
public class CourseStudentQo {
    private int flag;
    private String loginName;
    private String name;
    private int pageNo;
    private int pageSize;

    public CourseStudentQo() {
        this.pageNo = 1;
        this.pageSize = 10;
    }

    public CourseStudentQo(int flag) {
        this.flag = flag;
    }

    public CourseStudentQo(String loginName, String name) {
        this.flag=0;
        this.loginName = loginName;
        this.name = name;
        this.pageNo = 1;
        this.pageSize = 10;
    }

    public CourseStudentQo(int flag, String loginName, String name) {
        this.flag = flag;
        this.loginName = loginName;
        this.name = name;
        this.pageNo = 1;
        this.pageSize = 10;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "CourseStudentQo{" +
                "flag=" + flag +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }

}
