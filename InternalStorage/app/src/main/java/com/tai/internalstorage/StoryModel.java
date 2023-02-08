package com.tai.internalstorage;

public class StoryModel {
    private String name, content;

    public StoryModel(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "com.tai.internalstorage.Story.StoryModel{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
