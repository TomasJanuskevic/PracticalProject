package shop.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shop.dao.UserDao;
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

        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(userName, userPassword));
        transaction.commit();
        session.close();
        System.out.println("New account was created");
        UserDao userDao = new UserDao();
        return userDao.getUser(userName);
    }

    public User logIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name -> ");
        String userName = scanner.nextLine();
        UserDao userDao = new UserDao();
        User userFromDB = userDao.getUser(userName);
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
