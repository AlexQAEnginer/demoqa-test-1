package junit;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static jdk.dynalink.StandardNamespace.ELEMENT;

public class JunitTest {
@BeforeAll
static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadTimeout = 50000;
        }

@Test
    void fistTest(){

    open("/text-box");
    $("#userName").setValue("Alexey Kozharin");
    $("#userEmail").setValue("Lekseu7@yandex.ru");
    $("#currentAddress").setValue("Pytevay 18A");
    $("#permanentAddress").setValue("70");
    $("#submit").click();


}
}

