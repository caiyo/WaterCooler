package services;

import models.EmailToken;
import models.User;
import models.UserEmail;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import play.Play;
import util.Utility;

public class EmailService {
    
    public static void addEmailToUser(User user, EmailToken emailToken){
        String subject;
        String body;
        
        subject = "Confirmation for adding email to " + user.getUsername();
        body = "Please follow the link below to confirm this email address to be linked to"
                + " your water cooler account: \n " 
                + Utility.getBaseURL()+"/emailtoken/"+emailToken.getId()+"?token=" +emailToken.getToken()
                    + "&email=" + emailToken.getEmailAddress();
        sendEmail(emailToken, subject,body);
    }
    
    
    private static void sendEmail(EmailToken emailToken, String subject, String body) {
        String smtpHost = Play.application().configuration().getString("smtp.host");
        Integer smtpPort = Integer.parseInt(Play.application().configuration().getString("smtp.port"));
        String smtpUser = Play.application().configuration().getString("smtp.user");
        String smtpPassword = Play.application().configuration().getString("smtp.password");
        
        //SimpleEmail();
        Email email = new SimpleEmail();
        
        email.setHostName(smtpHost);
        if ( smtpPort != null && smtpPort > 1 && smtpPort < 65536 ) {
            email.setSmtpPort(smtpPort);
        }
        
        if (smtpUser!="" && smtpUser!=null ) {
            email.setAuthentication(smtpUser, smtpPassword);
        }
        
        try{
            email.setFrom(smtpUser);
            email.setSubject(subject);
            email.setMsg(body);
            email.addTo(emailToken.getEmailAddress());
            email.setStartTLSEnabled(true);
            email.send();
            System.out.println("mail sent");
        }catch (EmailException e){
            e.printStackTrace();
        }
    }
}
