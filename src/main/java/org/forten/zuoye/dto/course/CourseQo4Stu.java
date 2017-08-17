package org.forten.zuoye.dto.course;

/**
 * Created by Administrator on 2017/8/17.
 */
public class CourseQo4Stu {
    private int pageNo;
    private int pageSize;

    public CourseQo4Stu() {
        this.pageNo=1;
        this.pageSize=3;
    }

    public CourseQo4Stu(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
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
        return "CourseQo4Stu{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
