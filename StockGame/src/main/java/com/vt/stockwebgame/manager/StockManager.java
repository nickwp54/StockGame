/*
 * Created by Nicholas Phillpott on 2016.04.24  * 
 * Copyright © 2016 Nicholas Phillpott. All rights reserved. * 
 */
package com.vt.stockwebgame.manager;

import com.vt.stockwebgame.domains.*;
import java.io.Serializable;
import java.util.ArrayList;
import com.vt.stockwebgame.domains.User;
import com.vt.stockwebgame.helpers.StockLookup;
import java.util.Collections;

/**
 *
 * @author painter
 */
public class StockManager implements Serializable {
    
    private User user;
    private String username; 
    private String password; 
    private ArrayList<User> ActiveUsers; 
    private String statusMessage;
    
    public StockManager() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<User> getActiveUsers() {
        return ActiveUsers;
    }

    public void setActiveUsers(ArrayList<User> ActiveUsers) {
        this.ActiveUsers = ActiveUsers;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    public String createUser() {
        ActiveUsers.add(user);
        return ""; 
    }
    
    public float getStockPrice(String symbol) {
        Stock s = StockLookup.loadStock(symbol);
        if (s == null) {
            return 0;
        }
        else {
            return s.getPrice();
        }
    }
    
    public String getStockData(String symbol) {
        StockChart s = StockLookup.loadStockChart(symbol);
        if (s == null) {
            return "";
        }
        else {
            return s.getChartJSON();
        }
    }
    
    public boolean checkStockExists(String symbol) {
        return StockLookup.loadStock(symbol) == null;
    }
    
    public ArrayList<User> getLeaderboard() {
        ArrayList<User> leaderboard = new ArrayList<User>(ActiveUsers);
        Collections.sort(leaderboard);
        return leaderboard;
    }
    
    //--------------------------------------------------------------------------
    
    public String loginUser() {
        
        for (User u : ActiveUsers) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                user = u; 
            }
            else {
                statusMessage = "Username of Password Wrong";
                return "";
            }
        }
        return "Account";
    }
    
    public String logout() {
        user = null; 
        return "";
    }
    
    public String prepareCreateUser() {
        user = new User(); 
        return "CreateUser";
    }
}