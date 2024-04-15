package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class todopage {

    private WebDriver driver;

    // By locators
    private By todoLocator = By.xpath("//input[@class='new-todo']");
    private By itemList = By.xpath("//label[@data-testid='todo-item-label']");
    private By itemListradiobutton = By.xpath("//input[@data-testid='todo-item-toggle']");
    private By allLocator = By.xpath("//a[@href='#/']");
    private By activeLocator = By.xpath("//a[@href='#/active']");
    private By completedLocator = By.xpath("//a[@href='#/completed']");
    private By clear_completedLocator = By.xpath("//button[@class='clear-completed']");
    private By count = By.xpath("//span[@class='todo-count']");
    private By close = By.xpath("//button[@class='destroy']");

    public todopage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void todoList(String todo)  {
        WebElement todoInput = driver.findElement(todoLocator);
        todoInput.click();
        todoInput.sendKeys(todo);
        todoInput.sendKeys(Keys.RETURN);
    }

    public boolean itemExist(String items)
    {
        WebElement item = driver.findElement(itemList);
       // String actualItem =item.getText();
       // System.out.println("item added is " + actualItem);
        if(items.equalsIgnoreCase(item.getText()))
            return true;
        else
            return false;
    }

    public String itemList()
    {
        WebElement items = driver.findElement(itemList);
        String actualItem =items.getText();
        return actualItem;
    }

    public void itemClick()
    {
        WebElement itemClick = driver.findElement(itemListradiobutton);
        itemClick.click();
    }

    public void allClick()
    {
        WebElement allClick = driver.findElement(allLocator);
        allClick.click();
    }

    public void activeClick()
    {
        WebElement activeClick =  driver.findElement(activeLocator);
        activeClick.click();
    }

    public void completedLocatorClick()
    {
        WebElement completedClick = driver.findElement(completedLocator);
        completedClick.click();
    }

    public void clear_completedLocatorClick()
    {
        WebElement clear_completedClick = driver.findElement(clear_completedLocator);
        clear_completedClick.click();
    }

    public int countClick()
    {
        WebElement countClick = driver.findElement(count);
        String sentence = countClick.getText();
        String split[] = sentence.split(" ");
        int count = Integer.parseInt(split[0]);
        return count;
    }

    public void closelist()
    {
        WebElement items = driver.findElement(itemList);
        items.click();
        WebElement closeicon = driver.findElement(close);
        closeicon.click();
    }
}
