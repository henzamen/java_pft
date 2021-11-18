package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

public class ChangePasswordTests extends TestBase{
    List<String> userData;

    @BeforeMethod
    public void startMailServer() {
        userData = app.dbHelper().getUser();
        app.mailHelper().start();
    }

    @Test
    public void testChangePassword() throws IOException {
        String newPwd = "rootss";
        app.changePasswordHelper().resetPassword(userData.get(0));
        List<MailMessage> mailMessages = app.mailHelper().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, userData.get(1));
        app.changePasswordHelper().inputNewPassword(confirmationLink, newPwd);
        app.newSession().login(userData.get(0), newPwd);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mailHelper().stop();
    }
}