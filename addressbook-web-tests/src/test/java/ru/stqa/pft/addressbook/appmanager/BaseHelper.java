package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;


public class BaseHelper {
    static WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    public static void clickUpdate() {
        click(By.xpath("(//input[@name='update'])[1]"));
    }

    public static void clickSubmit() {
        click(By.xpath("(//input[@name='submit'])[1]"));
    }

    public static void click(By locator) {
        wd.findElement(locator).click();
    }

    public  static void reloadPage() {
        wd.navigate().refresh();
    }

    public static void type(By locator, String text) {
        wd.findElement(locator).click();
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public static void acceptAllert() {
        Alert alert = wd.switchTo().alert();
        alert.accept();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }

    }


}