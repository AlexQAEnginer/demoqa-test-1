package junit;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FirstTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fistTest(){
        open( "https://github.com/");
        $("span.flex-1").click();
        $("#query-builder-test").setValue("Selenide").pressEnter();
        $$("[data-testid=results-list]").first().$("a").click();
        sleep(50000);

    }
}

