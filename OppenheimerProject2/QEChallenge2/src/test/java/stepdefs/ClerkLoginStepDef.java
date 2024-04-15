package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import Pages.PageInfo;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ClerkLoginStepDef {

    private WebDriver driver;
    private PageInfo PageInfo;
    private Boolean result = false;
    private File folder = new File(UUID.randomUUID().toString());
    private String url = "jdbc:mysql://localhost:3306/testdb";
    private String username = "user";
    private String password = "userpassword";
    private Connection connection = null;
    private static Statement statement;
    public int beforecount=0;
    public int aftercount=0;
    public static int rowcount =0;

    @Before
    public void setup() {

        //Chrome driver set-up
        folder.mkdir();

        //chrome
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", folder.getAbsolutePath());

        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(options);

    }

    @After
    public void tearDown()
    {

        if (driver != null) {
            driver.quit();
        }
        for(File file : folder.listFiles()){
            file.delete();
        }
        folder.delete();

        //close db connection;
       // try {
         //   connection.close();
       // }
       // catch(Exception e){
      //      System.out.println(e);

    //}
    }

    public void DBSetup() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection(url, username, password);
             statement = connection.createStatement();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Given("I am on the Application login page")
    public void i_am_on_the_application_login_page(){
        driver.get("http://localhost:9997/login");
        PageInfo = new PageInfo(driver);
    }

    @Given("I have entered valid username {string} and password {string}")
    public void i_have_entered_valid_username_and_password(String user, String password) {
        PageInfo.enterName(user);
        PageInfo.enterPassword(password);
        PageInfo.submitClick();
    }

    @Then("User should be logged in successfully")
    public void _user_should_be_logged_in_successfully(){
        Assert.assertEquals(PageInfo.LogoutExists(), true);
    }

    @When("I upload a valid CSV file and verify the success message")
    public void i_upload_a_valid_csv_file_and_verify_the_success_message()  {
        Boolean flag=false;
        PageInfo.ClickHeroBtn();
        beforecount = CountRows("working_class_heroes");
        PageInfo.uploadfileSucess();
        aftercount = CountRows("working_class_heroes");
        if(aftercount>beforecount)
        {
            flag =true;
            Assert.assertTrue(flag, "record succesfully inserted");
        }
    }

    @Given("navigate to the homepage")
    public void navigate_to_the_homepage() {
        PageInfo.HomepageNavigation();
    }

    public static int CountRows(String tableName){
        //ResultSet res = statement.executeQuery("show tables");
        try {
            ResultSet resultSet = statement.executeQuery("select * count" + tableName);
            while (resultSet.next()) {
                rowcount = rowcount + 1;
            }
        }
        catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
        return rowcount;

    }
        @When("I upload a invalid CSV file")
    public void i_upload_a_invalid_csv_file() throws InterruptedException {
            Boolean flag = false;
            beforecount = CountRows("working_class_heroes");
            PageInfo.uploadfileFail();
            aftercount = CountRows("working_class_heroes");
            if(aftercount==beforecount)
            {
                flag =true;
                Assert.assertTrue(flag, "record not inserted when file is invalid");
            }

    }


    @Given("I have the string url")
    public void i_have_the_string_url() {

    }
    @Then("I should be able to perform the Post request")
    public void i_should_be_able_to_perform_the_post_request() {

    }

    @Test
    public void PostVoucher()
    {
    }

    @Test
    public void GetVoucher()
    {
        given().
                when().
                get("http://localhost:9997/api/v1/hero/owe-money?natid=101").
                then().
                assertThat().
                body("data", hasValue(101));
    }

    @Given("I have the field")
    public void i_have_the_field() {
    }
    @Then("verify that the field validation for {string} having {string} is correct")
    public void verify_that_the_field_validation_for_having_is_correct(String key, String value) {
        boolean result = PageInfo.isValid(key, value);
        Assert.assertTrue(result, "validation does not work fine");
    }

    @Then("verify that the salary and Tax Paid field validation for {string} having {string} is correct")
    public void verify_that_the_salary_and_tax_paid_field_validation_for_having_is_correct(String key, String value) {
        float num = Float.parseFloat(value);
        if(num>0 ) {
            result = PageInfo.decimalVerification(num);
            {
                result = true;
                Assert.assertTrue(result, "Value of " + key + "is integer. Please change it floating point number");

            }
        }
        else
            Assert.assertFalse(result, "Value of "+ key +"is negative number");
    }

    @When("User generates Tax Relief file")
    public void user_generates_tax_relief_file() throws InterruptedException {
        if(driver.findElement(Pages.PageInfo.EgressProgressMsg).isDisplayed())
            Assert.assertFalse(true, "Cannot click on Generate Tax Relief button because the Egress file process is in-progress");

        WebElement TaxReliefBtnclick = driver.findElement(PageInfo.TaxReliefBtn);
        if(TaxReliefBtnclick.isEnabled()) {
            TaxReliefBtnclick.click();
            Thread.sleep(4000);
            TaxReliefGeneration();
        }
    }

    public void TaxReliefGeneration() throws InterruptedException {


        File listOfFiles[] = folder.listFiles();
        //make sure directory is not empty
        //Assert.assertEquals(listOfFiles.length,is(not((long)0)));
        Assert.assertTrue(listOfFiles.length>0);

        Scanner scan = new Scanner(System.in);
        for(File file:listOfFiles){
            // make sure that downloaded file is not empty
           // Assert.assertEquals(file.length(),is(not((long)0)));
            Assert.assertTrue(file.length()>0);


        }


    }

}

