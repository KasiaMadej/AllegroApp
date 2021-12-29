package com.allegro.models;
import java.util.HashMap;

public class UserRepo {
    private String name;

    private Integer stargazersCount;

    private HashMap<String, Integer> languages;

    public UserRepo(String name, Integer stargazersCount, HashMap<String, Integer> languages) {
        this.name = name;
        this.stargazersCount = stargazersCount;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public HashMap<String, Integer> getLanguages() {
        return languages;
    }

    public void setLanguages(HashMap<String, Integer> languages) {
        this.languages = languages;
    }
}