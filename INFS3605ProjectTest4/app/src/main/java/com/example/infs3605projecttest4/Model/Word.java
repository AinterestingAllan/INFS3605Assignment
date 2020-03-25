package com.example.infs3605projecttest4.Model;

public class Word {
    private String english;
    private int image;
    private String local;

    public Word(String english, int image, String local) {
        this.english = english;
        this.image = image;
        this.local = local;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
