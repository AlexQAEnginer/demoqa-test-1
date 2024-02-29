package junit;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import pages.RegistrationPage;
import pages.components.TestData;

import java.util.Locale;

public class TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData data = new TestData();

    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadTimeout = 50000;
    }


}
