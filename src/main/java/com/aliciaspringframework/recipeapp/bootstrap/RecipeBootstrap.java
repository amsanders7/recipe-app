package com.aliciaspringframework.recipeapp.bootstrap;

import com.aliciaspringframework.recipeapp.models.*;
import com.aliciaspringframework.recipeapp.repositories.CategoryRepository;
import com.aliciaspringframework.recipeapp.repositories.RecipeRepository;
import com.aliciaspringframework.recipeapp.repositories.UnitOfMeasureRepository;
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

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading bootstrap data");
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if (eachUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (tablespoonUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (teaspoonUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if (dashUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if (pintUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> cupUOMOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (cupUOMOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM not found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUOMOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (americanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (mexicanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections(
                " 1. Cut avocado: remove flesh." + "\n" + "Cut the avocados in half. Remove the pit. Score the " +
                        "inside of the avocado with a blunt knife and scoop out the flesh with a spoon." + "\n" +
                        "Place in a bowl." + "\n" +
                "2. Mash the avocado flesh: " + "\n" + "Using a fork, roughly mash the avocado. (Don't overdo it! The " +
                        "guacamole should be a little chunky.)" +
                "3. Add the remaining ingredients to taste: " + "\n" + "Sprinkle with salt and lime (or lemon) juice. " +
                        "The acid in the lime juice will provide some balance to the richness of the avocado and will " +
                        "help delay the avocados from turning brown." + "\n" + "Add the chopped onion, cilantro, " +
                        "black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with " +
                        "a half of one chili pepper and add more to the guacamole to your desired degree of heat."
                        + "\n" + "Remember that much of this is done to taste because of the variability in the fresh " +
                        "ingredients. Start with this recipe and adjust to your taste." + "\n" +
                "4. Serve immediately: " + "\n" + "If making a few hours ahead, place plastic wrap on the surface of " +
                        "the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air " +
                        "causes oxidation which will turn the guacamole brown.)" + "\n" + "Garnish with slices of red " +
                        "radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your " +
                        "own homemade tortilla chips." + "\n" + "Refrigerate leftover guacamole up to 3 days." + "\n" +
                        " Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your " +
                        "guacamole, add it just before serving." +
                        "\n" + "\n" +
                        "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".25"), teaspoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(3), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("serrano (or jalapeno) chilis, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("cilantro (leaves and tender stems)", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("freshly ground black pepper", new BigDecimal(1), dashUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, chopped (optional)", new BigDecimal(".5"), eachUom));
        guacRecipe.addIngredient(new Ingredient("red radish or jicama slices for garnish (optional)", new BigDecimal(1), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        guacRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole");
        guacRecipe.setServings(4);
        guacRecipe.setSource("Simply Recipes");

        //add to return list
        recipes.add(guacRecipe);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections(
                "1. Prepare the grill: \n" +
                    "Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2. Make the marinade and coat the chicken: \n" +
                    "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic, and orange " +
                        "zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the " +
                        "bowl and toss to coat all over.\n" +
                        "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3. Grill the chicken: \n" +
                        "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the " +
                        "thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4. Thin the sour cream with milk: \n" +
                        " Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle.\n" +
                "5. Warm the tortillas: \n" +
                        "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as " +
                        "you see pockets of air start to puff up in the tortilla, turn it with tongs and heat for a " +
                        "few seconds on the other side.\n" +
                        "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5. Assemble the tacos: \n" +
                        "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with " +
                        "chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned " +
                        "sour cream. Serve with lime wedges.\n" +
                        "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled " +
                "jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot " +
                "pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and " +
                "sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the " +
                "tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("sugar", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("kosher salt", new BigDecimal(".5"), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("clove of garlic, finely chopped", new BigDecimal(1), eachUom));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("olive oil", new BigDecimal(2), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("skinless, boneless chicken thighs", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupUom));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), eachUom));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("sour cream", new BigDecimal(".5"), cupUom));
        tacosRecipe.addIngredient(new Ingredient("milk", new BigDecimal(".25"), cupUom));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(1), eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);
        return recipes;

    }
}
