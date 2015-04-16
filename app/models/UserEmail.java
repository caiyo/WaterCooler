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

@Entity
@Table(name="user_email")
public class UserEmail{
    private long id;
    
    private User user;
    
    private Community comunity;
    
    private String emailAddress;
    private Date createDate = new Date();

/*
 * GETTERS AND SETTERS
 * 
 */
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId(){
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
    @ManyToOne
    @JoinColumn(name="community_id")
    public Community getComunity() {
        return comunity;
    }
    public void setComunity(Community comunity) {
        this.comunity = comunity;
    }
    @Column(name="email_address", unique=true)
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public Date getCreateDate() {
        return createDate;
    }

    
}
