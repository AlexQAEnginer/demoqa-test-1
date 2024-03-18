package junit;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.RegistrationPage;
import pages.components.TestData;

import java.util.ArrayList;
import java.util.HashMap;


public class TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData data = new TestData();


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadTimeout = 50000;
    }
    @BeforeEach
    void beforeEachTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}
