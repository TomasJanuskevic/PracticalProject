package shop.exception;

public class FailedFindUserException extends Exception {
    public FailedFindUserException(String userName) {
        System.out.println("Didnt find user with " + userName + " name");
    }
}
