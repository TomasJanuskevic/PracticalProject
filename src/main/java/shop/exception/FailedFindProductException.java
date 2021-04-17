package shop.exception;

public class FailedFindProductException extends Exception {
    public FailedFindProductException() {
        System.out.println("Didn't find product");
    }
}
