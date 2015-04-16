package models;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="community")
public class Community {
    
    private long id;
    private String communityName;
    private String emailDomain;
    private Date createDate = new Date();

/*
 * Getters and Setters
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
    @Column(name="community_name")
    public String getCommunityName() {
        return communityName;
    }
    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
    @Column(name="email_domain")
    public String getEmailDomain() {
        return emailDomain;
    }
    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }
    public Date getCreateDate() {
        return createDate;
    }

    
    
}
