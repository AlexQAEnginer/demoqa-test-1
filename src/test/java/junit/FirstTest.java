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
        $$("ul.UnderlineNav-body li").get(5).$("#wiki-tab").click();
        $$("ul[data-filterable-type=substring] li").get(17).$("a.Truncate-text").shouldHave(text("Selenide vs Selenium")).click();
        $("div.markdown-body p").shouldHave(text("This page brings an overview of how Selenide API is simpler and more powerful than"));
        sleep(5000);
    }
}

