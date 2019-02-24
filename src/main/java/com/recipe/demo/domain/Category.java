package com.recipe.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude={"Recipe"})
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> Recipe;

    public Set<Recipe> getRecipe() {
        return Recipe;
    }

    public void setRecipe(Set<Recipe> recipe) {
        Recipe = recipe;
    }
}
