package pages.components;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {

    Faker faker = new Faker(new Locale("en-US"));

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String streetAddress = faker.address().streetAddress();
    public String email = faker.internet().emailAddress();
}
