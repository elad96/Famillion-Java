package il.ac.hit.javacourse.finalproject.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class FamillionModel implements IFamillionModel {

    private final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private final String familyDBURL = "jdbc:derby:famillion;create=true";

    /**
     * Function Description: Model constructor, creates the initial tables with test data
     */
    public FamillionModel() {
        try {
            this.dropTables();
            this.createTables();
            this.createTestData();
        } catch (FamillionException famillionException) {

        }
    }

    /**
     * Function Description: Establish Connection with the server
     */
    public Connection connect() throws FamillionException {
        Connection conn;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(familyDBURL);
        } catch (ClassNotFoundException | SQLException e) {
            throw new FamillionException(e.getMessage());
        }

        return conn;
    }

    /**
     * Function Description: Disconnects the connection with the server
     */
    public void disconnect(Connection conn) throws FamillionException {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throw new FamillionException(throwables.getMessage());
        }
    }

    /**
     * Function Description:  Sends SQL Query to the server (returns Strings)
     */
    private List<String> queryStrings(String query) throws FamillionException {
        Connection conn = connect();
        ResultSet rs = null;
        ArrayList<String> resultArray = new ArrayList<String>();
        try (Statement statement = conn.createStatement()) {
            rs = statement.executeQuery(query);
            while (rs.next()) {
                resultArray.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new FamillionException(e.getMessage());
        } finally {
            disconnect(conn);
        }

        return resultArray;
    }

    /**
     * Function Description:
     */
    private List<Cost> queryCosts(String query) throws FamillionException {
        Connection conn = connect();
        ResultSet rs = null;
        ArrayList<Cost> resultArray = new ArrayList<Cost>();
        try (Statement statement = conn.createStatement()) {
            rs = statement.executeQuery(query);
            while (rs.next()) {
                resultArray.add(new Cost(rs.getLong(1), new Category(rs.getString(2)), convertStringToCurrnecy(rs.getString(3)), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            throw new FamillionException(e.getMessage());
        } finally {
            disconnect(conn);
        }

        return resultArray;
    }

    /** Returning the corresponds enum value for the given string */
    private Currency convertStringToCurrnecy(String string) {
        Currency returnedCurrency = null;

        for(Currency currency : Currency.values()){
            if(currency.toString().equals(string)){
                returnedCurrency = currency;
            }
        }
        return returnedCurrency;
    }

    /**
     * Function Description: Sends SQL NonQuery to the server (doesn't return data)
     */
    private void nonQuery(String query) throws FamillionException {
        int queryResult;
        Connection conn = connect();
        try (Statement statement = conn.createStatement()) {
            queryResult = statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new FamillionException(e.getMessage());
        } finally {
            disconnect(conn);
        }
    }

    /**
     * Function Description: Creates the tables for the model
     */
    private void createTables() throws FamillionException {

        // Categories
        String sql1 = "CREATE TABLE Categories(\n" +
                "name VARCHAR(30) PRIMARY KEY)";
        //Costs
        String sql2 = "CREATE TABLE Costs(\n" +
                "id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),\n" +
                "total DOUBLE NOT NULL,\n" +
                "category VARCHAR(30) NOT NULL,\n" +
                "currency VARCHAR(30) NOT NULL,\n" +
                "description VARCHAR(30) NOT NULL,\n" +
                "date DATE NOT NULL)";
        nonQuery(sql1);
        nonQuery(sql2);
    }

    /**
     * Function Description: Drops all tables from the model
     */
    private void dropTables() throws FamillionException {
        // Categories
        String sql1 = "DROP TABLE Categories";
        //Costs
        String sql2 = "DROP TABLE Costs";
        nonQuery(sql1);
        nonQuery(sql2);
    }

    /**
     * Function Description: Creates example data for testing
     */
    private void createTestData() throws FamillionException {
        addCategory(new Category("Food"));
        addCategory(new Category("Gaming"));
        addCategory(new Category("Gardening"));
        addCost(new Cost(50L, new Category("Food"), Currency.ILS, "Shawarma", "2020-03-01"));
        addCost(new Cost(30L, new Category("Food"), Currency.ILS, "Sushi", "2020-03-06"));
        addCost(new Cost(40L, new Category("Food"), Currency.ILS, "Hamburger", "2020-03-04"));
        addCost(new Cost(20L, new Category("Gaming"), Currency.USD, "Warcraft", "2020-03-07"));
        addCost(new Cost(20L, new Category("Gaming"), Currency.USD, "Call of Duty", "2020-03-12"));
        addCost(new Cost(20L, new Category("Gaming"), Currency.USD, "Doom", "2020-03-06"));
        addCost(new Cost(40L, new Category("Gardening"), Currency.ILS, "Orange Tree", "2020-03-02"));
        addCost(new Cost(40L, new Category("Gardening"), Currency.ILS, "Sand", "2020-03-07"));
        addCost(new Cost(40L, new Category("Gardening"), Currency.ILS, "Tools", "2020-03-13"));
    }

    /**
     * Function Description: creates clean database for testing purposes
     *
     * @throws FamillionException
     */
    public void clearTablesForTesting() throws FamillionException {
        dropTables();
        createTables();
    }

    /**
     * Function Description: Adds new category to the model
     *
     * @param categoryToAdd The category we wish to add
     */
    @Override
    public void addCategory(Category categoryToAdd) throws FamillionException {
        checkCategory(categoryToAdd);
        String sql = String.format("INSERT INTO Categories (name)\r\n VALUES ('%s')", categoryToAdd.getName());
        nonQuery(sql);
    }

    /** Method in charge of checking the user category name*/
    private void checkCategory(Category categoryToAdd) throws FamillionException {
        if (categoryToAdd.getName().equals("")) throw new FamillionException("Category can not be empty");
    }

    /**
     * Function Description: Deletes existing category from the model
     *
     * @param category The category we wish to add
     */
    @Override
    public void deleteCategory(Category category) throws FamillionException {
        String sql = String.format("DELETE FROM Categories WHERE name = '%s'", category.getName());
        nonQuery(sql);
    }

    /**
     * Function Description: Adds new cost to the model
     *
     * @param cost The cost we wish to add
     */
    @Override
    public void addCost(Cost cost) throws FamillionException {
        checkCost(cost);
        String sql = String.format("INSERT INTO Costs (total, category, currency, description, date) VALUES (%s,'%s','%s','%s','%s')",
                cost.getSum(), cost.getCategory(), cost.getCurrency(), cost.getDescription()
                , cost.getDate());
        nonQuery(sql);
    }

    /** Method in charge of checking and validating the cost */
    private void checkCost(Cost cost) throws FamillionException{
        if(cost.getSum() < 0) throw new FamillionException("Cost sum must be non negative number");
        if(cost.getCategory().getName().equals("")) throw new FamillionException("Category can not be empty");
        if(cost.getDescription().equals("")) throw new FamillionException("Description can not be empty");
        if(!checkDate(cost.getDate())) throw new FamillionException("Date format must be yyyy-mm-dd");
    }

    /**
     * Function Description: checks if given date is valid
     * @param date the date to be checked
     * @return boolean - true if correct, false if incorrect
     */
    private boolean checkDate(String date){
        if(date.length()==10 && date.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d"))
            return true;
        else
            return false;
    }

    /**
     * Function Description: Get existing categories from the model
     *
     * @return ArrayList of Categories
     */
    @Override
    public List<Category> getCategories() throws FamillionException {
        List<String> categoriesStrings;
        List<Category> categories = new ArrayList<Category>();
        String sql = String.format("SELECT name FROM Categories");
        categoriesStrings = queryStrings(sql);
        for (String categoryString : categoriesStrings) {
            categories.add(new Category(categoryString));
        }

        return categories;
    }

    /**
     * Function Description: Get existing Currencies from the model
     *
     * @return ArrayList of Currnecies
     */
    @Override
    public List<Currency> getCurrencies() throws FamillionException{
        ArrayList<Currency> currencies = new ArrayList<Currency>(Arrays.asList(Currency.values()));
        if(currencies.size() == 0){
            throw new FamillionException("No currencies exists");
        }
        return currencies;
    }

    /**
     * Function Description: Get existing costs from the model, filtered by category
     *
     * @param category the category used to filter the results
     * @return ArrayList of Costs
     */
    @Override
    public List<Cost> getCostsByCategory(String category) throws FamillionException {
        List<Cost> costs;
        String sql = String.format("SELECT total,category,currency,description,date FROM Costs WHERE category='%s'", category);
        costs = queryCosts(sql);

        return costs;
    }

    /**
     * Function Description: Get existing costs from the model, filtered by time
     *
     * @param date1 begin date filter
     * @param date2 end date filter
     * @return ArrayList of Costs
     */
    @Override
    public List<Cost> getCostsByTime(String date1, String date2) throws FamillionException {
        List<Cost> costs;
        String sql = String.format("SELECT total,category,currency,description,date FROM Costs WHERE Date BETWEEN '%s' AND '%s'", date1, date2);
        costs = queryCosts(sql);

        return costs;
    }

}