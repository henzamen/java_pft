package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class BaseHelper {
    static WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    public static void clickUpdate() {
        click(By.xpath("(//input[@name='update'])[1]"));
    }

    public static void clickSubmit() {
        click(By.xpath("//input[@name='submit']"));
    }

    public static void clickDetails() {
        click(By.xpath("//img[@alt='Details']"));

    }

    public static void click(By locator) {
        wd.findElement(locator).click();
    }

    public static void type(By locator, String text) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public static void acceptAllert() {
        Alert alert = wd.switchTo().alert();
        alert.accept();
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