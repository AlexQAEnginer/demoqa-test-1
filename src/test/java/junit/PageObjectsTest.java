package junit;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PageObjectsTest extends TestBase {

    @Test
    void fistTest() {

        registrationPage.openPage()
                .setFirstName("Alex")
                .setLastName("Kozharin")
                .setEmail("Lekseu2007@yandex.ru")
                .setGender("Male")
                .setPhone("9080551234")
                .setBathDate("06", "july", "1992");


        $("#subjectsInput").setValue("Hindi").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/img/test.png"));
        $("#currentAddress").setValue("information");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();
        $("#submit").click();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", "Alex Kozharin")
                .verifyResult("Student Email", "Lekseu2007@yandex.ru");


        sleep(10000);
    }
}

