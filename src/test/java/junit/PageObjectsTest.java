package junit;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.io.File;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PageObjectsTest {
    RegistrationPage registrationPage = new RegistrationPage();


    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadTimeout = 50000;
    }

    @Test
    void fistTest() {

        registrationPage.openPage()
                .setFirstName("Alex")
                .setLastName("Kozharin")
                .setEmail("Lekseu2007@yandex.ru")
                .setGender("Male")
                .setPhone("9080551234");


        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1992");
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__day--006").click();
        $("#subjectsInput").setValue("Hindi").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/img/test.png"));
        $("#currentAddress").setValue("information");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();
        $("#submit").click();
        $(".modal-dialog").should(appear);
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".table").shouldHave(text("Male"), text("Sports"));
        sleep(10000);
    }
}

