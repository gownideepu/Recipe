package com.example.Recipe.Description.Service;

import com.example.Recipe.Description.Dto.MyDto;
import com.example.Recipe.Description.Dto.ResponseDto;

import java.util.List;

public interface MyService {
    ResponseDto addData(MyDto myDto);

    String deleteDataById(int id);

    ResponseDto getDataById(int id);

    ResponseDto getAllData();

    ResponseDto editData(int id, MyDto mydto);

    ResponseDto findingredients(List<String> recipeName);

    ResponseDto addstep(String name, String step);

    ResponseDto removestep(String step,String name);
}
   // Add a step to a recipe:
       // Remove a step from a recipe: