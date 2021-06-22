package modeltests;

import il.ac.hit.javacourse.finalproject.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class FamillionModelTest{

    FamillionModel testFammillionModel = new FamillionModel();

    @Before
    public void setUp() throws FamillionException {
        testFammillionModel.clearTablesForTesting();
    }

    @After
    public void tearDown() throws FamillionException {

    }

    @Test
    public void addCategory() throws FamillionException {
        List<Category> categories;
        categories =testFammillionModel.getCategories();
        assertEquals(categories.size(),0);
        testFammillionModel.addCategory(new Category("Test_Category"));
        categories = testFammillionModel.getCategories();
        assertEquals(categories.size(),1);
        assertEquals(categories.get(0).getName(),"Test_Category");
    }

    @Test
    public void deleteCategory() throws FamillionException {
        testFammillionModel.addCategory(new Category("Test_Category"));
        List<Category> categories =testFammillionModel.getCategories();
        assertEquals(categories.size(),1);
        testFammillionModel.deleteCategory(new Category("Test_Category"));
        categories =testFammillionModel.getCategories();
        assertEquals(categories.size(),0);
    }

    @Test
    public void addCost() throws FamillionException {
        Cost cost = new Cost(1L,new Category("u"), Currency.ILS,"one","2020-01-01");
        testFammillionModel.addCost(cost);
        List<Cost> costTestList = testFammillionModel.getCostsByCategory("u");
        assertEquals(cost.getSum(),costTestList.get(0).getSum());
        assertEquals(cost.getCategory().getName(),costTestList.get(0).getCategory().getName());
        assertEquals(cost.getCurrency(),costTestList.get(0).getCurrency());
        assertEquals(cost.getDescription(),costTestList.get(0).getDescription());
        assertEquals(cost.getDate(),costTestList.get(0).getDate());
        assertEquals(costTestList.size(),1);
    }

    @Test
    public void getCategories() throws FamillionException {
        testFammillionModel.addCategory(new Category("test1"));
        testFammillionModel.addCategory(new Category("test2"));
        List<Category> categories = testFammillionModel.getCategories();
        assertEquals(categories.get(0).getName(),"test1");
        assertEquals(categories.get(1).getName(),"test2");
        assertEquals(categories.size(),2);
    }

    @Test
    public void getCostsByCategory() throws FamillionException {
        Cost cost1 = new Cost(1L,new Category("u"),Currency.ILS,"one","2020-01-02");
        Cost cost2 = new Cost(2L,new Category("d"),Currency.USD,"two","2020-03-02");
        testFammillionModel.addCost(cost1);
        testFammillionModel.addCost(cost2);
        List<Cost> costTestList = testFammillionModel.getCostsByCategory("u");
        assertEquals(cost1.getSum(),costTestList.get(0).getSum());
        assertEquals(cost1.getCategory().getName(),costTestList.get(0).getCategory().getName());
        assertEquals(cost1.getCurrency(),costTestList.get(0).getCurrency());
        assertEquals(cost1.getDescription(),costTestList.get(0).getDescription());
        assertEquals(cost1.getDate(),costTestList.get(0).getDate());
        assertEquals(costTestList.size(),1);
    }

    @Test
    public void getCostsByTime() throws FamillionException {
        Cost cost1 = new Cost(1L,new Category("u"),Currency.ILS,"one","2020-01-02");
        Cost cost2 = new Cost(2L,new Category("d"),Currency.USD,"two","2020-03-02");
        testFammillionModel.addCost(cost1);
        testFammillionModel.addCost(cost2);
        List<Cost> costTestList = testFammillionModel.getCostsByTime("2020-01-01","2020-02-28");
        assertEquals(cost1.getSum(),costTestList.get(0).getSum());
        assertEquals(cost1.getCategory().getName(),costTestList.get(0).getCategory().getName());
        assertEquals(cost1.getCurrency(),costTestList.get(0).getCurrency());
        assertEquals(cost1.getDescription(),costTestList.get(0).getDescription());
        assertEquals(cost1.getDate(),costTestList.get(0).getDate());
        assertEquals(costTestList.size(),1);
    }

}
