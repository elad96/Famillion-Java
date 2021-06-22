package il.ac.hit.javacourse.finalproject.view;

import il.ac.hit.javacourse.finalproject.model.Category;
import il.ac.hit.javacourse.finalproject.model.Cost;
import il.ac.hit.javacourse.finalproject.model.Currency;
import il.ac.hit.javacourse.finalproject.viewmodel.IFammillionViewModel;
import java.util.List;


public interface IFamillionView {

    /**
     * Function Description: Displays pie chart of costs arraylist
     * @param costs the costs to be displayed
     * */
    public void displayPieChart(List<Cost> costs);

    /**
     * Function Description: Displays bar chart of costs arraylist
     * @param costs the costs to be displayed
     * */
    public void displayBarChart(List<Cost> costs);

    /**
     * Function Description: sets the data table with values of costs
     * @param costs the costs to be displayed
     * */
    public void setTable(List<Cost> costs);

    /**
     * Function Description: Sets the viewmodel interface attached to the view
     * @param vm The viewmodel to be set
     * */
    public void setViewModel(IFammillionViewModel vm);

    /**
     *  Function Description: Adds a new cost to the model
     * @param costToAdd the cost to be added to the model
     * */
    public void addNewCost(Cost costToAdd);

    /**
     *  Function Description: Adds a new category to the model
     * @param categoryToAdd the category to be added to the model
     * */
    public void addCategory(Category categoryToAdd);

    /**
     *  Function Description: Displays feedback message to user
     * @param messageToShow the message to be shown
     * @param status status number
     * */
    public void showFeedbackMessage(String messageToShow, int status);

    /**
     *  Function Description: Initializes the combo boxes with category arraylist
     * @param returnedCategory the categories to be set into the combo boxes
     * */
    public void initializeCategories(List<Category> returnedCategory);

    /**
     *  Function Description: Initializes the combo boxes with currency arraylist
     * @param returnedCurrencies the currencies to be set into the combo boxes
     * */
    public void initializeCurrencies(List<Currency> returnedCurrencies);

}
