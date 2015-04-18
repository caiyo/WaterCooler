package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.JPA;

@Entity
@Table(name="post")
@AttributeOverride(name = "id", column = @Column(name = "post_id"))
public class Post extends AbstractModel {
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="post", cascade=CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();
    
    private String content;

    public Post(){}
    public Post(User user, String content){
        this.user=user;
        this.content=content;
    }
   
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Set<Comment> getComments() {
        return comments;
    }
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

/*
 * STATIC METHODS
 *
 */
    public static Post findPostById(long id){
        return JPA.em().find(Post.class, id);
    }
    
    public static Post createPost(Post post){
        JPA.em().persist(post);
        return post;
    }
    //Not necessarily needed since dirty checking is enabled by default for hibernate
    //created just in case
    public static Post updatePost(Post updatedPost){
        JPA.em().merge(updatedPost);
        return updatedPost;
    }
    public static void deletePost(Post post){
        JPA.em().remove(post);
    }
    
}
