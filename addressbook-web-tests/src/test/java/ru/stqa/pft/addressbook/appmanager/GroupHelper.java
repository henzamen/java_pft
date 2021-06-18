package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public static void clickEditUpper() {
        click(By.xpath("(//input[@name='edit'])[1]"));
    }

    public static void clickEditLower() {
        click(By.xpath("(//input[@name='edit'])[2]"));
    }

    public void deleteGroupLower() {
        click(By.xpath("(//input[@name='delete'])[2]"));
    }

    public void selectGroup(String groupName) {
        click(By.xpath("//input[@title='Select (" + groupName + ")']"));
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
        click(By.name("new"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

}