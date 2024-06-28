package com.example.cardapio.controllers;

import com.example.cardapio.dtos.FoodRequestDTO;
import com.example.cardapio.dtos.FoodResponseDTO;
import com.example.cardapio.entities.Food;
import com.example.cardapio.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll() {
        return foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDTO> getFoodById(@PathVariable Long id) {
        Food food = foodRepository.findById(id).orElse(null);

        if (food == null) {
            return ResponseEntity.notFound().build();
        }

        FoodResponseDTO foodResponseDTO = new FoodResponseDTO(food);
        return ResponseEntity.ok(foodResponseDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data) {
        Food foodData = new Food(data);
        foodRepository.save(foodData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable Long id, @RequestBody FoodRequestDTO updatedFood) {
        Food food = foodRepository.findById(id).orElse(null);

        if (food != null) {
            food.setId(updatedFood.id());
            food.setTitle(updatedFood.title());
            food.setImage(updatedFood.image());
            food.setPrice(updatedFood.price());

            foodRepository.save(food);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable Long id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
