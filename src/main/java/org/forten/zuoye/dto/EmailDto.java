package org.forten.zuoye.dto;

/**
 * Created by student5 on 2017/8/22.
 */
public class EmailDto {
    private int id;
    private String title;
    private String test;

    public EmailDto() {
    }

    public EmailDto(int id, String title, String test) {
        this.id = id;
        this.title = title;
        this.test = test;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
    @Override
    public String toString() {
        return "EmailDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", test='" + test + '\'' +
                '}';
    }
}
