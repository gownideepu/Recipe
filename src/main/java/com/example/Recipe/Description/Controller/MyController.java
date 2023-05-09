package com.example.Recipe.Description.Controller;

import com.example.Recipe.Description.Dto.MyDto;
import com.example.Recipe.Description.Dto.ResponseDto;
import com.example.Recipe.Description.Service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class  MyController {


    /**
     *  Find the recipe
     *  Based on the ingredients
     *  Add a step to a recipe
     *  Remove a step  from recipe
     */

    @Autowired
    private MyService myService;
    @PostMapping("/add")
    public ResponseDto addData(@RequestBody MyDto myDto){
        return myService.addData(myDto);
    }
    @PutMapping("/edit/{id}")
    public ResponseDto editData(@PathVariable int id,@RequestBody MyDto mydto){
        return myService.editData(id,mydto);
    }
    @GetMapping("/")
    public ResponseDto getAllData(){
        return myService.getAllData();
    }
    @GetMapping("/get/{id}")
    public ResponseDto getData(@PathVariable int id){
        return myService.getDataById(id);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return myService.deleteDataById(id);
    }
    @GetMapping("/filter")
    public ResponseDto filtering(@RequestParam List<String> name){
        return myService.findingredients(name);
    }
    @PutMapping("/addstep")
    public ResponseDto addstep(@RequestParam String name,@RequestParam String step){
        return myService.addstep(name,step);
    }
    @PutMapping("/removestep")
    public ResponseDto removestep(@RequestParam String step,@RequestParam String name){
        return myService.removestep(step,name);
    }
}
