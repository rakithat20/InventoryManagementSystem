package src.controller;

import src.model.User;
import src.model.UserDAO;

public class AuthController {

    private UserDAO userDAO;
    private User loggedInUser ;
    public AuthController() {
        this.userDAO = new UserDAO();
    }

    public User login(String username, String password) {
        User user = userDAO.login(username, password);
        loggedInUser = user;
        return  user;
    }

    public boolean register(String username, String password) {
        User user = new User(username,password);
        System.out.println(user.getUsername());
        return userDAO.registerUser(user);
    }

    // TODO: Connect with login/register UI forms
}
