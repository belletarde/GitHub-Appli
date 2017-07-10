package com.example.felix.githubapplication.dto;

import com.example.felix.githubapplication.modelo.Items;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositorySync {
    private List<Items> Items;

    public List<Items> getItems() {
            return Items;
    }

    public void setItems(List<Items> Items) {
        this.Items = Items;
    }

}

