package ru.stqa.pft.rest.tests;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager();

    List<Integer> issuesOpenForThisTest = new ArrayList<Integer>();

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get(app.getProperty("bugify.issues.baseUrl")))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        //System.out.println(new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType()).toString());
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post(app.getProperty("bugify.issues.baseUrl"))
                        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                                new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }


    public boolean isIssueOpen(int issueId) throws IOException {
        Set<Issue> allIssues = getIssueState(issueId);

        System.out.println("Issue #" + issueId + " is " + allIssues.iterator().next().getState_name());

        String state = allIssues.iterator().next().getState_name();
        if (state.equals("Open"))
            return true;
        else
            return false;
    }

    private Set<Issue> getIssueState(int issueId) throws IOException {

        String json = getExecutor().execute(Request.Get(app.getProperty("bugify.issues.shortUrl") + issueId + ".json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    public int getLastIssueId() throws IOException {
        Set<Issue> allIssues = getIssues();
        return allIssues.stream().sorted((o1, o2) -> o2.getId()- o1.getId()).findFirst().orElse(null).getId();
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            System.out.println("Test ignored because of issue " + issueId + ".");
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public void checkIssuesForThisTest() {
        Stream<Integer> stream = issuesOpenForThisTest.stream();
        stream
                .forEach(element -> {
                    try {
                        skipIfNotFixed(element);

                    } catch (MalformedURLException e) {

                    } catch (IOException e) {

                    }
                });
        System.out.println("There are no open issues for this test.");
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}