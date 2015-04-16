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
@Table(name="event")
public class Event {
    private long id;
    private Date createDate = new Date();


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id=id;
    }
    public Date getCreateDate() {
        return createDate;
    }
}
