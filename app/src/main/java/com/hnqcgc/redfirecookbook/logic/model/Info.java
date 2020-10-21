package com.hnqcgc.redfirecookbook.logic.model;

import java.util.ArrayList;
import java.util.List;

public class Info {

    private String flavour;

    private String method;

    private String time;

    private String degree;

    public Info() {
    }

    public Info(String flavour, String method, String time, String degree) {
        this.flavour = flavour;
        this.method = method;
        this.time = time;
        this.degree = degree;
    }

    public String getFlavour() {
        return "风味：" + flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getMethod() {
        return "做法：" + method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTime() {
        return "制作时间：" + time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDegree() {
        return "制作温度：" + degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<String> asList() {
        List<String> info = new ArrayList<>();
        if (null != flavour) {
            info.add(getFlavour());
        }
        if (null != method) {
            info.add(getMethod());
        }
        if (null != time) {
            info.add(getTime());
        }
        if (null != degree) {
            info.add(getDegree());
        }
        return info;
    }

}