package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactRequiredData;

import java.util.concurrent.TimeUnit;

public class ContactCreationTest {
    private WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\TestTools\\webdrivers\\geckodriver.exe");
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost:8080/addressbook/group.php");
        login("admin", "secret");
    }

    private void login(String username, String password) {
        editField("user", username);
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Test
    public void testContactCreation() {
        String name = "Henry";
        String lastName = "Sam";

        addNewContact();
        fillContactForm(new ContactRequiredData(name, lastName, "+7473 1234567", "hensam@list.ru"));
        goToHomePage();
        selectContact(name + " " + lastName);
        clickDetails();
        clickLinkHome();
        editContact();
        editField("company", "unknown");
        submitUpdate();
        goToHomePage();
        selectContact(name + " " + lastName);
        pressButtonDelete();
        acceptAllert();
        logout();
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    private void acceptAllert() {
        Alert alert = wd.switchTo().alert();
        alert.accept();
    }

    private void pressButtonDelete() {
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    private void submitUpdate() {
        wd.findElement(By.xpath("(//input[@name='update'])[2]")).click();
    }

    private void editField(String field, String value) {
        wd.findElement(By.name(field)).click();
        wd.findElement(By.name(field)).clear();
        wd.findElement(By.name(field)).sendKeys(value);
    }

    private void editContact() {
        wd.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    private void clickLinkHome() {
        wd.findElement(By.linkText("home")).click();
    }

    private void clickDetails() {
        wd.findElement(By.xpath("//img[@alt='Details']")).click();
    }

    private void selectContact(String nameLastName) {
        wd.findElement(By.xpath("//input[@title='Select (" + nameLastName + ")']")).click();

    }

    private void goToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    private void fillContactForm(ContactRequiredData contactRequiredData) {
        editField("firstname", contactRequiredData.getFirstname());
        editField("lastname", contactRequiredData.getLastname());
        editField("mobile", contactRequiredData.getMobile());
        editField("email", contactRequiredData.getEmail());
        wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    private void addNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        wd.quit();

    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
