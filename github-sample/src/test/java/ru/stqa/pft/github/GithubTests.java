package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {

        Github github = new RtGithub("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");
        //https://github.com/settings/tokens
        RepoCommits commits = github.repos().get(new Coordinates.Simple("henzamen", "java_pft")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
