package dao;

import business.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends Dao implements UsersDaoInterface {


        public List<Users> findAllUsers() {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            List<Users> userslist = new ArrayList<Users>();
            try {
                con = this.getConnection();

                String query = "SELECT * FROM users";
                ps = con.prepareStatement(query);

                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    int phone = Integer.parseInt(rs.getString("phone"));
                    String address = rs.getString("address");
                    String city = rs.getString("city");
                    String postcode = rs.getString("postcode");
                    int user_type = Integer.parseInt(rs.getString("user_type"));
                    int disabled = Integer.parseInt(rs.getString("disabled"));
                    Users us = new Users( name, email, username, password, phone, address, city, postcode,user_type,disabled);
                    userslist.add(us);
                }
            } catch (SQLException e) {
                //throw new DaoException("findAllUsers() " + e.getMessage());
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
                   // throw new DaoException(e.getMessage());
                }
            }
            return userslist;     // may be empty
        }
    public Users findUserByUsernamePassword(String uname, String pword) throws DaoException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users us = null;
        try {
            con = this.getConnection();

            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, uname);
            ps.setString(2, pword);

            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int phone = Integer.parseInt(rs.getString("phone"));
                String address = rs.getString("address");
                String city = rs.getString("city");
                String postcode = rs.getString("postcode");
                int user_type = Integer.parseInt(rs.getString("user_type"));
                int disabled = Integer.parseInt(rs.getString("disabled"));
                 us = new Users( name, email, username, password, phone, address, city, postcode,user_type,disabled);

            }
        } catch (SQLException e) {
            //throw new DaoException("findPlayerByUsernamePassword " + e.getMessage());
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
                //throw new DaoException("findPlayerByUsernamePassword" + e.getMessage());
            }
        }
        return us;     // p may be null
    }
    /*
     * Primary key is inserted automatically by DBMS
     */
    public int addUser(User c) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        try {
            con = getConnection();
            /*
             * Ensure symbol does not already exist
             */
            String query = "SELECT SYMBOL FROM users WHERE SYMBOL = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, c.getSymbol()); c.

            rs = ps.executeQuery();
            if (rs.next()) {
                //throw new DaoException("Symbol " + c.getSymbol() + " already exists");
            }

            String command = "INSERT INTO COMPANY (SYMBOL, COMPANYNAME, SHAREPRICE, HIGH, LOW) VALUES(?, ?, ?, ?, ?)";
            ps = con.prepareStatement(command);
            ps.setString(1, c.getSymbol());
            ps.setString(2, c.getCompanyName());
            ps.setDouble(3, c.getSharePrice());
            ps.setDouble(4, c.getHigh());
            ps.setDouble(5, c.getLow());

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("addCompany: " + e.getMessage());
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
                throw new DaoException("addCompany(): " + e.getMessage());
            }
        }
        return rowsAffected;
    }


    /*Override
        public int register(String name, String email, String username, String password,
                            int phone, String address, String city, String postcode,
                            int user_type, int disabled)
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            Users us = null;
            try {
                con = this.getConnection();

                //String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                String query = "INSERT INTO users (id, name, email, username, password, phone, address, city, postcode, user_type, disable) " +
                        "VALUES( name, email, username, password,  phone, address, city, postcode, user_type, disabled);

                ps = con.prepareStatement(query);
                ps.setString(1, uname);
                ps.setString(2, pword);

                rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    int phone = Integer.parseInt(rs.getString("phone"));
                    String address = rs.getString("address");
                    String city = rs.getString("city");
                    String postcode = rs.getString("postcode");
                    int user_type = Integer.parseInt(rs.getString("user_type"));
                    int disabled = Integer.parseInt(rs.getString("disabled"));
                    us = new Users( name, email, username, password, phone, address, city, postcode,user_type,disabled);

                }
            } catch (SQLException e) {
                //throw new DaoException("findPlayerByUsernamePassword " + e.getMessage());
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
                    //throw new DaoException("findPlayerByUsernamePassword" + e.getMessage());
                }
            }
            return 1;     // p may be null

            return 0;
        }*/

}
