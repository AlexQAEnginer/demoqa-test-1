package junit;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ThirdTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
    }
    @Test
    void fistTest(){
        open( "/drag_and_drop");
        $("#column-a").dragAndDropTo($("#column-b"));
        sleep(5000); //ставлю слипы чтобы было наглядно видно как отрабытывают команды
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}

