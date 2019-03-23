package com.example.studyit.model;

import java.util.UUID;

public class CTask {
    private String name;
    private String date;
    private String discipline;
    private String control_point;

    private UUID status;

    public CTask(String name, String date, String discipline,String control_point, UUID status)
    {
        this.setName(name);
        this.setDate(date);
        this.setDiscipline(discipline);
        this.setPoint(control_point);
        this.setStatus(status);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoint() {
        return control_point;
    }

    public void setPoint(String control_point) {
        this.control_point = control_point;
    }

    public UUID getStatus() {
        return status;
    }

    public void setStatus(UUID status) {
        this.status = status;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
}
