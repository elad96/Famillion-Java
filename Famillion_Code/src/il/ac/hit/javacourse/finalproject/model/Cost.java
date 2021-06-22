package il.ac.hit.javacourse.finalproject.model;

import java.util.Objects;

/**
 * Class description: class which represents the client Cost
 * */

public class Cost {

    /** Cost class fields */
    private Long sum;
    private Category category;
    private Currency currency;
    private String description;
    private String date;

    /** Cost class constructor in charge of setting all the fields
     * @param sum represents the cost price
     * @param category represents the cost category
     * @param currency represents the cost currency
     * @param description represents the information given about that purchase
     * @param date represents the purchase date
     * */
    public Cost(Long sum, Category category, Currency currency, String description, String date) {
        setSum(sum);
        setCategory(category);
        setCurrency(currency);
        setDescription(description);
        setDate(date);
    }

    /** Sum getter */
    public Long getSum() {
        return sum;
    }

    /** Sum setter */
    public void setSum(Long sum){
        this.sum =sum;
    }

    /** Category getter */
    public Category getCategory() {
        return category;
    }

    /** Category setter */
    public void setCategory(Category category) {
        this.category = category;
    }

    /** Currency getter */
    public Currency getCurrency() {
        return currency;
    }

    /** Currency setter */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /** Description getter */
    public String getDescription() {
        return description;
    }

    /** Description setter */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Date getter */
    public String getDate() {
        return date;
    }

    /** Date setter */
    public void setDate(String date) {
        this.date = date;
    }

    /** Overriding toString to allow to print the name directly */
    @Override
    public String toString() {
        return "Cost{" +
                "sum=" + sum +
                ", category=" + category +
                ", currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    /** Overriding the equals method to allow comparison by name */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cost cost = (Cost) o;
        return sum.equals(cost.sum) &&
                category.equals(cost.category) &&
                currency.equals(cost.currency) &&
                description.equals(cost.description) &&
                date.equals(cost.date);
    }

    /** Overriding the hashCode method to get the name hash instead of the default one */
    @Override
    public int hashCode() {
        return Objects.hash(sum, category, currency, description, date);
    }

}
