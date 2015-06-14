package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPA;

public class UserTest extends BaseTest {
    @Before
    public void createUser(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User.createUser(new User("caiyo", "", ""));
            }
        });     
    }
    
    @Test
    public void testUserCreation() {
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u =User.createUser(new User("test"));
                assertNotNull(u);        

            }
        }); 
        
    }
    @Test
    public void testUserNotCreated() {
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u = User.createUser(new User("caiyo"));
                assertNull(u);        
            }
        }); 
        
    }
    @Test
    public void testFindUserById() {
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u = User.findUserById(1);
                assertNotNull(u);       

            }
        });
    }
    @Test
    public void testFindUserByUsername() {
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u = User.findUserByUsername("caiyo");
                assertNotNull(u);
                assertEquals(u.getUsername(), "caiyo");

            }
        });
    }
    @Test
    public void testUserUpdate() {
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u = User.findUserById(1);
                User.updateUser(u);
                
                User updated = User.findUserById(1);
                assertNotNull(updated);
            }
        });
    }
    @Test
    public void testUserDelete() {
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                
                User user = User.findUserById(1);
                User.deleteUser(user);
                assertNull(User.findUserById(1));
                
                //TODO Check that all posts and comments associated with user are also deleted

            }
        });
    }
    @Test
    public void testUserConstructor() {
        User u = new User("testuser", "abc", "abc");
        assertNotNull(u.getUsername());
        assertNotNull(u.getPassword());
        assertNotNull(u.getConfirmPassword());
    }
    @Test 
    public void testValidateUserShortPassword() {
        User u = new User("testuser", "abc", "abc");
        assertFalse(u.validateUserForCreation());
    }
    @Test 
    public void testValidateUserWrongConfirmPassword() {
        User u = new User("testuser", "password", "123456");
        assertFalse(u.validateUserForCreation());
    }
    @Test 
    public void testValidateUserNullConfirmPassword() {
        User u = new User("testuser", "password", null);
        assertFalse(u.validateUserForCreation());
    }
}
