package util;

import com.sun.mail.pop3.POP3SSLStore;
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
        String porta = "995";
        Folder folder = null;
        Store store = null;
   
        try {

        Properties prop = new Properties();
        
        if (!pop.equals("pop.live.com")) {
            prop.put("mail.store.protocol", "pop3s"); // Google usa POP3S
        }else{
        prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.pop3.socketFactory.fallback", "false");
        prop.setProperty("mail.pop3.port", porta);
        prop.setProperty("mail.pop3.socketFactory.port", porta);
        prop.setProperty("mail.pop3.host", host);
        prop.setProperty("mail.store.protocol", host);
        }
            Session session = Session.getInstance(prop);
//            session.setDebug(true);
            store = session.getStore();
                    if (!pop.equals("pop.live.com")) {
                          store.connect(host, 995, user, pass);
       // Google usa POP3S
        }else{
                          store.connect(host, user, pass);
                    }
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
