package com.flat.utils;

import com.flat.model.Apartment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Notificator {

    private static Logger LOGGER = Logger.getLogger(Notificator.class.getName());

    @Value("${email.from}")
    String emailFrom;

    @Value("${email.to}")
    String emailTo;

    public void notify(List<Apartment> newApartments) throws UnsupportedEncodingException, MessagingException {
        LOGGER.info("Send notification to " + emailTo);

        String msgBody = MessageBuilder.buildApartmentsMessage(newApartments);
        String subject = "Hey! " + newApartments.size() + " New flats!";

        sendMessage(subject, msgBody);
    }

    public void notifyError(Throwable e)
    {
        String subject = "Roof in the FIRE!!!";
        String msgBody = e.getMessage();
        try
        {
            sendMessage(subject, msgBody);
        } catch (MessagingException | UnsupportedEncodingException e1)
        {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    private void sendMessage(String subject, String body) throws MessagingException, UnsupportedEncodingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailFrom, "FlatFinder"));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo, "Me"));
        msg.setSubject(subject);
        msg.setText(body);
        Transport.send(msg);
    }
}
