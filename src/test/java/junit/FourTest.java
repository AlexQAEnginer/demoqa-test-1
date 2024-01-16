package junit;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FourTest {
    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadTimeout = 50000;
    }

    @Test
    void fistTest(){

        open("/automation-practice-form");
        $("#firstName").setValue("Alexey");
        $("#lastName").setValue("Kozharin");
        $("#userEmail").setValue("Lekseu2007@yandex.ru");
        $("#genterWrapper").$(byText("Male")).click();
        sleep(10000);

    }
}

