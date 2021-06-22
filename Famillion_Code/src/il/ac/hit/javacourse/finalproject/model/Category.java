package il.ac.hit.javacourse.finalproject.model;

import java.util.Objects;

/**
 * Class description: class which represents the cost category
 * */

public class Category {

    /** Class fields */
    private String name;

    /** Category constructor which get the category string
     * @param name represents the Category description string
     * */
    public Category(String name) {
        setName(name);
    }

    /** Name getter */
    public String getName() {
        return name;
    }

    /** Name setter */
    public void setName(String name) {
        this.name = name;
    }

    /** Overriding toString to allow to print the name directly */
    @Override
    public String toString() {
        return this.name;
    }

    /** Overriding the equals method to allow comparison by name */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name);
    }

    /** Overriding the hashCode method to get the name hash instead of the default one */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
