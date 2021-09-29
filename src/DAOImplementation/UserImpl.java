/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplementation;

import Utility.DB;
import Utility.Query;
import DAO.DAOInterface;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The DAO Implementation for the User Model. Handles all queries to the User table in the database.
 * @author KC Green
 */
public class UserImpl implements DAOInterface<User>{
    /**
     * An ObservableList containing all of the Users retrieved from queries.
     */
    private ObservableList<User> users = FXCollections.observableArrayList();

    @Override
    public User get(int id) {
        User user = new User();
        try{        
            String selectStatement = "SELECT * FROM users WHERE User_ID="+id;

            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();

            ps.execute();

            ResultSet rs = ps.getResultSet();
            
            while(rs.next()){
                user.setId(rs.getInt("User_ID"));
                user.setName(rs.getString("User_Name"));
                user.setPassword(rs.getString("Password"));
                user.setCreateDate(rs.getTimestamp("Create_Date").toInstant().atZone(ZoneId.systemDefault()));
                user.setCreatedBy(rs.getString("Created_By"));
                user.setLastUpdate(rs.getTimestamp("Last_Update").toInstant().atZone(ZoneId.systemDefault()));
                user.setLastUpdatedBy(rs.getString("Last_Updated_By"));
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }
    @Override
    public ObservableList<User> getAll() {
        this.users.clear();
        try{
            String selectStatement = "SELECT * FROM users;";
        
            Query.setPreparedStatement(DB.getConnection(), selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();

            ps.execute();

            ResultSet rs = ps.getResultSet();
        
            while(rs.next()){
                int userId = rs.getInt("User_ID");
                String  userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                ZonedDateTime createDate = rs.getTimestamp("Create_Date").toInstant().atZone(ZoneId.systemDefault());
                String createdBy = rs.getString("Created_By");
                ZonedDateTime lastUpdate = rs.getTimestamp("Last_Update").toInstant().atZone(ZoneId.systemDefault());
                String lastUpdatedBy = rs.getString("Last_Updated_By");
            
                User newUser = new User(userId, userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
                users.add(newUser);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return this.users;
    }
    /**
     * Not supported yet.
     * @param t The User object.
     * @return The number of rows affected by the Insert Query.
     */
    @Override
    public int create(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Not supported yet.
     * @param t The User object.
     * @return The number of rows affected by the Update Query.
     */    
    @Override
    public int update(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Not supported yet.
     * @param t The User object.
     * @return The number of rows affected by the Delete Query.
     */    
    @Override
    public int delete(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
