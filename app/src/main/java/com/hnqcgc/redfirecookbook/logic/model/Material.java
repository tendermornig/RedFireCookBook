package com.hnqcgc.redfirecookbook.logic.model;

import java.util.List;

public class Material {

    private String name;

    private String num;

    public Material(String name, String num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

}
