package models;

public class UserInfo {
    private String name;
    private int age;
    private String[] teachersName;
    private CourseInfo courseInfo;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String[] getTeachersName() {
        return teachersName;
    }
    public void setTeachersName(String[] teachersName) {
        this.teachersName = teachersName;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }
    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

}


