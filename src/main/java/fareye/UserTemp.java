package fareye;

import java.sql.Timestamp;

/**
 * Created by fareye on 19/11/15.
 */
public class UserTemp {
    private String name;
    private long username;
    private String profileimgurl;
    private String message;
    private Timestamp timeSt;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimeSt() {
        return timeSt;
    }

    public void setTimeSt(Timestamp timeSt) {
        this.timeSt = timeSt;
    }

    public UserTemp(){

    }

    public UserTemp(String name, long username,String profileimgurl){
        this.name=name;
        this.username=username;
        this.profileimgurl=profileimgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public long getUsername() {
        return username;
    }

    public void setUsername(long username) {
        this.username = username;
    }



    public String getProfileimgurl() {
        return profileimgurl;
    }

    public void setProfileimgurl(String profileImgUrl) {
        this.profileimgurl = profileImgUrl;
    }
}
