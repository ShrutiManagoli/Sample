package stepdefs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class VoucherIT {
    @Test
    public void postVoucherSuccess() {
        File payload = new File("Payload.rtf");
        int count = ClerkLoginStepDef.CountRows("vouchers");
        given().contentType(ContentType.JSON)
                .body(payload)
            .when()
                .post("http://localhost:9997/api/v1/hero/vouchers")
            .then()
                .statusCode(200);

        int afterCount = ClerkLoginStepDef.CountRows("vouchers");
        Assert.assertEquals(afterCount, count + 1);
    }

    @Test
    public void postVoucherFail() {
        File payload = new File("Payload.rtf");
        int count = ClerkLoginStepDef.CountRows("vouchers");
        Response response = given().contentType(ContentType.JSON).body(payload).when().post("http://localhost:9997/api/v1/hero/vouchers");

        if (response.statusCode() == 400) {
            System.out.println("Voucher Name cannot be blank");
            int afterCount = ClerkLoginStepDef.CountRows("vouchers");
            if (afterCount == count) {
                System.out.println("Record not inserted in db when voucher ID is null");
                Assert.assertTrue(true);
            }

        } else Assert.assertTrue(false);
        System.out.println("Insert with new natid");
    }

    @Test
    public void GetVoucher() {
        given().when().get("http://localhost:9997/api/v1/hero/owe-money?natid=natid-12").then().assertThat().statusCode(500);
    }

    @Test
    public void GetPersonType() {
        given().when().get("http://localhost:9997/api/v1/voucher/by-person-and-type/").then().body("data.name", hasItem("hello")).and().body("data.voucherType", hasItem("TRAVEL")).and().body("data.count", hasItem(1)).and().statusCode(200);
    }

}
