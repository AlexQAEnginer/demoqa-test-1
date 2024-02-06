package junit;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.error.ShouldHave.shouldHave;

public class TestBankRF {
    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
    }
    @Test
    void career(){
        open("https://domrfbank.ru/");
        $$("ul.header__contacts li").first().click();
        $(".about__info-content").shouldHave(text("Информация о банке"));
        $(".helpful__content").shouldHave(text("Карьера")).scrollIntoView(true).shouldBe(visible);
        $("[href=\"https://дом.рф/career/vacancies/?city=all&company=%D0%94%D0%9E%D0%9C.%D0%A0%D0%A4&department=all&query=&utm_source=domrfbank.ru&utm_medium=referral&utm_campaign=ref_606_dom.rf_rf_page.link&utm_content=domrfbank.ru/about/\"]").click();
        switchTo().window(1);
        $("input[name=\"form[LAST_NAME]\"]").setValue("Кожарин");
        $("input[name=\"form[FIRST_NAME]\"]").setValue("Алексей");
        $(".dp__input_wrap").click();
        $$("div.dp__month_year_row div").get(3).click();
        $$("div.dp__overlay div.dp__overlay_cell").get(42).shouldHave(text("1992")).click();
        $$("div.dp__month_year_row div").get(2).click();
        $$("div.dp__overlay_container div.dp__overlay_cell").get(5).shouldHave(text("июль")).click();
        $$("div.dp__calendar div.dp__cell_inner").get(7).shouldHave(text("6")).click();
        $(".js--dp-apply").click();
        $("[name=\"form[CITY]\"]").click();
        $("[name=\"form[CITY]\"]").setValue("Челябинск");
        $("[name=\"form[CITY]\"]").click();
        $("input[name=\"form[EMAIL]\"]").setValue("Lekseu2007@yandex.ru");
        $("[name=\"form[PHONE]\"]").click();
        $("input.input__field[name=\"form[PHONE]\"]").setValue("9080551234");
        $("[name=\"form[PROFF_INT]\"]").click();
        $("[name=\"form[PROFF_INT]\"]").setValue("Информационные технологии");
        $("[name=\"form[PROFF_INT]\"]").click();
        $("[name=\"form[ABOUT]\"]").setValue("Хочу работать в лучшем банке мира:)))) ссылка на видео автотеста:.....)))");
        $("input[type=\"file\"]").uploadFromClasspath("example/Резюме_Тестировщик_ПО_QA_Enginner_Алексей_Владимирович_Кожарин_от.pdf");
        $(".form__conf-policy div.checkbox__field").click();
        sleep(20000);
    }
}
