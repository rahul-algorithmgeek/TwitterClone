package fareye;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fareye on 16/11/15.
 */

@Component
public class EmailPassword {

    @Autowired
    UserRepository usr;
    @Autowired
    EmailAPI emailAPI;

    public EmailPassword() {

    }

    @SuppressWarnings("resource")
    public void send(String toAddress) {
        try {

            String toAddr = toAddress;
            String fromAddress = "rahul@roboticwares.com";

            // email subject
            String mailSubject = "Your login details !";
            String unique="";

            // email body
            User user=usr.findByEmail(toAddr);
            String mailBodyf =  "Your login credentials are :\n" + "Email id :" + toAddr + "\nPassword : " + user.getPassword() ;

            emailAPI.readyToSendEmail(toAddr, fromAddress, mailSubject, mailBodyf);
        } catch (Exception e) {
            System.out.print("Error in email test " + e);
        }
    }
}