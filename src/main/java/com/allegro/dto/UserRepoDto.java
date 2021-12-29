package com.allegro.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class UserRepoDto {

    @JsonProperty("name")
    private String name;
    @JsonProperty("stargazers_count")
    private Integer stargazersCount;
    @JsonProperty("languages_url")
    private String languageUrl;

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

    public String getLanguageUrl() {
        return languageUrl;
    }

    public void setLanguageUrl(String languageUrl) {
        this.languageUrl = languageUrl;
    }
}
