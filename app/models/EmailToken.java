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
import javax.persistence.NoResultException;
import javax.persistence.Table;

import play.db.jpa.JPA;
import util.Utility;

@Entity
@Table(name="email_token")
@AttributeOverride(name = "id", column = @Column(name = "email_token_id"))
public class EmailToken extends AbstractModel{
    private static final int TOKEN_SIZE=32;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(unique=true)
    private String token; 
    
    @Column(name="email_address", unique=true)
    private String emailAddress;
    
    @ManyToOne
    @JoinColumn(name="community_id")
    private Community community;
    
    private boolean validated;
    
    

  public EmailToken(User user, String emailAddress, Community community) {
        this.user = user;
        this.emailAddress = emailAddress;
        this.community = community;
        validated=false;
    }
/*
   *GETTERS AND SETTERS 
   * 
   */

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public Community getCommunity() {
        return community;
    }
    public void setCommunity(Community community) {
        this.community = community;
    }
    public boolean isValidated() {
        return validated;
    }
    public void setValidated(boolean validated) {
        this.validated = validated;
    }
    
    public boolean validateEmailToken(User loggedInUser){
        if (getUser() == loggedInUser)
            return true;
        return false;
    }
/*
 * STATIC METHODS 
 */
    public static EmailToken findUserById(long id){
        return JPA.em().find(EmailToken.class, id);
    }
    
    public static EmailToken findTokenByEmail(String emailAddress){
        EmailToken et;
        try{
            et = JPA.em().
                createQuery("from EmailToken where lower(emailAddress)=?", EmailToken.class)
                .setParameter(1, emailAddress.toLowerCase())
                .getSingleResult();
        }catch(NoResultException e){
            System.out.println("No tokens exist for given email address");
            et=null;
        }
        return et;
    }
    
    public static EmailToken createEmailToken(EmailToken emailToken){
        String token = Utility.generateHexString(TOKEN_SIZE);
        emailToken.setToken(token);
        try{
            JPA.em().persist(emailToken);
        }catch(Exception e){
            //should throw error if trying to create a token with an email address that is already existing
            e.printStackTrace();
            emailToken=null;
        }
        return emailToken;
    }

    
}
