package dao;

import business.Users;

import java.util.List;

/**
 * @author Conor,Julie
 */
public interface UsersDaoInterface {

    /**
     * This method allows the Admin to view all existing Users including
     * members and admins
     *
     * @return A List containing all present Users
     */
    public List<Users> findAllUsers();

    /**
     * This method will allow the search for a user using a
     * user_id variable
     *
     * @param user_id the user ID supplied of the individual
     *
     * @return A User Object containing the user_id
     */
    public Users findUserByUserID(int user_id);

    /**
     * This method will the User to register to the library
     * application, using the INSERT query and the users id will
     * be returned indicating if successful or not
     *
     * @param name the users name
     * @param email the users email
     * @param username the users unique username
     * @param password the users password which will be hashed and salted
     * @param phone the users phone number
     * @param address the users address
     * @param city the users city
     * @param postcode the users postcode
     *
     * @return the Users unique id which will indicate that registration is successful
     */
    public int userRegister(String name, String email, String username, String password, String phone, String address, String city, String postcode);

    /**
     * This method will allow the user to login to the application
     *
     * @param email the user ID supplied of the individual
     * @param password the user ID supplied of the individual
     *
     * @return A Users Object indicating that the user has logged on
     * it would be null if they weren't able to
     */
    public Users checkLogin(String email, String password);

    /**
     * This method will ensure if the username supplied is
     * present or not
     *
     * @param username the users username unique to them
     *
     * @return true/false, depending on if the username is present or
     * not
     */
    public boolean usernameCheck(String username);

    /**
     * This method will ensure if the email supplied is
     * present or not
     *
     * @param email the users email unique to them
     *
     * @return true/false, depending on if the email is present or
     * not
     */
    public boolean emailCheck(String email);

    /**
     * This method will allow a User Object to be deleted using
     * the user_id
     *
     * @param user_id the user ID supplied of the individual
     * to be deleted
     *
     * @return the row affected whether it was deleted or not
     */
    public int deleteUser(int user_id);

}
