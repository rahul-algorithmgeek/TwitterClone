package fareye;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fareye on 16/11/15.
 */

@Component
public class EmailTest {

    @Autowired
    UserRepository usr;
    @Autowired
    EmailAPI emailAPI;

    public EmailTest() {

    }

    @SuppressWarnings("resource")
    public void send(String toAddress,String password) {
        try {

            String toAddr = toAddress;
            String fromAddress = "rahul@roboticwares.com";

            // email subject
            String mailSubject = "Welcome to Twitter !";

            // email body
            User user=usr.findByEmail(toAddr);
            String mailBody = "Congratulations ! You just created account on MyTwitter " + "\nYour login credentials are :\n" + "Email id :" + toAddr + "\nPassword : " + password ;
            mailBody+="\nClick url to activate your account: 192.168.1.168:8080/activate?un="+user.getUsername();
            emailAPI.readyToSendEmail(toAddr, fromAddress, mailSubject, mailBody);
        } catch (Exception e) {
            System.out.print("Error in email test " + e);
        }
    }
}