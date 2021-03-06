package util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author adail
 */
public class Email {

    public static boolean enviarEmail(String serverEmail, String emailFrom, String emailTo, String emailCc, String emailBcc, String subject, String mensagem, String senha, List<File> attachedFiles) {

        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */

        if (!serverEmail.equals("smtp.live.com")) {
            props.put("mail.smtp.host", serverEmail);
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

        } else if (serverEmail.equals("smtp.live.com")) {
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", serverEmail);
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
        }

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailFrom,
                                senha);
                    }
                });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailFrom));
            //Remetente

            Address[] toUser = InternetAddress.parse(emailTo);
            Address[] toCc = InternetAddress.parse(emailCc);
            Address[] toBcc = InternetAddress.parse(emailBcc);
            //Assunto
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setRecipients(Message.RecipientType.CC, toCc);
            message.setRecipients(Message.RecipientType.BCC, toBcc);
            message.setSubject(subject);

            // Criando mensagem particionada
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(mensagem, "text/html");

            // Criando  multi-part
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // adds attachments
            if (attachedFiles != null && attachedFiles.size() > 0) {
                for (File aFile : attachedFiles) {
                    MimeBodyPart attachPart = new MimeBodyPart();

                    try {
                        attachPart.attachFile(aFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    multipart.addBodyPart(attachPart);
                }
            }

            //Incluindo mult na mensagem 
            message.setContent(multipart);

            Transport.send(message);

            return true;

        } catch (MessagingException e) {
            return false;
        }
    }

    //Retorna SMTP referente ao e-mail 

    public static String retornaSMTP(String email) {

        String[] partir;

        partir = email.split("@");
        String smtp = "smtp." + partir[1];

        if (partir[1].equals("hotmail.com")) {
            return "smtp.live.com";
        }
        if (partir[1].equals("yahoo.com")) {
            return "smtp.mail.yahoo.com";
        }
        return smtp;
    }

}
