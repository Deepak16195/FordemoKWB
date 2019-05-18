package com.rdave.kamwaliapplication.AdapterContent;

public class J10list_Content {
    private int jImage;
    private String jCategory;
    private String jState;
    private String jCity;
    private String jLandmark;
    private String jTime;
    private String jQualification;
    private String jName;
    private String jSalary;

    public J10list_Content(String jCategory, String jState, String jTime, String jName, String jSalary, String jCity, String jLandmark, String jQualification, int jImage) {
        this.jCategory = jCategory;
        this.jState = jState;
        this.jTime = jTime;
        this.jName = jName;
        this.jSalary = jSalary;
        this.jCity = jCity;
        this.jLandmark =jLandmark;
        this.jQualification = jQualification;
        this.jImage=jImage;
    }

    public String getjCategory() {
        return jCategory;
    }

    public String getjState() {
        return jState;
    }

    public String getjTime() {
        return jTime;
    }

    public String getjName() {
        return jName;
    }

    public String getjSalary() {
        return jSalary;
    }

    public String getjCity() {
        return jCity;
    }

    public String getjLandmark() {
        return jLandmark;
    }

    public String getjQualification() {
        return jQualification;
    }

    public int getjImage() {
        return jImage;
    }

    public void setjCategory(String jCategory) {
        this.jCategory = jCategory;
    }

    public void setjState(String jState) {
        this.jState = jState;
    }

    public void setjTime(String jTime) {
        this.jTime = jTime;
    }

    public void setjName(String jName) {
        this.jName = jName;
    }

    public void setjSalary(String jSalary) {
        this.jSalary = jSalary;
    }

    public void setjCity(String jCity) {
        this.jCity = jCity;
    }

    public void setjLandmark(String jLandmark) {
        this.jLandmark = jLandmark;
    }

    public void setjQualification(String jQualification) {
        this.jQualification = jQualification;
    }

    public void setjImage(int jImage) {
        this.jImage = jImage;
    }
}
