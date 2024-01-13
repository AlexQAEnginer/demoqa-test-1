package junit;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;

public class SecondTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fistTest(){
        open( "https://github.com/");
        $$("div.header-menu-wrapper ul li.HeaderMenu-item").get(1).hover();
        sleep(5000); //ставлю слипы чтобы было наглядно видно как отрабытвают команды
        $$("div.header-menu-wrapper ul li.HeaderMenu-item").get(1).$$("div.HeaderMenu-dropdown ul li").first().click();
        sleep(5000);
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered"));
        sleep(5000);
    }
}
