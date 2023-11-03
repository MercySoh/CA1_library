package dao;

import business.Users;

import java.util.List;

public interface UsersDaoInterface {

    public List<Users> findAllUsers() ;
    public Users findUserByPrimaryKey(int id) ;
    public Users findUserByUsernamePassword(String uname, String pword);
    public Users findByString(String str) ;
    public int addUser(String username,String userpwd );

    public int checkLogin(String username,String userpwd );
    public int addUser(Users u) ;
    public int amendUser(Users u) ;
    public int deleteUser(int userId) ;

}
