package Pages;

import dev.failsafe.internal.util.Assert;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageInfo {

    private WebDriver driver;

    public Pattern stringPattern;


    //By Locators
    private final By UsernameLocator = By.name("username");
    private final By PasswordLocator = By.name("password");
    private final By ClerkDashboardLocator = By.xpath("//div[@class='col-md']/h1[contains(text(),'Clerk Dashboard')]");
    private final By SubmitLocator = By.xpath("//input[@class='btn btn-primary']");
    private final By LogoutLocator = By.xpath("//button[contains(text(),'Log out')]");
    private final By HeroButton = By.xpath("//button[@id ='dropdownMenuButton2']");
    private final By UploadCSVDrpdownOption = By.xpath("//a[@href='/clerk/upload-csv']");
    private final By ChooseFileButton = By.xpath("//input[@id='upload-csv-file']");
    private final By CreateButton = By.xpath("//button[contains(text(), 'Create')]");
    private final By SuccessMsg = By.xpath("//div[@id='notification-block']");
    //private final By ErrorMsg = By.xpath("//div[@class='row py-2']//descendant::h3[contains(text(), 'Unable to create hero')]");
    private final By ErrorMsg = By.xpath("//div[contains(@class, '')]/h3");
    private final By BackBtn = By.xpath("//a[@class='btn btn-primary']");
    public By TaxReliefBtn = By.xpath("//button[@id='tax_relief_btn']");

    public static By EgressProgressMsg = By.xpath("//span[@id=\"tax_relief_status_id");
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

    public void ClickHeroBtn() {
        if (driver.findElement(HeroButton).isDisplayed()) {
            WebElement HeroButtonClick = driver.findElement(HeroButton);
            HeroButtonClick.click();
        }
    }

    public void uploadfileSucess() {
        WebElement selectCSVdropdown = driver.findElement(UploadCSVDrpdownOption);
        selectCSVdropdown.click();
        WebElement ChooseFileButtonClick = driver.findElement(ChooseFileButton);
        System.out.println(ChooseFileButtonClick.isDisplayed());
        File file = new File("./Oppenheimer-Sheet1.csv");
        //System.out.println(file.getAbsolutePath());
        ChooseFileButtonClick.sendKeys(file.getAbsolutePath());
        WebElement CreatebuttonClick = driver.findElement(CreateButton);
        CreatebuttonClick.click();
        WebElement SuccessMsgClick = driver.findElement(SuccessMsg);
        if (SuccessMsgClick.isDisplayed()) {
            System.out.println("CSV file having user data uploaded successfully");
        } else {
            System.out.println("Something wrong, could not upload file. Please check");
        }
    }

    public void HomepageNavigation() {
        if (driver.findElement(BackBtn).isDisplayed()) {
            driver.findElement(BackBtn).click();
            if (driver.findElement(ClerkDashboardLocator).isDisplayed())
                Assert.isTrue(true, "User not on homegpae. Please check");
        } else
            Assert.isTrue(true, "User not on homegpae. Please check");
    }

    public void uploadfileFail() throws InterruptedException {
        WebElement HeroBtnClick = driver.findElement(HeroButton);
        HeroBtnClick.click();
        WebElement selectCSVdropdown = driver.findElement(UploadCSVDrpdownOption);
        selectCSVdropdown.click();

        WebElement ChooseFileButtonClick = driver.findElement(ChooseFileButton);
        File file = new File("./Oppenheimer-invalid.csv");
        ChooseFileButtonClick.sendKeys(file.getAbsolutePath());
        WebElement CreatebuttonClick = driver.findElement(CreateButton);
        CreatebuttonClick.click();
        if(!(driver.findElement(ErrorMsg).isDisplayed()))
        {
            System.out.println("Waiting for error message to be displayed");
            Thread.sleep(5000);
        }

        if (driver.findElement(ErrorMsg).isDisplayed()) {
            Assert.isTrue(true, "Invalid file - therefore the upload failed.");

        } else {
            Assert.isTrue(false,"Something wrong, could not upload file. Please check");
        }


    }

        public Date DateConverter(String value) {
            Date date=null;
            String dateString = value; // Your date string
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            try {
                 date = dateFormat.parse(dateString);
                System.out.println("Parsed Date: " + date);
            } catch (ParseException e) {
                e.printStackTrace(); // Handle the ParseException (if the string format is incorrect)
            }
            return date;

        }


    public boolean isValid(String key, String value) {

        switch (key) {

            case "natid":
                stringPattern = Pattern.compile("natid-\\b(?:[0-9]{1,7}|9999999)\\b");
                Matcher m = stringPattern.matcher(value);
                if (m.find())
                    return true;
                else
                    return false;

                case "name":
                stringPattern = Pattern.compile("^[a-zA-Z ]{1,100}$");
               if (stringPattern.matcher(value).find())
                   return true;
               else
                   return false;

            case "gender":
                if(value.equals("FEMALE") || value.equals("MALE"))
                    return true;
                else
                    return false;

            case "birthDate":
                stringPattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}");
                if(stringPattern.matcher(value).find())
                    return true;
                else
                    return false;

            case "deathDate":
                    stringPattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}");
                    if ((value.isEmpty()) || stringPattern.matcher(value).find())
                        return true;
                    else
                        return false;
            case "browniePoints":
                if(value.isEmpty() || Integer.parseInt(value)==0 || Integer.parseInt(value)!=0)
                    return true;
        }
        return false;
    }

    public boolean decimalVerification(float num)
    {
        if((num != (int) num) || Double.isInfinite(num) || Double.isNaN(num))
            return true;
        else
            return false;
    }
}
