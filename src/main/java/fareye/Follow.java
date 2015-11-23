package fareye;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Created by fareye on 18/11/15.
 */
@Entity
@Table(name="follows")
public class Follow {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long username;
    @Column(name="followsto")
    private long followsTo;

   public Follow(){

   }
   public Follow(long username,long followsTo){
        this.username=username;
        this.followsTo=followsTo;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUsername() {
        return username;
    }

    public void setUsername(long username) {
        this.username = username;
    }

    public long getFollowsTo() {
        return followsTo;
    }

    public void setFollowsTo(long followsTo) {
        this.followsTo = followsTo;
    }


}
