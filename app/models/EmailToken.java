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
public class EmailToken{
    private long id;
    private User user;
    private String token; 
    private String emailAddress;
    private Community community;
    private Date createDate = new Date();

  /*
   *GETTERS AND SETTERS 
   * 
   */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id){
        this.id=id;
    }
    @ManyToOne
    @JoinColumn(name="user_id")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @Column(unique=true)
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    @Column(name="email_address", unique=true)
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    @ManyToOne
    @JoinColumn(name="community_id")
    public Community getCommunity() {
        return community;
    }
    public void setCommunity(Community community) {
        this.community = community;
    }
    public Date getCreateDate() {
        return createDate;
    }
    
    
}
