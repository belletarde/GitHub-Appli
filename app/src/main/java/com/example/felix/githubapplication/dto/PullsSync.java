package com.example.felix.githubapplication.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class PullsSync {
    private String title, created_at, body, html_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setUrl(String html_url) {
        this.html_url = html_url;
    }

    @Override
    public String toString() {
        return getBody() + " " + getTitle();
    }
}
