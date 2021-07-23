package util;

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
            System.out.println("Numero de mensagens : " + folder.getMessageCount());
            System.out.println("Numero de mensagens não lidas: " + folder.getUnreadMessageCount());

            return messages;

        } catch (Exception e) {

            return null;

        }

    }

    //retorna Pop referente ao e-mail
    public String retornaPop(String email) {

        String[] partir;

        partir = email.split("@");
        String pop = "pop." + partir[1];

        if (partir[1].equals("hotmail.com")) {
            return "pop.live.com";
        }
        if (partir[1].equals("yahoo.com")) {
            return "pop.mail.yahoo.com";
        }
        return pop;
    }

}
