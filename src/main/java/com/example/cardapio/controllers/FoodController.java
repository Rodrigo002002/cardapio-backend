package com.example.cardapio.controllers;

import com.example.cardapio.dtos.FoodRequestDTO;
import com.example.cardapio.dtos.FoodResponseDTO;
import com.example.cardapio.entities.Food;
import com.example.cardapio.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data) {
        Food foodData = new Food(data);
        foodRepository.save(foodData);
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll() {
        return foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
    }
}
