package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPA;


public class PostTest extends BaseTest {
    
    @Before
    public void createObjects(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u = User.createUser(new User("caiyo", "Kyle"));     
                Post.createPost(new Post(u, "this is post 1"));
            }
        });
    }
    @Test
    public void testPostCreation(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u = User.findUserById(1);
                Post p = new Post(u, "this is a post!");
                assertNotNull(Post.createPost(p));
            }
        });
    }
    @Test
    public void testFindPostById(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Post p = Post.findPostById(1);
                assertNotNull(p);
            }
        });
    }
    @Test
    public void testUpdatePost(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                String content= "This post has been updated";
                Post p = Post.findPostById(1);
                p.setContent(content);
                Post.updatePost(p);
                
                Post updated = Post.findPostById(1);
                assertNotNull(updated);
                assertEquals(updated.getContent(), content);
            }
        });
    }
    
    @Test
    public void testDeletePost(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Post p = Post.findPostById(1);
                Post.deletePost(p);
                p=Post.findPostById(1);
                assertNull(p);
            }
        });
    }
}
