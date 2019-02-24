package com.recipe.demo.bootstrap;

import com.recipe.demo.domain.*;
import com.recipe.demo.repositories.CategoryRepository;
import com.recipe.demo.repositories.RecipeRepository;
import com.recipe.demo.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading bootstrap data");
    }

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional =
                unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not found");
        }
        Optional<UnitOfMeasure> tableSpoonUomOptional =
                unitOfMeasureRepository.findByDescription("TableSpoon");
        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not found");
        }
        Optional<UnitOfMeasure> teaSpoonUomOptional =
                unitOfMeasureRepository.findByDescription("TeaSpoon");
        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not found");
        }
        Optional<UnitOfMeasure> dashUomOptional =
                unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not found");
        }
        Optional<UnitOfMeasure> pintUomOptional =
                unitOfMeasureRepository.findByDescription("Pint");
        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not found");
        }
        Optional<UnitOfMeasure> cupUomOptional =
                unitOfMeasureRepository.findByDescription("Cup");
        if(!cupUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not found");
        }

        //get Optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional =
                categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }
        Optional<Category> mexicanCategoryOptional =
                categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }
        Category mexicanCategory = mexicanCategoryOptional.get();
        Category americanCategory = americanCategoryOptional.get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Slice the avocados in half, removed the pit and skin and place in a mixing bowl. Mash the avocado with a fork and make it as chunky or smooth as you’d like." +
                "2. Add the remaining ingredients and stir together.");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Use a large knife to pulverise 1 large ripe" +
                " tomato to a pulp on a board, then tip into a bowl. Halve and stone the 3 avocados (saving a stone) and use a spoon to scoop out the flesh into the bowl with the tomato.");
        // needed for bidirectional -should be one method call

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados",
                new BigDecimal(2),eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher Salt",
                new BigDecimal(".5"),teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Fresh Lime juice or " +
                "lemon juice ",new BigDecimal(2),tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onions or " +
                "thinly sliced green onion",new BigDecimal(2),tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Serrano chiles, stems" +
                " and seeds removed, minced ",
                new BigDecimal(2),eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro",
                new BigDecimal(2),tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Freshly grated black " +
                "pepper",new BigDecimal(2),dashUom));
        guacRecipe.addIngredient(new Ingredient("Ripe tomato,seeds and" +
                " pulp removed, chopped",
                new BigDecimal(".5"),eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        //add to return list
        recipes.add(guacRecipe);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1.Cook beef in 10-inch skillet over medium" +
                " heat 8 to 10 minutes, stirring occasionally, until brown; " +
                "drain. \n" +
                "2.Stir salsa into beef. Heat to boiling, stirring " +
                "constantly; " +
                "reduce heat to medium-low. Cook 5 minutes, stirring " +
                "occasionally. Pour beef mixture into large serving bowl.\n" +
                "3.Heat taco shells as directed on package. Serve taco shells with beef mixture, lettuce, tomato and cheese.");
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this : " +
                "Everything goes better ina tortilla , usually with a healthy" +
                " dose of pickled jalapenoes. I can always sniff out a late " +
                "night ");
//        tacoNotes.setRecipe(tacosRecipe);
        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.addIngredient(new Ingredient("Ancho Chilli Powder"
                ,new BigDecimal(2),tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano"
                ,new BigDecimal(1),teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin"
                ,new BigDecimal(1),teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Sugar"
                ,new BigDecimal(1),teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Salt"
                ,new BigDecimal(".5"),teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic"
                ,new BigDecimal(1),eachUom));
        tacosRecipe.addIngredient(new Ingredient("Finally grated " +
                "orange zestr"
                ,new BigDecimal(1),tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Fresh- squeezed " +
                "orange juice"
                ,new BigDecimal(1),tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil"
                ,new BigDecimal(2),tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Boneless chicken " +
                "thighs"
                ,new BigDecimal(4),tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Small corn tortillasr"
                ,new BigDecimal(8),eachUom));
        tacosRecipe.addIngredient(new Ingredient("Packed baby argula"
                ,new BigDecimal(3),cupUom));
        tacosRecipe.addIngredient(new Ingredient("Medium ripe " +
                "avocados, slic"
                ,new BigDecimal(2),eachUom));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly " +
                "sliced"
                ,new BigDecimal(4),eachUom));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges"
                ,new BigDecimal(4),eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);


        recipes.add(tacosRecipe);

        return recipes;

    }
}
