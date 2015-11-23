package fareye;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * Created by fareye on 16/11/15.
 */

@Component
public class EmailAPI {

    @Autowired
    private MailSender mail;

    public EmailAPI() {

    }

    public void readyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody) {

        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromAddress);
            message.setTo(toAddress);
            message.setSubject(subject);
            message.setText(msgBody);

            mail.send( message);
        } catch (Exception e) {
            System.out.print("error in mail api " + e );
        }
    }
}