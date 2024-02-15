package junit;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class ParameterizedSet {

    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        open("https://www.google.com/");
    }

    //@CsvSource({
    //        "Велосипеды, www.velostrana.ru",
    //        "Мячики, www.ozon.ru"
    //})

    @CsvFileSource(resources = "/example/testDataBikesAppearWhenSearchingInChromeBrowser.csv")
    @ParameterizedTest(name = "При вводе {0} отображается сайт {1}")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void BikesAppearWhenSearchingInChromeBrowser(
            String productName,
            String urlName
    ) {
        $("[name=q]").setValue(productName).pressEnter();
        $("[id=rso]").shouldHave(text(urlName));
    }
}
