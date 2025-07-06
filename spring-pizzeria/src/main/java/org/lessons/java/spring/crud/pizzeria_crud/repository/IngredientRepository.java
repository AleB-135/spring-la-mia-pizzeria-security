package org.lessons.java.spring.crud.pizzeria_crud.repository;

import org.lessons.java.spring.crud.pizzeria_crud.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
