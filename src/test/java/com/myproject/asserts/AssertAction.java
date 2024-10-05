package com.myproject.asserts;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class AssertAction {

    public void verifyresponsebody(String actual, String expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void verifyresponsebody(int actual, int expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void verifyStausCode(Response response, Integer expected) {
        assertEquals(response.getStatusCode(), expected);
    }
}
