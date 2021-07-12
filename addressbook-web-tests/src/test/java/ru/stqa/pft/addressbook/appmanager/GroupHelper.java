package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void clickEditUpper() {
        click(By.xpath("(//input[@name='edit'])[1]"));
    }

    public void clickEditLower() {
        click(By.xpath("(//input[@name='edit'])[2]"));
    }

    public void deleteGroupLower() {
        click(By.xpath("(//input[@name='delete'])[2]"));
    }

    public void selectFirstGroup() {
        click(By.xpath("(//input[@name='selected[]'])[1]"));
    }

    public void selectGroupByValue(int value) {
        click(By.cssSelector("input[value ='" + value + "']"));
    }

    public void selectGroupName(String groupName) {
        wd.findElement(By.xpath("//input[@name='selected[]' and @title = 'Select (" + groupName + ")']")).click();;
    }


    public void submitGroupCreation() {
        clickSubmit();
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.xpath("(//input[@name='new'])[1]"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupByValue(group.getValue());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupByValue(group.getValue());
        deleteGroupLower();
        returnToGroupPage();
    }

    public boolean isThereAnyGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Set<GroupData> all() {
        Set<GroupData> groups = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int value = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withValue(value).withName(name));
        }
        return groups;
    }

}