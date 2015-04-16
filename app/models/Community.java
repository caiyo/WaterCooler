package models;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityExistsException;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Table;

import play.db.jpa.JPA;

@Entity
@Table(name="community")
@AttributeOverride(name = "id", column = @Column(name = "community_id"))
public class Community extends AbstractModel {
        
    @Column(name="community_name", unique=true)
    private String communityName;
    
    @Column(name="email_domain", unique=true)
    private String emailDomain;

/*
 * Getters and Setters
 * 
 */
    public String getCommunityName() {
        return communityName;
    }
    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
    public String getEmailDomain() {
        return emailDomain;
    }
    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }
/*
 * STATIC Methods
 */
    public static Community findCommunityById(long id){
        return JPA.em().find(Community.class, id);
    }
    
    public static Community findCommunityByName(String communityName){
        Community com;
        try{
            com=JPA.em()
                    .createQuery("from Community where lower(communityName)=?", Community.class)
                    .setParameter(0, communityName.toLowerCase())
                    .getSingleResult();
        }catch(NoResultException e){
            com=null;
        }
        return com;
    }
    
    public static Community findCommunityByEmail(String emailDomain){
        Community com;
        try{
            com=JPA.em()
                    .createQuery("from Community where lower(emailDomain)=?", Community.class)
                    .setParameter(0, emailDomain.toLowerCase())
                    .getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
            com=null;
        }
        return com;
    }
    
    public static Community createCommunity(Community community){

        JPA.em().persist(community); 
        return community;
    }
    
    public static Community updateCommunity(Community updatedCommunity){
      JPA.em().merge(updatedCommunity);
      return updatedCommunity;
    }
    
    public static void deleteCommunity(Community community){
        JPA.em().remove(community);
    }
    
}
