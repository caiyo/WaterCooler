package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPA;

public class UserTest extends BaseTest {
    @Before
    public void createUser(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User.createUser(new User("caiyo", "Kyle"));
            }
        });     
    }
    
    @Test
    public void testUserCreation(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u =User.createUser(new User("test", "Kyle"));
                assertNotNull(u);        

            }
        }); 
        
    }
    @Test
    public void testUserNotCreated(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u =User.createUser(new User("caiyo", "Kyle"));
                assertNull(u);        

            }
        }); 
        
    }
    @Test
    public void testFindUserById(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u =User.findUserById(1);
                assertNotNull(u);       

            }
        });
    }
    @Test
    public void testFindUserByUsername(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u =User.findUserByUsername("caiyo");
                assertNotNull(u);
                assertEquals(u.getUsername(), "caiyo");

            }
        });
    }
    @Test
    public void testUserUpdate(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u =User.findUserById(1);
                u.setName("Shaun");
                User.updateUser(u);
                
                User updated = User.findUserById(1);
                assertNotNull(updated);
                assertEquals(updated.getName(), "Shaun");
            }
        });
    }
    @Test
    public void testUserDelete(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                
                User user = User.findUserById(1);
                User.deleteUser(user);
                assertNull(User.findUserById(1));
            }
        });
    }
}
