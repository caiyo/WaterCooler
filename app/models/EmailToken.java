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

@Entity
@Table(name="email_token")
@AttributeOverride(name = "id", column = @Column(name = "email_token_id"))
public class EmailToken extends AbstractModel{

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
 
}
