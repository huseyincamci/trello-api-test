package com.trellorest;

import com.trellorest.util.PropertyUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Before;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BaseTest {

    protected Logger _logger;
    protected HashMap<String, String> globalParams;

    @Before
    public void setUp() throws IOException {
        PropertyUtil util = new PropertyUtil("config.properties");
        baseURI = "https://api.trello.com/1/";
        globalParams = new HashMap<String, String>();
        globalParams.put("key", util.getPropValue("key"));
        globalParams.put("token", util.getPropValue("token"));
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        _logger = Logger.getLogger(BaseTest.class);
        BasicConfigurator.configure();
    }

    public RequestSpecification request() {
        return given().queryParams(globalParams)
                .contentType(ContentType.JSON);
    }

    protected String getRandomValue(List<String> values) {
        return values.get(getRandomInt(0, values.size() - 1));
    }

    protected static int getRandomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random rnd = new Random();
        return rnd.nextInt((max - min) + 1) + min;
    }
}
