package com.example.Recipe.Description.Dto;

import lombok.Data;
import java.util.List;

@Data
public class MyDto {
    private String name;
    private String description;
    private List<String> ingredients;
    private List<String> instructions;
}

