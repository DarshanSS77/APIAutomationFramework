package com.myproject.tests.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCreateBookingPOST {
    @Owner("Darshan")
    @Description("Verify that POST request is working fine")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void testVerifyCreateBookingPOST01() {
        Assert.assertEquals("true", "true");
    }
}
