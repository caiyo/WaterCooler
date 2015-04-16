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
import play.db.jpa.JPA;

@Entity
@Table(name="comment")
@AttributeOverride(name = "id", column = @Column(name = "comment_id"))
public class Comment extends AbstractModel {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
   
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
    
    private String content;
    
/*
 * Getters and setters
 * 
 */
    
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
/*
 * STATIC METHODS
 */
    public static Comment findCommentById(long id){
        return JPA.em().find(Comment.class, id );
    }
    
    public static Comment createComment(Comment comment, Post post){
        comment.setPost(post);
        JPA.em().persist(comment);
        return comment;
    }
    
    public static Comment updateComment(Comment comment, String content){
        comment.setContent(content);
        JPA.em().merge(comment);
        return comment;
    }
    
    public static void deleteComment(Comment comment){
        JPA.em().remove(comment);
    }

}
