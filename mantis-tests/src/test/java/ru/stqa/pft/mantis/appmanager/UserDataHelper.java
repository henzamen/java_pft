package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserDataHelper extends HelperBase {

    public UserDataHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPassword(String user) {
        wd.get(app.getProperty("mantis.web.baseUrl"));
        type(By.id("username"), app.getProperty("mantis.web.adminLogin"));
        click(By.cssSelector("input[type='submit']"));
        type(By.id("password"), app.getProperty("mantis.web.adminPassword"));
        click(By.cssSelector("input[type='submit']"));
        wd.get(app.getProperty("mantis.web.baseUrl") + "/manage_user_page.php");
        click(By.xpath("//a[text()='" + user + "']"));
        click(By.cssSelector("form[id='manage-user-reset-form'] input[type='submit']"));
    }

    public void inputNewPassword(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button"));
    }
}