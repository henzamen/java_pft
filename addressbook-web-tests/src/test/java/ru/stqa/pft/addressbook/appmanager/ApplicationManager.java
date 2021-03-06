package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public final Properties properties;

    private static WebDriver wd;
    private SessionHelper sessionHelper;
    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private ContactHelper contactHelper;
    public String browser;
    private DbHelper dbHelper;

    public ApplicationManager(String browser)  {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target","local");

        properties.load(new FileReader(String.format("src/test/resources/%s.properties",target)));
        dbHelper = new DbHelper();

        if ("".equals((properties.getProperty("selenium.server")))) {
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                //System.setProperty("webdriver.chrome.driver", properties.getProperty("web.chromeDriverDir"));
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.EDGE)) {
                System.setProperty("webdriver.edge.driver", properties.getProperty("web.edgeDriverDir"));
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                wd = new EdgeDriver(edgeOptions);
            } else
                wd = new FirefoxDriver();
        } else {
            target = System.getProperty("target","remote");
            properties.load(new FileReader(String.format("src/test/resources/%s.properties",target)));

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }

        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);  //?????????????? ????????????????
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);

        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroups() {
        return groupHelper;
    }

    public ContactHelper getContacts() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public byte[] takeScreenshot() {
        return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
    }

}