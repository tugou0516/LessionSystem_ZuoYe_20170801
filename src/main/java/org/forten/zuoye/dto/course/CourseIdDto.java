package org.forten.zuoye.dto.course;

/**
 * Created by student3 on 2017/8/15.
 */
public class CourseIdDto {
    private int courseId;
    private int chooseStatus;

    public CourseIdDto(int courseId) {
        this.courseId = courseId;
    }

    public CourseIdDto(int courseId, int chooseStatus) {
        this.courseId = courseId;
        this.chooseStatus = chooseStatus;
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

    @Override
    public String toString() {
        return "CourseIdDto{" +
                "courseId=" + courseId +
                ", chooseStatus=" + chooseStatus +
                '}';
    }
}
