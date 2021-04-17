package shop.service;

import shop.dao.UserDao;
import shop.exception.ExistingUserException;
import shop.exception.FailedFindUserException;
import shop.model.User;
import shop.utils.DatabaseUtils;

import java.util.Scanner;

public class UserService {
    public void deleteAccount(User user) {
        UserDao userDao = new UserDao();
        userDao.deleteUser(user);
        DatabaseUtils.shutDown();
        System.out.println("Goodbye");
    }

    public User createAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name -> ");
        String userName = scanner.nextLine();
        System.out.print("Enter password -> ");
        String userPassword = scanner.nextLine();

        try {
            User user = new User(userName, userPassword);
            UserDao userDao = new UserDao();
            userDao.saveUser(user);
            System.out.println("New account was created");
            return userDao.getUser(userName);
        } catch (ExistingUserException | FailedFindUserException e){
            e.printStackTrace();
            return null;
        }
    }

    public User logIn() {
        User userFromDB;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name -> ");
        String userName = scanner.nextLine();
        UserDao userDao = new UserDao();

        try {
            userFromDB = userDao.getUser(userName);
        } catch (FailedFindUserException e) {
            e.printStackTrace();
            return null;
        }

        System.out.print("Enter password -> ");
        String userPassword = scanner.nextLine();

        if (userPassword.equals(userFromDB.getPassword())) {
            System.out.println("Welcome " + userFromDB.getName());
            return userFromDB;
        } else {
            System.out.println("Wrong password");
        }
        return null;
    }
}
