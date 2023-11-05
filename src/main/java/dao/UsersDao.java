package dao;

import business.Users;
import exceptions.DuplicationEmailException;
import exceptions.DuplicationUsernameException;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Julie, Conor
 */
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


    /**
     * This method will the User to register to the library
     * application, using the INSERT query and the users id will
     * be returned indicating if successful or not
     *
     * @param name     the users name
     * @param email    the users email
     * @param username the users unique username
     * @param password the users password which will be hashed and salted
     * @param phone    the users phone number
     * @param address  the users address
     * @param city     the users city
     * @param postcode the users postcode
     * @return the Users unique id which will indicate that registration is successful
     */
    @Override
    public int userRegister(String name, String email, String username, String password, String phone, String address, String city, String postcode) {

        if (emailCheck(email)) {
            throw new DuplicationEmailException("The Email Already exists");
        }
        if (usernameCheck(username)) {
            throw new DuplicationUsernameException("The username already exists");
        }

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int newId = -1;

        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try {
            con = this.getConnection();

            String query = "INSERT INTO users(name, email, username, password,phone,address,city,postcode) VALUES (?, ?, ?, ?,?,?,?,?)";

            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, username);
            ps.setString(4, hashPassword);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setString(7, city);
            ps.setString(8, postcode);

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {

                newId = rs.getInt(1);

            }
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the addStockItem method:");
            System.err.println("\t" + e.getMessage());
            newId = -1;
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
                System.err.println("A problem occurred when closing down the addStockItem method:\n" + e.getMessage());
            }
        }
        return newId;
    }

    /**
     * This method will allow the user to log in to the application
     *
     * @param email    the user ID supplied of the individual
     * @param password the user ID supplied of the individual
     * @return A Users Object indicating that the user has logged on
     * it would be null if they weren't able to
     */
    @Override
    public Users checkLogin(String email, String password) {
        Users u = null;
        if (!emailCheck(email)) {

            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                con = this.getConnection();

                String query = "SELECT * FROM users where email=?";

                ps = con.prepareStatement(query);

                rs = ps.executeQuery();

                if (rs.next()) {
                    String hashPassword = rs.getString("password");
                    boolean isPresent = BCrypt.checkpw(password, hashPassword);
                    if (isPresent) {

                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String username = rs.getString("username");
                        String storedEmail = rs.getString("email");
                        String address = rs.getString("address");
                        String phone = rs.getString("phone");
                        String city = rs.getString("city");
                        String postcode = rs.getString("postcode");
                        int disabled = rs.getInt("disable");
                        int user_type = rs.getInt("user-type");

                        u = new Users(id, name, storedEmail, username, null, phone, address, city, postcode, user_type, disabled);

                    } else {
                        u = null;
                    }

                }
            } catch (SQLException e) {
                System.err.println("\tA problem occurred during the checkLogin method:");
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
                    System.err.println("A problem occurred when closing down the checkLogin method:\n" + e.getMessage());
                }
            }
        }
        return u;
    }

    /**
     * This method will ensure if the username supplied is
     * present or not
     *
     * @param username the users username unique to them
     * @return true/false, depending on if the username is present or
     * not
     */
    @Override
    public boolean usernameCheck(String username) {
        boolean isPresent = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = this.getConnection();
            String query = "SELECT * from users where username=? ";
            ps = con.prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                isPresent = count > 0;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("	A problem occurred during the duplicateUsername method:");
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
                System.err.println("A problem occurred when closing down the duplicateUsername() method");
                System.out.println(e.getMessage());
            }
        }
        return isPresent;
    }

    /**
     * This method will ensure if the email supplied is
     * present or not
     *
     * @param email the users email unique to them
     *
     * @return true/false, depending on if the email is present or
     * not
     */
    @Override
    public boolean emailCheck(String email) {
        boolean isPresent = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = this.getConnection();
            String query = "SELECT * from users where email=? ";
            ps = con.prepareStatement(query);
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                isPresent = count > 0;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("	A problem occurred during the duplicateEmail method:");
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
                System.err.println("A problem occurred when closing down the duplicateEmail() method");
                System.out.println(e.getMessage());
            }
        }
        return isPresent;
    }


    /**
     * This method will allow a User Object to be deleted using
     * the user_id
     *
     * @param user_id the user ID supplied of the individual
     * to be deleted
     *
     * @return the row affected whether it was deleted or not
     */
    @Override
    public int deleteUser(int user_id) {
        Connection con = null;
        PreparedStatement ps = null;
        int rows = 0;

        try {
            con = this.getConnection();

            String query = "DELETE FROM users WHERE user_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);

            rows = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the deleteUser method:");
            System.err.println("\t" + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.err.println("A problem occurred when closing down the deleteUser method:\n" + e.getMessage());
            }
        }

        if (rows == 0) {
            System.err.println("No user_id :" + user_id + "found");

        }

        return rows;
    }


}
