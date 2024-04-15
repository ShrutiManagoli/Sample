package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.todopage;

public class ToDoStepDefs {

    private WebDriver driver;
    private todopage page;

    @Before
    public void setup()
    {
        driver = new ChromeDriver();
    }

    @After
    public void teardown()
    {
        if(driver!=null)
        {
            driver.quit();
        }
    }

    @Given("I am on the ToDo page")
    public void i_am_on_the_to_do_page() {
      driver.get("https://todomvc.com/examples/react/dist/");
      page = new todopage(driver);
    }
    @Given("launch the todo application")
    public void launch_the_todo_application() {
        driver.get("https://todomvc.com/examples/react/dist/");
        page = new todopage(driver);
    }

    @Then("user should be able to add the list of items {string}")
    public void user_should_be_able_to_add_the_list_of_items(String items)  {
        page.todoList(items);
        boolean itemadded =  page.itemExist(items);
        System.out.println(itemadded);
        //Assert.assertEquals(itemadded,true);
    }

    @When("clicked on radio button item is marked as done")
    public void clicked_on_radio_button_item_is_marked_as_done() {
        page.itemClick();
        Assert.assertEquals(page.itemList(),false);
    }

    @When("clicked on the active link")
    public void clicked_on_the_active_link() {
        page.activeClick();
    }

    @Then("only the active items are displayed")
    public void only_the_active_items_are_displayed(){
        page.activeClick();
        int activecount = page.countClick();
        page.allClick();
        int allcount = page.countClick();
        Assert.assertTrue(allcount>activecount);
    }

    @When("clicked on the close icon")
    public void clicked_on_the_close_icon(){
      page.closelist();
    }

    @Then("the item {String} is deleted")
    public void the_item_is_deleted(String item)
    {
        Boolean itemexists = page.itemExist(item);
        Assert.assertFalse(itemexists, "item is removed successfully");
    }
}
