package models;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import play.db.jpa.JPA;

/**
 * @author kylewong
 *
 */
@Entity
@Table(name="user")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends AbstractModel{

    @Column(unique=true)
    private String username;
    
    private String name;
    
    @JsonIgnore
    private String password;
    
    @Transient
    private String confirmPassword;
    
    @JsonIgnore
    private String salt;
    
    public User(){}
    public User(String username, String name){
        this.username=username;
        this.name=name;
    }

    
    public User(String username, String name, String password,
            String confirmPassword) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
    
/*
 * GETTERS AND SETTESR
 */
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }
    /*
 * STATIC METHODS
 */
    public static User findUserById(long id){
        return JPA.em().find(User.class, id);
    }
    
    public static User findUserByUsername(String username){
        User u;
        try{
            u = JPA.em().
                createQuery("from User where lower(username)=?",User.class)
                .setParameter(1, username.toLowerCase())
                .getSingleResult();
        }catch(NoResultException e){
            System.out.println("Username doesn't exist");
            u=null;
        }
        return u;
    }
    
    public static User createUser(User user){
        try{
            JPA.em().persist(user);
        }catch(Exception e){
            //Throws error because username is taken or duplicated id
            e.printStackTrace();
            user=null;
        }
        return user;
    }
    
    //Not necessarily needed since dirty checking is enabled by default for hibernate
    //created just in case
    public static User updateUser(User updatedUser){
        JPA.em().merge(updatedUser);
        return updatedUser;
    }
    
    public static void deleteUser(User user){
        JPA.em().remove(user);
    }
    
    
    
}
