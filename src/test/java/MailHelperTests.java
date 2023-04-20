import cz.cvut.fel.ts1.Refactoring.DBManager;
import cz.cvut.fel.ts1.Refactoring.Mail;
import cz.cvut.fel.ts1.Refactoring.MailHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MailHelperTests {


    private String to ="email@template.org";
    private String body = "Mail body";
    private String subject = "mailSubject";
    private boolean isSent = false;
    private MailHelper mailHelper;

    @BeforeEach
    public void setMail(){
        mailHelper= new MailHelper(new DBManager());
        mailHelper.setMail(to,subject,body);
    }

    @Test
    public void setMail_setToHasCorrectValue_setHasCorrectValue(){
        Assertions.assertEquals(to,mailHelper.getMail().getTo());
    }
    @Test
    public void setMail_setSubjectHasCorrectValue_setHasCorrectValue(){
        Assertions.assertEquals(subject,mailHelper.getMail().getTo());
    }
    @Test
    public void setBody_setBodyHasCorrectValue_setHasCorrectValue(){
        Assertions.assertEquals(body,mailHelper.getMail().getTo());
    }

@Test
    public void setMail_setIsSent_false(){
        Assertions.assertFalse(mailHelper.getMail().isSent());
    }





}
