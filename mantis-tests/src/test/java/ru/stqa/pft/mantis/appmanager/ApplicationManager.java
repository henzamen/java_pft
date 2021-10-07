package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public final Properties properties;

    private static WebDriver wd;

    public String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");

        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));


        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
//        } else if (browser.equals(BrowserType.CHROME)) {
//            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.EDGE)) {
            System.setProperty("webdriver.edge.driver", properties.getProperty("web.edgeDriverDir"));
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            wd = new EdgeDriver(edgeOptions);
        } else
            wd = new FirefoxDriver();

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);  //неявное ожидание
        wd.get(properties.getProperty("web.baseUrl"));
    }

    public void stop() {
        wd.quit();
    }


}