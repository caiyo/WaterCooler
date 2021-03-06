package models;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.db.jpa.JPA;

@Entity
@Table(name="user_email")
@AttributeOverride(name = "id", column = @Column(name = "user_email_id"))
public class UserEmail extends AbstractModel{
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name="community_id")
    private Community comunity;
    
    @Column(name="email_address", unique=true)
    private String emailAddress;
    
    public UserEmail(User user, Community comunity, String emailAddress) {
        this.user = user;
        this.comunity = comunity;
        this.emailAddress = emailAddress;
    } 

/*
 * GETTERS AND SETTERS
 * 
 */
    
    
   
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Community getComunity() {
        return comunity;
    }
    public void setComunity(Community comunity) {
        this.comunity = comunity;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

/*
 * STATIC METHODS
 */
    public static UserEmail createUserEmail(UserEmail userEmail){
        try{
            JPA.em().persist(userEmail);
        }catch(Exception e){
            e.printStackTrace();
            userEmail=null;
        }
        return userEmail;
    }
    
    
}   
