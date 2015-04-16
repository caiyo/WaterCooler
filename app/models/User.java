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

import play.db.jpa.JPA;

@Entity
@Table(name="user")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends AbstractModel{

    @Column(unique=true)
    private String username;
    
    private String name;
    
    public User(){}
    public User(String username, String name){
        this.username=username;
        this.name=name;
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
    
    public static User updateUser(User updatedUser){
        JPA.em().merge(updatedUser);
        return updatedUser;
    }
    
    public static void deleteUser(User user){
        JPA.em().remove(user);
    }
    
    
    
}
