package junit;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;


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

