package fareye;

import sun.util.calendar.LocalGregorianCalendar;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.zip.DataFormatException;

/**
* Created by fareye on 13/11/15.
*/
@IdClass(TweetPrimaryKey.class)
@Entity
@Table(name="tweets")
public class Tweet {
    @Id
    private long username;
    @Id
    @Column(name="timest")
    private Timestamp timeSt;
    private String message;

    public Tweet(){}
    public Tweet(String message){
        this.message=message;
    }

    public Tweet(long username, String message){
        this.username=username;


        this.message=message;
    }

    public long getUsername() {
        return username;
    }

    public void setUsername(long username) {
        this.username = username;
    }

    public Timestamp getTimeSt() {
        return timeSt;
    }

    public void setTimeSt() {
        java.util.Date date= new java.util.Date();
        this.timeSt=new Timestamp(date.getTime());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
