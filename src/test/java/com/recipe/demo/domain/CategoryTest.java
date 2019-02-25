package com.recipe.demo.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }
    @Test
    public void getRecipe() {
    }

    @Test
    public void getId() {
        Long idvalue = 4L;
        category.setId(idvalue);
        assertEquals(idvalue,category.getId());

    }

    @Test
    public void getDescription() {
    }
}