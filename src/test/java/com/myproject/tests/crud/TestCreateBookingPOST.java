package com.myproject.tests.crud;

import com.myproject.base.BaseTest;
import com.myproject.endpoints.APIConstants;
import com.myproject.pojos.BookingResponse;
import com.myproject.utils.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;


public class TestCreateBookingPOST extends BaseTest {
    @Owner("Darshan")
    @Description("Verify that POST request is working fine")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void testVerifyCreateBookingPOST01() {
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
    }
}
