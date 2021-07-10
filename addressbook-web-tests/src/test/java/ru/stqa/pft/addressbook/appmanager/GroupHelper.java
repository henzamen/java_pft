package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;


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

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();  // правильнее так!
        //click(By.xpath("(//input[@name='selected[]'])["+ index +"]"));
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

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public boolean isThereAnyGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int value = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData(value, name,null,null);
            groups.add(group);
        }
        return groups;
    }
}