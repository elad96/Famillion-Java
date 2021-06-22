package il.ac.hit.javacourse.finalproject.viewmodel;

import il.ac.hit.javacourse.finalproject.model.Category;
import il.ac.hit.javacourse.finalproject.model.Cost;
import il.ac.hit.javacourse.finalproject.model.IFamillionModel;
import il.ac.hit.javacourse.finalproject.view.IFamillionView;

public interface IFammillionViewModel {

    /**
     *  Function Description: Sets the current model used with this viewmodel
     * @param vm The model's interface
     * */
    public void setModel(IFamillionModel vm);

    /**
     *  Function Description: Get existing costs from the model, filtered by category
     *  @param category the category used to filter the results
     * */
    public void getCostByCategory(Category category) ;

    /**
     *  Function Description: Get existing costs from the model, filtered by dates
     *  @param fromDate begin date filter
     *  @param toDate end date filter
     * */
    public void getCostByDate(String fromDate, String toDate) ;

    /**
     *  Function Description: Get existing categories from the model
     * */
    public void getCategories() ;

    /**
     *  Function Description: Get existing currencies from the model
     * */
    public void getCurrencies();

    /**
     *  Function Description: Adds new cost to the model
     * @param costToAdd The cost we wish to add
     * */
    public void addNewCost(Cost costToAdd) ;

    /**
     *  Function Description: Adds new category to the model
     * @param categoryToAdd The category we wish to add
     * */
    public void addCategory(Category categoryToAdd) ;

    /**
     *  Function Description: Deletes existing category from the model
     * @param categoryToDelete The category we wish to add
     * */
    public void deleteCategory(Category categoryToDelete) ;

    /**
     * Function Description: Sets the current view interface object
     * @param viewInstance the IFamillionView object
     */
    public void setView(IFamillionView viewInstance);

}
