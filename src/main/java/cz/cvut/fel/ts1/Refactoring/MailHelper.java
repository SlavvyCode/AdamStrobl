package cz.cvut.fel.ts1.Refactoring;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author balikm1
 */
public class MailHelper {

    private final DBManager dbManager;
    public MailHelper(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    private Mail mail;
    public Mail getMail() {
        return mail;
    }

    public void setMail(String to, String subject, String body) {
        mail = new Mail();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setBody(body);
        mail.setIsSent(false);
    }

    public void saveMailToDB() {
        DBManager dbManager = new DBManager();
        dbManager.saveMail(mail);
    }

    public void handleDebugAndSendMail() {

        if (!Configuration.isDebug) {
            (new Thread(() -> {
                sendMail(mail.getMailId());
            })).start();
        }
    }

    public void createAndSendMail(String to, String subject, String body) {
        setMail(to,subject,body);
        saveMailToDB();
        handleDebugAndSendMail();
    }



    public void sendMail(int mailId) {
        try {
            // get entity
            this.mail = this.dbManager.findMail(mailId);
            if (mail == null) {
                return;
            }

            if (mail.isSent()) {
                return;
            }
//
//            String from = "user@fel.cvut.cz";
//            String smtpHostServer = "smtp.cvut.cz";
//            Properties props = System.getProperties();
//            props.put("mail.smtp.host", smtpHostServer);
//            Session session = Session.getInstance(props, null);
//            MimeMessage message = new MimeMessage(session);
//
//            message.setFrom(from);
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo(), false));
//            message.setSubject(mail.getSubject());
//            message.setText(mail.getBody(), "UTF-8");
//
//            // send
//            Transport.send(message);
            mail.setIsSent(true);
            new DBManager().saveMail(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
