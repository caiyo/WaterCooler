package models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
@MappedSuperclass
public abstract class AbstractModel {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate = new Date();
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate){
        this.creationDate=creationDate;
    }
    
    
}
