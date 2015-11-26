package fareye;


import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by fareye on 11/11/15.
 */
@RestController

public class RequestHandler {
    @Autowired
    EmailPassword emailPassword;

    @Autowired
    EmailTest emailTest;
    @Autowired
    FollowRepository followRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    TwitterRepository twitterRepository;
    @Autowired
    SessionData sessionData;

    //Authentication handler
    @RequestMapping("/auth")
    public ResponseEntity handleAuthenticationRequest(@RequestBody String json) {//(@RequestBody String json ,HttpServletResponse res) use res.sendRedirect(/#/Dashboard);
        System.out.println("Login Credentials are: " + json);
        try {

            JSONObject login_cred = new JSONObject(json);

            User user = userRepository.findByPhoneOrEmail(login_cred.getString("username"), login_cred.getString("username"));
            System.out.println("User: " + user);
            if (user != null && user.getAcStatus().equals("Active")) {
                System.out.println("DATA: " + user.getName() + user.getPassword());
                if (login_cred.getString("password").equals(user.getPassword())) {
                    JSONObject resobj = new JSONObject();
                    resobj.put("name", user.getName());
                    resobj.put("username", user.getUsername());
                    resobj.put("imageUrl", user.getProfileImgUrl());
                    sessionData.setUsername(user.getUsername());
                    sessionData.setName(user.getName());
                    System.out.println("Success");
                    return new ResponseEntity(resobj.toString(), HttpStatus.OK);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("Failure");
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    //Add new user handler
    @RequestMapping("/addUser")
    public ResponseEntity handleAddUserRequest(@RequestBody User user) {
            System.out.println("In add user !");
        try {
            if ((userRepository.findByEmail(user.getEmail()) == null) && (userRepository.findByPhone(user.getPhone()) == null)) {
                user.setAcStatus("InActive");
                user.setProfileImgUrl("../image/default.jpg");
                userRepository.save(user);
                emailTest.send(user.getEmail(), user.getPassword());

                User v = userRepository.findByEmail(user.getEmail());
                System.out.println("v::::" + v.getUsername());
                sessionData.setUsername(v.getUsername());
                sessionData.setName(v.getName());
                Follow obj = new Follow(v.getUsername(), v.getUsername());

                followRepository.save(obj);
                JSONObject resobj = new JSONObject();
                resobj.put("name", v.getName());
                resobj.put("username", v.getUsername());
                resobj.put("imageUrl", v.getProfileImgUrl());

                System.out.println("SEt data " + sessionData.getUsername());
                return new ResponseEntity(resobj.toString(), HttpStatus.OK);
            } else {
                System.out.println("In add user else part !");
                return new ResponseEntity(null, HttpStatus.FOUND);
            }
        } catch (Exception e) {
            System.out.println("Exception in handleAddUserRequest. " + e);
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    //Email Activation link handler
    @RequestMapping(value="/activate")
    public HttpStatus activateUserAccount(@RequestParam(value="un") String username){
        User user=userRepository.findByUsername(Long.parseLong(username));
        user.setAcStatus("Active");
        userRepository.save(user);
        return HttpStatus.ACCEPTED;
    }

    //Listener for change password request.
    @RequestMapping("/changePswd")
    public ResponseEntity handleChangePasswordRequest(@RequestBody String json) {
            System.out.println("Change password request received");
            User c=userRepository.findByUsername(sessionData.getUsername());

            try {
                JSONObject resetPswd = new JSONObject(json);
                if(c.getEmail().equals(resetPswd.getString("email")) && c.getPassword().equals(resetPswd.getString("oldPassword"))) {
                    userRepository.resetUserPassword(resetPswd.getString("newPassword"), resetPswd.getString("email"));
                    System.out.println("Password changed successfully");
                    return new ResponseEntity(null,HttpStatus.OK);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
        }

    //Forget Password handler
    @RequestMapping("/forgot")
    public ResponseEntity handleForgotPasswordRequest(@RequestBody String json) {
            System.out.println("Forgot password request received");
            try {
                JSONObject resetPswd = new JSONObject(json);
                User f=userRepository.findByEmail(resetPswd.getString("email"));
                if (f.getEmail() != null && f.getPhone() != null) {
                    System.out.println("Password Send successfully");
                    emailPassword.send(resetPswd.getString("email"));
                    return new ResponseEntity(null,HttpStatus.OK);
                }
            } catch (Exception e) {
                return new ResponseEntity(null,HttpStatus.BAD_REQUEST) ;
    //            e.printStackTrace();

            }
            return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
        }


    //Insert new tweet handler
    @RequestMapping("/uploadNewTweet")
    public HttpStatus handleAddUserRequest(@RequestBody Tweet tweet) {
            if(sessionData.getUsername()!=0){
        try {

            tweet.setUsername(sessionData.getUsername());
            System.out.println("tweet :" + tweet.getUsername());
            tweet.setTimeSt();
            twitterRepository.save(tweet);
            return HttpStatus.ACCEPTED;
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
            }

        return HttpStatus.BAD_REQUEST;
    }

    //Upload Image handler
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public @ResponseBody void handleFileUpload( @RequestParam("file") MultipartFile file,HttpServletResponse response)
    {
        if (!file.isEmpty()) {
            System.out.println("In uploadimage ! ");
            try {

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("src/main/webapp/image/"+sessionData.getUsername()+".jpg"));
                stream.write(bytes);
                stream.close();

                userRepository.updateProfileImage("../image/"+sessionData.getUsername()+".jpg", sessionData.getUsername());
                System.out.println("Updated Image successfully ");
                response.sendRedirect("/#/dashboard/profile");

            } catch (Exception e) {

            }
        } else {

        }
    }

    //Updating Profile Image in view
    @RequestMapping("/getImageUrl")
    public ResponseEntity handleImageUrlRequest(@RequestBody String obj){
        User img=userRepository.findByUsername(sessionData.getUsername());
        JSONObject ob=new JSONObject();
        try {
            ob.put("profileImgUrl", img.getProfileImgUrl());
            return new ResponseEntity(ob.toString(), HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
    }
    //Follower counts , following count and tweet counts
    @RequestMapping("/counts")
    public ResponseEntity handleCount(@RequestBody User obj){
        System.out.println("Following count "+followRepository.following(sessionData.getUsername()));
        System.out.println("Tweet  count "+twitterRepository.tweetCount(sessionData.getUsername()));
        System.out.println("Follower count "+followRepository.follower(sessionData.getUsername()));
            try{
                JSONObject ob=new JSONObject();
                ob.put("following",followRepository.following(sessionData.getUsername()));
                ob.put("follower",followRepository.follower(sessionData.getUsername()));
                ob.put("tweetcount",twitterRepository.tweetCount(sessionData.getUsername()));
            return new ResponseEntity(ob.toString(),HttpStatus.OK);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        return  new ResponseEntity(null,HttpStatus.BAD_REQUEST);
    }

    //Handling Person I  Follow list
    @RequestMapping("/follow")
    public ResponseEntity handleShowMyFollowRequest(@RequestBody User obj) {
        System.out.println("Handling my Follow list " + obj.getUsername());
        try {

            List<User> followList = userRepository.whomIFollow(obj.getUsername());
            System.out.println("User Lists: " + followList);
            return new ResponseEntity(followList, HttpStatus.OK);

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    //Handling Follow recombination list
    @RequestMapping("/nfollow")
    public ResponseEntity handleShowMyNFollowRequest(@RequestBody User obj) {
        System.out.println("Handling my NFollow list " + obj.getUsername());
        try {
            JSONArray respArr = new JSONArray();

            List<User> followList = userRepository.tobefollowed(obj.getUsername());
            System.out.println("Nfollow User Lists: " + followList);
            return new ResponseEntity(followList, HttpStatus.OK);

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    //unFollow request handler
    @RequestMapping("/Requnfollow")
    public HttpStatus handleUnFollowRequest(@RequestBody String json) {
        System.out.println("Change password request received");
        try {

            JSONObject un = new JSONObject(json);
            followRepository.unfollow(sessionData.getUsername(), un.getLong("follow"));
            System.out.println("Unfollowed successfully");
            return HttpStatus.ACCEPTED;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpStatus.BAD_REQUEST;
    }

    //Follow request handler
    @RequestMapping("/Reqfollow")
    public HttpStatus handleFollowRequest(@RequestBody String json) {
        System.out.println("Change password request received");
        try {

            JSONObject un = new JSONObject(json);
            Follow obj = new Follow(sessionData.getUsername(), un.getLong("follow"));

            followRepository.save(obj);

            System.out.println("followed successfully");
            return HttpStatus.ACCEPTED;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpStatus.BAD_REQUEST;


    }

    //Displaying myTweets handler
    @RequestMapping("/mytweets")
    public ResponseEntity handleShowMyTweetRequest(@RequestBody Tweet obj) {
            System.out.println("Handling myTweet list " + obj.getUsername());
            try {


                List<Tweet> tweetList = twitterRepository.getMessages(obj.getUsername());
                System.out.println("Mytweet  Lists: " + tweetList);
                return new ResponseEntity(tweetList, HttpStatus.OK);

            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }

    //Displaying all tweets handler
    @RequestMapping("/tweets")
    public ResponseEntity handleShowTweetRequest(@RequestBody Tweet obj) {
        System.out.println("Handling allTweet list " + obj.getUsername());
        try {


            List<UserTemp> tweetList = twitterRepository.getCurrentMessages(obj.getUsername());
            System.out.println("All tweet  Lists: " + tweetList);
            return new ResponseEntity(tweetList, HttpStatus.OK);

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }


    //Logout handler
    @RequestMapping("/logout")
    public HttpStatus handleLogoutRequest(@RequestBody String json) {
        System.out.println("Logout request received");
        try {

            sessionData.setUsername(0);
            sessionData.setName(null);

            System.out.println("Logged out  successfully");
            return HttpStatus.ACCEPTED;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpStatus.BAD_REQUEST;
    }

}




