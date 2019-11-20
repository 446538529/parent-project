package com.qz.example.job.model;

import javax.persistence.Id;

public class TaskModel {
    @Id
    private Integer id;
    private String name;
    private String corn;
    private String jobClassPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorn() {
        return corn;
    }

    public void setCorn(String corn) {
        this.corn = corn;
    }

    public String getJobClassPath() {
        return jobClassPath;
    }

    public void setJobClassPath(String jobClassPath) {
        this.jobClassPath = jobClassPath;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", corn='" + corn + '\'' +
                ", jobClassPath='" + jobClassPath + '\'' +
                '}';
    }
}
