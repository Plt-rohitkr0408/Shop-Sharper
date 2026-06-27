package com.shopsharper.auth_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequest {
    @NotBlank(message = "Category name is required")
    private String name;

    @Size(max = 500)
    private String description;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public CategoryRequest(String name, String description){
        this.name = name;
        this.description = description;
    }

    public CategoryRequest(){}
}
