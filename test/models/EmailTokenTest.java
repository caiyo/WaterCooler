package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPA;

public class EmailTokenTest extends BaseTest{
    @Before
    public void createUser(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User.createUser(new User("caiyo"));
            }
        });     
    }
    
    @Test
    public void testEmailTokenCreation(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                
                EmailToken et = new EmailToken(User.findUserById(1), "test@google.com", Community.findCommunityByEmail("google.com"));
                assertNotNull(EmailToken.createEmailToken(et));
            }
        });   
    }
    
    @Test
    public void testEmailTokenCreationFail(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                
                EmailToken et = new EmailToken(User.findUserById(1), "test@google.com", Community.findCommunityByEmail("google.com"));
                EmailToken et1 = new EmailToken(User.findUserById(1), "test@google.com", Community.findCommunityByEmail("google.com"));
                
                EmailToken.createEmailToken(et);
                assertNull(EmailToken.createEmailToken(et1));
            }
        });
        
    }
    
    @Test
    public void testEmailTokenValidation(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u = User.findUserById(1);
                EmailToken et = new EmailToken(u, "test@google.com", Community.findCommunityByEmail("google.com"));
                EmailToken.createEmailToken(et);
                
                assertTrue(EmailToken.validateEmailToken(u, et));
                assertTrue(EmailToken.validateEmailToken(u, EmailToken.findUserById(1)));
            }
        });
        
    }
    
    
    
}
