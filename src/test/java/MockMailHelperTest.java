import cz.cvut.fel.ts1.Refactoring.DBManager;
import cz.cvut.fel.ts1.Refactoring.Mail;
import cz.cvut.fel.ts1.Refactoring.MailHelper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MockMailHelperTest {

//    mock(MailHelper);

    DBManager mockDBManager = Mockito.mock(DBManager.class);
    MailHelper mailHelper = new MailHelper(mockDBManager);

@Test
    public void positiveVerifySendMailOnce() {

    mailHelper.sendMail(5);
    Mockito.verify(mockDBManager).findMail(5);
}
//    @Test
//    public void negativeVerifySendMailTwice() {
//
//        mailHelper.sendMail(3);
//        Mockito.verify(mockDBManager, Mockito.times(2)).findMail(3);
//    }
    @Test
    public void negativeVerifySendMailWithDifferentParam() {

        mailHelper.sendMail(3);
        Mockito.verify(mockDBManager).findMail(3);
    }

    @Test
    public void mockTest(){
     int mailId = 1;
     mailHelper.setMail("mockedto","mockedsubject","mockedbody");
     Mail mockedMail = mailHelper.getMail();
     Mockito.when(mockDBManager.findMail(mailId)).thenReturn(mockedMail);
     mailHelper.sendMail(mailId);

     System.out.println(mailHelper.getMail().getBody());
    }
}
