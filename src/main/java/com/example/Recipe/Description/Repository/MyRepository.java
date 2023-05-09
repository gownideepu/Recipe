package com.example.Recipe.Description.Repository;

import com.example.Recipe.Description.Model.MyModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyRepository extends org.springframework.data.jpa.repository.JpaRepository<MyModel,Integer>{


//    @Query(value = "SELECT instructions FROM recipe.my_model",nativeQuery = true)
//    List<String> findIngediants();
    @Query(value = "SELECT * FROM recipe.my_model where name = :name",nativeQuery = true)
   MyModel findByName(String name);
}
