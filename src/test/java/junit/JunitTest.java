package junit;


import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class JunitTest {
@Test
    void fistTest(){

    open("https://demoqa.com/text-box");

    $("#userName").setValue("Alexey Kozharin");
    $("#userEmail").setValue("Lekseu7@yandex.ru");
    $("#currentAddress").setValue("Pytevay 18A");
    $("#permanentAddress").setValue("70");

    sleep(15000);



}

}
