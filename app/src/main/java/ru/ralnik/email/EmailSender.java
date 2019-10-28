package ru.ralnik.email;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender extends javax.mail.Authenticator{
    private Multipart _multipart;
    private String mailhost = "smtp.mail.ru";
    private String user;
    private String password;
    private Session session;
    static {
        Security.addProvider(new ru.ralnik.email.JSSEProvider());
    }

    public EmailSender(String user, String password, String smtp) {
        this.user = user;
        this.password = password;

        _multipart = new MimeMultipart();

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.put("mail.smtp.socketFactory.fallback", "false");
       // props.setProperty("mail.smtp.quitwait", "false");

       // session = Session.getDefaultInstance(props, this);
        session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
    }



    public synchronized void sendMail(String subject, String body, String sender, String recipients, String filename)  {
        try {
            MimeMessage message = new MimeMessage(session);

            // Кто
            message.setSender(new InternetAddress(sender));
            // О чем
            message.setSubject(subject);
            // Кому
            if (recipients.indexOf(',') > 0)
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(recipients));
            else
                message.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(recipients));

            // Хочет сказать
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            _multipart.addBodyPart(messageBodyPart);

            // И что показать
            if (!filename.equalsIgnoreCase("")) {
                BodyPart attachBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filename);
                attachBodyPart.setDataHandler(new DataHandler(source));
                attachBodyPart.setFileName(filename);

                _multipart.addBodyPart(attachBodyPart);
            }

            message.setContent(_multipart);

           // Transport.send(message);
            Transport transport = session.getTransport("smtp");
            transport.connect(mailhost, user, password);
            Log.d("myDebug","allrecipients: "+message.getAllRecipients());
            transport.send(message);
            transport.close();
            Log.d("myDebug", "Email sent successfully.");
        } catch (Exception e) {
            Log.d("myDebug","Ошибка отправки функцией sendMail! ");
        }
    }

    public void send(){
        //sent to email
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_SUBJECT, "SUBJECT TEXT 123");
        share.putExtra(Intent.EXTRA_TEXT, "TEXT 123");
        File outputFile = new File("attach_file");
        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(outputFile)); // сюда прилетает картинка
        // startActivity(Intent.createChooser(share, "SHARE RESULT")); //
    }


}
