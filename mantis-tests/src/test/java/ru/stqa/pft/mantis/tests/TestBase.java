package ru.stqa.pft.mantis.tests;


import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    List<Integer> issuesOpenForThisTest = new ArrayList<Integer>();


    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),
                "config_inc_php", "config_inc_php.bak");
    }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = new MantisConnectLocator()
                .getMantisConnectPort(new URL(
                        app.getProperty("mantis.web.baseUrl") + app.getProperty("mantis.api.soap.mantisconnect"))
                );
        IssueData issueMarker = mc.mc_issue_get(
                app.getProperty("mantis.web.adminLogin"),
                app.getProperty("mantis.web.adminPassword"), BigInteger.valueOf(issueId));
        ObjectRef status = issueMarker.getStatus();
        if (status.getName().equals("resolved"))
            return false;
        else return true;
    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            System.out.println("Test ignored because of issue " + issueId + ".");
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public void checkIssuesForThisTest() {
        Stream<Integer> stream = issuesOpenForThisTest.stream();
        stream
                .forEach( element -> {
                    try { skipIfNotFixed(element);

                    } catch (MalformedURLException e) {

                    } catch (ServiceException e) {

                    } catch (RemoteException e) {

                    }
                });
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc_php.bak", "config_inc_php");
        app.stop();
    }
}