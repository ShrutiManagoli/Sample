package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;

public class PageInfo {

    private WebDriver driver;

    public String path = "Users/admin/Desktop/Oppenheimer-Sheet1.csv";
    //By Locators
    private By UsernameLocator = By.name("username");
    private By PasswordLocator = By.name("password");
    private By SubmitLocator = By.xpath("//input[@class='btn btn-primary']");
    private By LogoutLocator = By.xpath("//button[contains(text(),'Log out')]");
    private By HeroButton = By.xpath("//a[@href ='/clerk/upload-csv']");
    private By UploadCSVDrpdown = By.xpath("//a[@href='/clerk/upload-csv']");
    private By AddDropdownOption = By.xpath("//a[@href='/clerk/add']");

    private By ChooseFilebButton = By.xpath("//input[@id='upload-csv-file']");



    //Constructor

    public PageInfo(WebDriver driver) {
        this.driver = driver;
    }

    //page methods
    public void enterName(String name) {
        WebElement nameInput = driver.findElement(UsernameLocator);
        nameInput.sendKeys(name);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(PasswordLocator);
        passwordInput.sendKeys(password);
    }

    public void submitClick() {
        WebElement submitClick = driver.findElement(SubmitLocator);
        submitClick.click();
    }
    public void LogoutClick() {
        WebElement LogoutClick = driver.findElement(LogoutLocator);
        LogoutClick.click();
    }

    public boolean LogoutExists() {
        WebElement LogoutClick = driver.findElement(LogoutLocator);
        if (LogoutClick.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void ClickHeroBtn()
    {
        WebElement HeroButtonClick = driver.findElement(HeroButton);
        HeroButtonClick.click();
    }

    public void UploadCSVfileClick()  {
        WebElement UploadCSVClick = driver.findElement(UploadCSVDrpdown);
        UploadCSVClick.click();
    }

    public void uploadfile(){
        WebElement ChooseFilebButtonClick = driver.findElement(ChooseFilebButton);
        ChooseFilebButtonClick.sendKeys("/Users/admin/Desktop/Oppenheimer-Sheet1.csv");
    }

}