/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
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

    String serverEmail;
    String emailFrom;
    String emailTo;
    String emailCc;
    String emailBcc;
    String subject;
    String mensagem;
    String senha;

    public Email() {

    }

    public boolean enviarEmail(String serverEmail, String emailFrom, String emailTo, String emailCc, String subject, String mensagem, String senha) {

        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */

        props.put("mail.smtp.host", serverEmail);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
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

            if (emailCc.equals("")) {

            } else {
                emailCc = "," + emailCc;
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailFrom));
            //Remetente

            Address[] toUser = InternetAddress.parse(emailTo + emailCc);

            //Assunto
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(subject);
//            message.setText(mensagem);
            // cria a primeira parte da mensagem
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(mensagem);

            // cria a segunda parte da mensage
//            MimeBodyPart mbp2 = new MimeBodyPart();
           
          
            
            // anexa o arquivo na mensagem
//            FileDataSource fds = new FileDataSource(filename);
//            mbp2.setDataHandler(new DataHandler(fds));
//            mbp2.setFileName(fds.getName());

            // cria a Multipart
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);
//            mp.addBodyPart(mbp2);

            // adiciona a Multipart na mensagem
            message.setContent(mp);

            // configura a data: cabecalho
            message.setSentDate(new Date());

            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

            return true;

        } catch (MessagingException e) {
            return false;
        }
    }

    public String retornaSMTP(String email) {

        String[] partir;

        partir = email.split("@");
        String smtp = "smtp." + partir[1];
        
        if(partir[1].equals("hotmail.com")){
            return  "smtp.live.com";
        }
        return smtp;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getServerEmail() {
        return serverEmail;
    }

    public void setServerEmail(String serverEmail) {
        this.serverEmail = serverEmail;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailCc() {
        return emailCc;
    }

    public void setEmailCc(String emailCc) {
        this.emailCc = emailCc;
    }

    public String getEmailBcc() {
        return emailBcc;
    }

    public void setEmailBcc(String emailBcc) {
        this.emailBcc = emailBcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
