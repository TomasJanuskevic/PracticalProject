package shop.exception;

public class ExistingUserException extends Exception {
    public ExistingUserException(String userName){
        System.out.println("User with " + userName + " already exist");
    }
}
