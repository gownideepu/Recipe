package com.example.Recipe.Description.Service;

import com.example.Recipe.Description.Dto.MyDto;
import com.example.Recipe.Description.Dto.ResponseDto;
import com.example.Recipe.Description.Exception.CustomException;
import com.example.Recipe.Description.Model.MyModel;
import com.example.Recipe.Description.Repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class MyServiceImp implements MyService{
    @Autowired
    private MyRepository myRepository;
    @Override
    public ResponseDto addData(MyDto myDto) {
        MyModel Data = new MyModel(myDto);
        myRepository.save(Data);
        return new ResponseDto("The data added",Data);
    }
    @Override
    public String deleteDataById(int id) {
        myRepository.deleteById(id);
        return "Data deleted";
    }
    @Override
    public ResponseDto getDataById(int id) {
        return new ResponseDto("The data is ",myRepository.findById(id));
    }
    @Override
    public ResponseDto getAllData() {
        return new ResponseDto("The all data",myRepository.findAll());
    }
    @Override
    public ResponseDto editData(int id, MyDto mydto) {
        MyModel model=myRepository.findById(id).orElseThrow();
        model.updateDta(mydto);

        return new ResponseDto("The data edited ",myRepository.save(model));
    }

    @Override
    public ResponseDto findingredients(List<String> name) {
        List<MyModel> listRecipe=new ArrayList<>();
        List<MyModel> data=myRepository.findAll();
        for (MyModel j:data) {
            List<String> dbList=j.getIngredients();
            MyModel recipieName=j;
            boolean check=false;
            for (String x:name) {
                boolean ispresent=false;
                for (String y : dbList) {
                    if (x.equals(y)) {
                        ispresent=true;
                        check = true;
                        break;
                    }
                }if(ispresent!=true){
                    check=false;
                    break;
                }
            }if(check){
                listRecipe.add(j);
            }
            }
        return new ResponseDto("the ingredients filtered",listRecipe);
    }

    @Override
    public ResponseDto addstep(String name, String step) {
       MyModel data=myRepository.findByName(name);//.orElseThrow(() -> new CustomException("Data not found error :- "));
        if (data==null)
        {
            CustomException customException=new CustomException("The data is not present");
            return new ResponseDto("exception while fetching the data",customException);
        }
       data.getInstructions().add(step);
       return new ResponseDto("THe step added to the instructions",myRepository.save(data));
    }
    @Override
    public ResponseDto removestep(String step, String name) {
        System.out.println("----------------------------");
        MyModel data = myRepository.findByName(name);//orElseThrow(() -> new CustomException("Data not found error :- "));
//        System.out.println("data "+data);
        if (data==null)
        {
            CustomException customException=new CustomException("The data is not present");
            return new ResponseDto("exception while fetching the data",customException);
        }
        int len = 0;
        for (String j : data.getInstructions()) {
//            System.out.println("Db -"+j +"   "+" step "+step);
            if (j.equals(step)) {
                len = (data.getInstructions().indexOf(j));
            }
            }
        if(len>0) {
            data.getInstructions().remove(len);
        }
        return new ResponseDto("THe step removed to the instructions", myRepository.save(data));
        }
}

