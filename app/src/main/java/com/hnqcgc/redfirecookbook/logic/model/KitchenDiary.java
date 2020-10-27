package com.hnqcgc.redfirecookbook.logic.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class KitchenDiary {

    private String imageUri;

    private String title;

    private String content;

    @PrimaryKey
    private long lastWriteDate;

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getLastWriteDate() {
        return lastWriteDate;
    }

    public void setLastWriteDate(long lastWriteTime) {
        this.lastWriteDate = lastWriteTime;
    }

}
