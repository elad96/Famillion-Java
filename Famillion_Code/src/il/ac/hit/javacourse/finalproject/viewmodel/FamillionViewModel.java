package il.ac.hit.javacourse.finalproject.viewmodel;

import il.ac.hit.javacourse.finalproject.model.*;
import il.ac.hit.javacourse.finalproject.view.IFamillionView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FamillionViewModel implements IFammillionViewModel {

    private IFamillionModel model;
    private IFamillionView view;
    private final ExecutorService pool;
    private List<Category> categories;
    private List<Currency> currencies;

    public FamillionViewModel() {
        pool = Executors.newFixedThreadPool(20);
        categories = new ArrayList<Category>();
        currencies = new ArrayList<Currency>();
    }

    /**
     * Function Description: Sets the current model used with this viewmodel
     * @param givenModel The model's interface
     * */
    @Override
    public void setModel(IFamillionModel givenModel) {
        this.model = givenModel;
    }

    /**
     * Function Description: Get existing costs from the model, filtered by category
     * @param category the category used to filter the results
     * */
    @Override
    public void getCostByCategory(Category category)  {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                List<Cost> returnedCosts = null;
                try {
                    //get cost by category and update view
                    returnedCosts = model.getCostsByCategory(category.getName());
                    view.setTable(returnedCosts);
                    view.displayPieChart(returnedCosts);
                    view.showFeedbackMessage("Get costs operation succeeded",0);
                } catch (FamillionException famillionException) {
                    view.showFeedbackMessage(famillionException.getMessage(),1);
                }
            }
        });
    }

    /**
     * Function Description: Get existing costs from the model, filtered by dates
     * @param fromDate begin date filter
     * @param toDate end date filter
     * */
    @Override
    public void getCostByDate(String fromDate, String toDate) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                //get cost by date and update view
                List<Cost> returnedCost = null;
                try {
                    returnedCost = model.getCostsByTime(fromDate, toDate);
                    view.setTable(returnedCost);
                    view.displayBarChart(returnedCost);
                    view.showFeedbackMessage("Cost by date operation ended successfully", 0);
                } catch (FamillionException famillionException) {
                    view.showFeedbackMessage(famillionException.getMessage(), 1);
                }
            }
        });

    }

    /**
     * Function Description: Get existing categories from the model
     * */
    @Override
    public void getCategories() {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //get categories and update view
                    categories =  model.getCategories();
                    view.initializeCategories(categories);
                    view.showFeedbackMessage("Categories initalized successfully",0);
                } catch (FamillionException famillionException) {
                    view.showFeedbackMessage(famillionException.getMessage(),1);
                }
            }
        });
    }

    /**
     * Function Description: Get existing currencies from the model
     * */
    @Override
    public void getCurrencies() {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //get categories and update view
                    currencies =  model.getCurrencies();
                    view.initializeCurrencies(currencies);
                    view.showFeedbackMessage("Currencies initalized successfully",0);
                } catch (FamillionException famillionException) {
                    view.showFeedbackMessage(famillionException.getMessage(),1);
                }
            }
        });
    }

    /**
     * Function Description: Adds new cost to the model
     * @param costToAdd The cost we wish to add
     * */
    @Override
    public void addNewCost(Cost costToAdd) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                    try {
                        //add new cost to the model and update view
                        model.addCost(costToAdd);
                        view.addNewCost(costToAdd);
                        view.showFeedbackMessage("Cost added successfully",0);
                    } catch (FamillionException famillionException) {
                        view.showFeedbackMessage(famillionException.getMessage(),1);
                    }
                }
        });
    }

    /**
     * Function Description: Adds new category to the model
     * @param categoryToAdd The category we wish to add
     * */
    @Override
    public void addCategory(Category categoryToAdd) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                    try {
                        //add category to the db and to the vm list
                        model.addCategory(categoryToAdd);
                        categories.add(categoryToAdd);
                        view.addCategory(categoryToAdd);
                        view.showFeedbackMessage("Category has been added successfully",0);
                    } catch (FamillionException famillionException) {
                        view.showFeedbackMessage(famillionException.getMessage(),1);
                    }
            }
        });
    }

    /**
     * Function Description: Deletes existing category from the model
     * @param categoryToDelete The category we wish to add
     * */
    @Override
    public void deleteCategory(Category categoryToDelete)  {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //delete category from the db
                    model.deleteCategory(categoryToDelete);
                    //delete category from the vm list
                    for(Category cat : categories){
                        if(categoryToDelete.equals(cat)){
                            categories.remove(cat);
                            break;
                        }
                    }
                    //repopulate categories on the view
                    view.initializeCategories(categories);
                    view.showFeedbackMessage("Category has been deleted succesfully",0);
                } catch (FamillionException famillionException) {
                    view.showFeedbackMessage(famillionException.getMessage(),1);
                }
            }
        });

    }

    /**
     * Function Description: Sets the current view interface object
     * @param viewInstance the IFamillionView object
     */
    @Override
    public void setView(IFamillionView viewInstance) {
        view = viewInstance;
    }

}

