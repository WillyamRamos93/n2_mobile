package com.ritter.cursoextensao;

public class CourseModel {

    private int id;
    private String name;
    private String session;
    private String weekDay;
    private String description;

    public CourseModel(int id, String name, String session, String weekDay, String description) {
        this.id = id;
        this.name = name;
        this.session = session;
        this.weekDay = weekDay;
        this.description = description;
    }

    public CourseModel() {
    }


    //toString for printing the contents


    @Override
    public String toString() {
        return "courseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", session='" + session + '\'' +
                ", weekDay='" + weekDay + '\'' +
                ", description='" + description + '\'' +
                '}';
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

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
