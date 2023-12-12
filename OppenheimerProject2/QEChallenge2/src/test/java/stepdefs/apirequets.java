package stepdefs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasValue;

public class apirequets {
    @Test
    public void PostVoucherSuccess()
    {
        File payload= new File("Payload.rtf");
        Response response = given().
        contentType(ContentType.JSON).
                body(payload).
                when().
                post("http://localhost:9997/api/v1/hero/vouchers");
        if(response.statusCode()==200)
            System.out.println("Record successfully inserted");
        else
            System.out.println("Insert with new natid");
    }

    @Test
    public void PostVoucherFail()
    {
        File payload= new File("Payload.rtf");
        Response response = given().
                contentType(ContentType.JSON).
                body(payload).
                when().
                post("http://localhost:9997/api/v1/hero/vouchers");
        if(response.statusCode()==400) {
            System.out.println("Voucher Name cannot be blank");
        }
        else
            System.out.println("Insert with new natid");
    }

    @Test
    public void GetVoucher()
    {
                given().
                when().
                get("http://localhost:9997/api/v1/hero/owe-money?natid=natid-12").
                then().
                assertThat().
                statusCode(500);
    }

    @Test
    public void GetPersonType()
    {
         given().
                when().
                get("http://localhost:9997/api/v1/voucher/by-person-and-type/").
                then().
                body("data.name",hasItem("hello")).
                 and().
                 body("data.voucherType",hasItem("TRAVEL")).
                 and().
                 body("data.count",hasItem(1)).
                 and().statusCode(200);
    }

}
