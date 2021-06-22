package il.ac.hit.javacourse.finalproject.model;

public class FamillionException extends Exception {

    /**
     * Function Description: Instantiate custom exception with cause string
     * @param cause The exception string
     * */
    public FamillionException(String cause){
        super(cause);
    }

    /**
     * Function Description: Instantiate custom exception with cause string
     * @param cause The exception string
     * @param throwable The exception threw from the model
     * */
    public FamillionException(String cause, Throwable throwable){
        super(cause,throwable);
    }

}
