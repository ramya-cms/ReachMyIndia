package Generic;

import javax.mail.*;
import javax.mail.search.SubjectTerm;
import java.util.Properties;

public class GmailIMAPService {

    private static Session createSession() {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imap.host", "imap.gmail.com");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.ssl.enable", "true");
        props.put("mail.debug", "true"); // Enable debug logging

        return Session.getInstance(props);
    }

    public static void readEmails(String username, String appPassword, String subject) throws Exception {
        Session session = createSession();

        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", username, appPassword);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.search(new SubjectTerm(subject));
        for (Message message : messages) {
            System.out.println("Email Subject: " + message.getSubject());
            System.out.println("Email Body: " + message.getContent().toString());
        }

        inbox.close(false);
        store.close();
    }
}
