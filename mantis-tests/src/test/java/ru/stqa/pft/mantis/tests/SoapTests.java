package ru.stqa.pft.mantis.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

    @Test(priority = 1)
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {

        Set<Project> projects = app.soapHelper().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test(priority = 2)
    public void checkCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soapHelper().getProjects();
        Issue issue = new Issue()
                .withSummary("Test issue")
                .withDescription("Test issue description")
                .withProject(projects.iterator().next());

        Issue created = app.soapHelper().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }
}