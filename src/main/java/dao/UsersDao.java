package dao;

import business.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends Dao implements UsersDaoInterface {

    public UsersDao(String dbName) {
        super(dbName);
    }

    /**
     * This method allows the Admin to view all existing Users including
     * members and admins
     *
     * @return A List containing all present Users
     */
    @Override
    public List<Users> findAllUsers() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Users> usersList = new ArrayList<>();

        try {

            con = this.getConnection();

            String query = "SELECT * FROM users ";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String postcode = rs.getString("postcode");
                int user_type = Integer.parseInt(rs.getString("user_type"));
                int disabled = Integer.parseInt(rs.getString("disabled"));
                Users us = new Users(id, name, email, username, password, phone, address, city, postcode, user_type, disabled);
                usersList.add(us);
            }
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the getAllLoansByUserID method:");
            System.err.println("\t" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.err.println("A problem occurred when closing down the getAllLoansByUserID() method");
                System.out.println(e.getMessage());
            }
        }
        return usersList;
    }

    /**
     * This method will allow the search for a user using a
     * user_id variable
     *
     * @param user_id the user ID supplied of the individual
     * @return A User Object containing the user_id
     */
    @Override
    public Users findUserByUserID(int user_id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        // In case it returns null
        Users u = null;

        try {
            con = getConnection();

            String query = "SELECT * from users where id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                u = new Users(rs.getInt("id"),
                        rs.getString("name"), rs.getString("email"), rs.getString("username"),
                        rs.getString("password"), rs.getString("phone"), rs.getString("address"),
                        rs.getString("city"), rs.getString("postcode"), rs.getInt("user-type"), rs.getInt("disable"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the findUserByUserID() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the findUserByUserID() method: " + e.getMessage());
            }
        }
        return u;
    }

    @Override
    public int userRegister(String name, String email, String username, String password, String phone, String address, String city, String postcode) {
        return 0;
    }

    @Override
    public Users checkLogin(String email, String password) {
        return null;
    }

    @Override
    public boolean usernameCheck(String username) {
        return false;
    }

    @Override
    public boolean emailCheck(String email) {
        return false;
    }

    @Override
    public int deleteUser(int user_id) {
        return 0;
    }


}
