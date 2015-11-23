 package fareye;

import javax.persistence.IdClass;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by fareye on 16/11/15.
 */

//Annotation for implementing a class as a primary key

    @IdClass(TweetPrimaryKey.class)
public class TweetPrimaryKey implements Serializable {
    private long username;
    private Timestamp timeSt;

//    Constructors
    public TweetPrimaryKey(){

    }

    public TweetPrimaryKey(long username){
        this.username=username;
        java.util.Date date= new java.util.Date();
        this.timeSt=new Timestamp(date.getTime());
    }

//    Getters and setters
    public long getUsername() {
        return username;
    }

    public void setUsername(long username) {
        this.username = username;
    }

    public Timestamp getTimeSt() {
        return timeSt;
    }

    public void setTimeSt(Timestamp timeSt) {
        this.timeSt = timeSt;
    }


//    Override methods for primary key class

    @Override
    public boolean equals(Object obj){
        if(obj instanceof TweetPrimaryKey){
            TweetPrimaryKey pk=(TweetPrimaryKey)obj;
            if(this.username==pk.getUsername() && this.timeSt==pk.getTimeSt())
                return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
}

