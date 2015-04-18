package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPA;


public class CommentTest extends BaseTest{
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
    public void testCommentFindById(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u = User.findUserById(1);
                Post p = Post.findPostById(1);
                String c = "Comment!";
                Comment comment = new Comment(u,p,c);
                Comment.createComment(comment);
                
                Comment dbComment = Comment.findCommentById(1);
                assertNotNull(dbComment);
            }
        });
        
    }
    
    @Test
    public void testCommentCreation(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u = User.findUserById(1);
                Post p = Post.findPostById(1);
                String c = "Comment!";
                Comment comment = new Comment(u,p,c);
                Comment.createComment(comment);
                
                Comment dbComment = Comment.findCommentById(1);
                assertEquals(dbComment.getContent(), c);
            }
        });
    }
    
    @Test
    public void testCommentUpdate(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u = User.findUserById(1);
                Post p = Post.findPostById(1);
                String c = "Comment!";
                Comment comment = new Comment(u,p,c);
                Comment.createComment(comment);
                String c2 = "This is a new comment!";
                comment.setContent(c2);
                Comment dbComment = Comment.findCommentById(1);
                
                assertEquals(dbComment.getContent(), c2);
            }
        });
    }
    
    @Test
    public void testCommentDeletion(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                User u = User.findUserById(1);
                Post p = Post.findPostById(1);
                String c = "Comment!";
                Comment comment = new Comment(u,p,c);
                Comment.createComment(comment);

                Comment dbComment = Comment.findCommentById(1);
                
                assertNotNull(dbComment);
                
                Comment.deleteComment(dbComment);
                
                assertNull(Comment.findCommentById(1));
            }
        });
    }
    
    
    
   
}
