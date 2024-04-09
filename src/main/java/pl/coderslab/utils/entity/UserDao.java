package pl.coderslab.utils.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO users (username, email, password) VALUES (?,?,?)";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE  users SET email = ? , username = ?, password = ? WHERE id = ? ";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String READ_USERS_QUERY = "SELECT * FROM users";

    public static User create (User user){
        try(Connection connection = DbUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, hashPassword(user.getPassword()));
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                int id = (int) rs.getLong(1);
                System.out.println("Inserted ID: " + id);
                user.setId(id);
            }
            return user;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }


    }

    public static User read (int index){
        try(Connection connection = DbUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(READ_USER_QUERY);
            preparedStatement.setInt(1, index);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new User(Integer.parseInt(resultSet.getString(1)),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4) );
            }else{
                return null;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void update(User user){
        try(Connection connection = DbUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, hashPassword(user.getPassword()));
            preparedStatement.setInt(4,user.getId());
            int numberOfChangedRows = preparedStatement.executeUpdate();
            if(numberOfChangedRows <=0){
                System.out.println("Didnt able to update");
            }else {
                System.out.println("updated " + numberOfChangedRows + " row/s successfully");
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void delete (int index){
        try(Connection connection = DbUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY);
            preparedStatement.setInt(1,index);
            int numberOfRowsDeleted = preparedStatement.executeUpdate();
            if(numberOfRowsDeleted<1){
                System.out.println("Didnt delete any row");
            }else{
                System.out.println("Successfully deleted " + numberOfRowsDeleted + " row/s");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static User[] findAll (){
        try(Connection connection = DbUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(READ_USERS_QUERY);
            ResultSet resultset = preparedStatement.executeQuery();
            User[] users = {};
            while (resultset.next()){
                User user  = new User();
                user.setId(resultset.getInt("id"));
                user.setEmail(resultset.getString("email"));
                user.setUserName(resultset.getString("username"));
                user.setPassword(resultset.getString("password"));
                users =addToArray(user,users);
            }
            return users;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static String hashPassword(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    private static User[] addToArray(User u, User[] users)
    {
        User[] tempUsers = Arrays.copyOf(users,users.length+1);
        tempUsers[tempUsers.length-1] = u;
        return tempUsers;
    }

}
