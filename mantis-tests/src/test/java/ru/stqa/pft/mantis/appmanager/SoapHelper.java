package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


public class SoapHelper {

    private ApplicationManager app;
    private final String userName;
    private final String password;

    public SoapHelper(ApplicationManager app) {
        userName = app.getProperty("mantis.web.adminLogin");
        password = app.getProperty("mantis.web.adminPassword");
        this.app = app;
    }

    public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {

        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible(userName, password);
        System.out.println();

        return Arrays.asList(projects).stream()
                .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL(
                        app.getProperty("mantis.web.baseUrl") + app.getProperty("mantis.api.soap.mantisconnect"))
                );
    }

    public Issue addIssue(Issue issue) throws ServiceException, MalformedURLException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(userName, password, BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add(userName, password, issueData);
        IssueData createdIssueData = mc.mc_issue_get(userName, password, issueId);
        return new Issue().withId(createdIssueData.getId().intValue())
                .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
                .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                        .withName(createdIssueData.getProject().getName()));
    }

    public Set<Issue> getIssues(Project prj) throws ServiceException, MalformedURLException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData[] issues = mc.mc_project_get_issues(
                userName, password, BigInteger.valueOf(prj.getId()), BigInteger.valueOf(1), BigInteger.valueOf(-1)
        );
        return Arrays.asList(issues).stream()
                .map((p) -> new Issue().withId(p.getId().intValue())
                        .withDescription(p.getDescription()))
                .collect(Collectors.toSet());
    }
}