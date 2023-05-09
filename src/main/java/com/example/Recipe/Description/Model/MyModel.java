package com.example.Recipe.Description.Model;

import com.example.Recipe.Description.Dto.MyDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
public class MyModel {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;

//    @ElementCollection
////    @CollectionTable(name = "ingredients", joinColumns = @JoinColumn(name="id"))
//    @Column(name = "ingredients")

    @ElementCollection
    @CollectionTable(name="ingredients",joinColumns = @JoinColumn(name = "id"))
    private List<String> ingredients;
    @ElementCollection
    @CollectionTable(name="instructions",joinColumns = @JoinColumn(name = "id"))
    private List<String> instructions;
    public void updateDta(MyDto myDto) {
        this.name=myDto.getName();
        this.description=myDto.getDescription();
        this.ingredients=myDto.getIngredients();
        this.instructions=myDto.getInstructions();
    }
    public MyModel(MyDto myDto){
        this.updateDta(myDto);
    }

}
