package com.myproject.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.myproject.pojos.*;

public class PayloadManager {
    Gson gson;

    //ser & der
//Converting Java object int string
    public String createPayloadbookingAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Darshan");
        booking.setLastname("S S");
        booking.setDepositpaid(true);
        booking.setTotalprice(2999);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingdates);

        booking.setAdditionalneeds("Breakfast, Lunch, dinner");

        System.out.println(booking);

        gson = new Gson();
        String gsonPayload = gson.toJson(booking);
//        System.out.println(gsonPayload);

        return gsonPayload;
    }

    public String createPayloadbookingAsStringfaker() {
        Faker faker = new Faker();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setDepositpaid(true);
        booking.setTotalprice(faker.random().nextInt(1000));

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingdates);

        booking.setAdditionalneeds("Breakfast, Lunch, dinner");

        System.out.println(booking);

        Gson gson = new Gson();
        String gsonPayload = gson.toJson(booking);
//        System.out.println(gsonPayload);

        return gsonPayload;
    }

    public BookingResponse bookingresponse(String responseString) {
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;

    }

    //get token
    public String setAuthPayload() {
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");

        gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);

        return jsonPayloadString;
    }


    public String getTokenFromJson(String tokenResponse) {
        gson = new Gson();

        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse, TokenResponse.class);
        return tokenResponse1.getToken();

    }

    public Booking getResponseFromJson(String getResponse) {
        gson = new Gson();
        Booking booking = gson.fromJson(getResponse, Booking.class);

        return booking;

    }

    public String fullUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("James");
        booking.setLastname("Bond");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);
    }
}
