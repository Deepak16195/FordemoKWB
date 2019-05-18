package com.rdave.kamwaliapplication.AdapterContent;

public class CL_Content {

    private int hImg;
    private String hNaMe;
    private String hProfiLe;
    private String hExprience;
    private String hlocate;

    public CL_Content(int hImg, String hNaMe, String hProfiLe, String hExprience, String hlocate) {
        this.hImg = hImg;
        this.hNaMe = hNaMe;
        this.hProfiLe = hProfiLe;
        this.hExprience = hExprience;
        this.hlocate = hlocate;
    }

    public int gethImg() {
        return hImg;
    }

    public String gethNaMe() {
        return hNaMe;
    }

    public String gethProfiLe() {
        return hProfiLe;
    }

    public String gethExprience() {
        return hExprience;
    }

    public String getHlocate() {
        return hlocate;
    }

    public void sethImg(int hImg) {
        this.hImg = hImg;
    }

    public void sethNaMe(String hNaMe) {
        this.hNaMe = hNaMe;
    }

    public void sethProfiLe(String hProfiLe) {
        this.hProfiLe = hProfiLe;
    }

    public void sethExprience(String hExprience) {
        this.hExprience = hExprience;
    }

    public void setHlocate(String hlocate) {
        this.hlocate = hlocate;
    }
}
