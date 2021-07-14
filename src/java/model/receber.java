package model;

import java.io.*;
import java.util.*;
import javax.mail.*;

public class receber {

    String host;
    String user;
    String pass;

    public Message[] doit(String pop, String email, String senha) throws MessagingException, IOException {
        host = pop;
        user = email;
        pass = senha;

        Folder folder = null;
        Store store = null;
        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "pop3s"); // Google uses POP3S not POP3
            Session session = Session.getInstance(props);
            // session.setDebug(true);
            store = session.getStore();
            store.connect(host, user, pass);
            folder = store.getDefaultFolder().getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            Message[] messages = folder.getMessages();
            System.out.println("No of Messages : " + folder.getMessageCount());
            System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());

            return messages;
//      for (int i=0; i < messages.length; ++i) {
//        System.out.println("MESSAGE #" + (i + 1) + ":");
//        Message msg = messages[i];
//        String from = "unknown";
//        if (msg.getReplyTo().length >= 1) {
//          from = msg.getReplyTo()[0].toString();
//        }
//        else if (msg.getFrom().length >= 1) {
//          from = msg.getFrom()[0].toString();
//        }
//        String subject = msg.getSubject();
//        
//       
//        System.out.println("Saving ... " + subject +" " + from);

        // you may want to replace the spaces with "_"
            // the files will be saved into the TEMP directory
//      }
        } catch (Exception e) {

            return null;

        }

    }

    public String retornaPop(String email) {

        String[] partir;

        partir = email.split("@");
        String pop = "pop." + partir[1];
        
        if (partir[1].equals("hotmail.com")) {
            return "pop.live.com";
        }
        return pop;
    }

}
