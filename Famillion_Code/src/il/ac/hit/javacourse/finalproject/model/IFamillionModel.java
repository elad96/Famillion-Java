package il.ac.hit.javacourse.finalproject.model;

import java.util.List;

public interface IFamillionModel {

    /**
     * Function Description: Adds new category to the model
     * @param categoryToAdd The category we wish to add
     * */
    public void addCategory(Category categoryToAdd) throws FamillionException;

    /**
     * Function Description: Deletes existing category from the model
     * @param category The category we wish to add
     * */
    public void deleteCategory(Category category) throws FamillionException;

    /**
     * Function Description: Adds new cost to the model
     * @param cost The cost we wish to add
     * */
    public void addCost(Cost cost) throws FamillionException;

    /**
     * Function Description: Get existing categories from the model
     * @return ArrayList of Categories
     * */
    public List<Category> getCategories() throws FamillionException;

    /**
     * Function Description: Get existing Currencies from the model
     * @return ArrayList of Currencies
     * */
    public List<Currency> getCurrencies() throws FamillionException;

    /**
     * Function Description: Get existing costs from the model, filtered by category
     * @param category the category used to filter the results
     * @return ArrayList of Costs
     * */
    public List<Cost> getCostsByCategory(String category) throws FamillionException;

    /**
     * Function Description: Get existing costs from the model, filtered by time
     * @param date1 begin date filter
     * @param date2 end date filter
     * @return ArrayList of Costs
     * */
    public List<Cost> getCostsByTime(String date1, String date2) throws FamillionException;

}
