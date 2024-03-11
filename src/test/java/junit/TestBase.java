package junit;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import pages.RegistrationPage;
import pages.components.TestData;

import java.util.Locale;

public class TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData data = new TestData();


    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadTimeout = 50000;
    }


}
