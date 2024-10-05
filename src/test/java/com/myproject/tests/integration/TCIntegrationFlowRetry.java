package com.myproject.tests.integration;

import com.myproject.base.BaseTest;
import com.myproject.endpoints.APIConstants;
import com.myproject.listeners.RetryAnalyzer;
import com.myproject.pojos.Booking;
import com.myproject.pojos.BookingResponse;
import com.myproject.utils.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test(retryAnalyzer = RetryAnalyzer.class)
public class TCIntegrationFlowRetry extends BaseTest {

    @Test(groups = "integration", priority = 1)
    @Owner("Darshan")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext) {
        iTestContext.setAttribute("token", getToken());


        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadbookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(Integer.parseInt(PropertyReader.readProperty("booking.post.status.success")));

//        Default rest assured
        validatableResponse.body("booking.firstname", Matchers.equalTo(PropertyReader.readProperty("booking.post.firstname")));

        BookingResponse bookingResponse = payloadManager.bookingresponse(response.asString());

//        AsserttJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readProperty("booking.post.firstname"));

//        TestNG assertion
        assertAction.verifyStausCode(response, 200);

//Set the booking id
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());


    }

    @Test(groups = "integration", priority = 2)
    @Owner("Promode")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext) {
        Integer bookingId = (Integer) iTestContext.getAttribute("bookingid");

//        Get req
        String basePathGet = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId;
        System.out.println("=============================================================" + basePathGet);
        requestSpecification.basePath(basePathGet);

        response = RestAssured.given(requestSpecification).when().get();
        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJson(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readProperty("booking.post.firstname"));

    }

    @Test(groups = "integration", priority = 3)
    @Owner("Promode")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext) {
        System.out.println("Token -> " + iTestContext.getAttribute("token"));
        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basePathPUTPATCH);
        response = RestAssured.given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJson(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readProperty("booking.put.firstname"));
        assertThat(booking.getLastname()).isEqualTo(PropertyReader.readProperty("booking.put.lastname"));


    }

    @Test(groups = "integration", priority = 4)
    @Owner("Promode")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext) {
        String token = (String) iTestContext.getAttribute("token");

        Integer booingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + booingid;
        System.out.println(basePathDELETE);

        requestSpecification.basePath(basePathDELETE).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();

        validatableResponse.statusCode(200);

    }

}
