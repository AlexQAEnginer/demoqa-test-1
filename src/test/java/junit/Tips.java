package selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;

import java.io.*;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// this is not a full list, just the most common
public class Tips {

    void browser_command_examples() {
        open("https://google.com");
        open("/customer/orders");     // -Dselenide.baseUrl=http://123.23.23.1, приминимо в случае, когда тесты воспроизводятся на разных окружениях.
        open("/", AuthenticationType.BASIC,
                new BasicAuthCredentials("", "user", "password")); // когда всплывает окно авторизации.

        Selenide.back(); // вернуться назад
        Selenide.refresh(); //обновить страницу

        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        executeJavaScript("sessionStorage.clear();"); // в целом все три команды применяются одновременно, чтобы почитить куки/кеши

        Selenide.confirm(); // OK in alert dialogs
        Selenide.dismiss(); // Cancel in alert dialogs // команды для сплывающих окон, первая кликает ок, вторая кликает закрыть

        Selenide.closeWindow(); // close active tab // закрыть одно окно
        Selenide.closeWebDriver(); // close browser completely // закрыть браузер

        Selenide.switchTo().frame("new"); // перейти во фрейм
        Selenide.switchTo().defaultContent(); // return from frame back to the main DOM // выйти из фрейма

        Selenide.switchTo().window("The Internet"); // перейти на следующее окно

        var cookie = new Cookie("foo", "bar");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie); // установить куки


    }

    void selectors_examples() {
        $("div").click();
        element("div").click(); //селекторы работают одинаково, находят элемент, кликают по нему, второй селектор используется на языке - Котлин

        $("div", 2).click(); // the third div // выбирает нужный объект по счёту, не обязательно div

        $x("//h1/div").click();
        $(byXpath("//h1/div")).click(); // структура селектора, если пользуешься икспасом, для обоих селекторов

        $(byText("full text")).click(); //найти текст на странице и кликнуть
        $(withText("ull tex")).click(); //найти часть текста на странице и кликнуть

        $(byTagAndText("div", "full text")); // найти текст через тег
        $(withTagAndText("div", "ull text")); // найти часть текста через тег

        $("").parent(); // это поиск по родителю
        $("").sibling(1); // поиск по детяем вниз
        $("").preceding(1); // поиск по детяем вверх
        $("").closest("div");
        $("").ancestor("div"); //  closest и ancestor одно и то же, помогают перейти выше по дереву
        $("div:last-child"); // выбор последнего ребёнка

        $("div").$("h1").find(byText("abc")).click(); //выбирается поиск по div, потом по h1 внутри div, находим текст abc || fibd тоже самое, что $
        // very optional
        $(byAttribute("abc", "x")).click(); // такми образом можно производить поиск по атрибутам
        $("[abc=x]").click(); // аналогично верхнему, но сокращённо

        $(byId("mytext")).click(); // аналогично поиск по id
        $("#mytext").click(); //аналогично верхнему но сокращённо

        $(byClassName("red")).click(); //аналогично поиск по классу
        $(".red").click(); // аналогично верхнему, но сокращённо
    }

    void actions_examples() {
        $("").click(); //клик
        $("").doubleClick(); //двойной клик
        $("").contextClick(); //клик правой кнопкой

        $("").hover();//поднести мышку и не кликать

        $("").setValue("text");//удаляет всё в поле, если там что-то есть и напишет заново
        $("").append("text");//добавит часть теста в конец уже написанного
        $("").clear();// очищает поле, не факт что сработает
        $("").setValue(""); // очищает поле тоже, может сработать

        $("div").sendKeys("c"); // эмуляци нажатия на клавишу
        actions().sendKeys("c").perform(); //тоже эмуляция нажатия клавиши, но без привязки к элементу
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F камбинация клавишь без привязки к элементу
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));// как мне кажется, можно выбрать элемент и нажать комбинацию клавишь

        $("").pressEnter();//нажать enter
        $("").pressEscape();//нажать назад
        $("").pressTab();//нажать tab


        // complex actions with keybord and mouse, example
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();//взять объект и потянуть в нужное место

        // old html actions don't work with many modern frameworks
        $("").selectOption("dropdown_option");// используется для стархы дроуп даунов - выпадашек
        $("").selectRadio("radio_options");// используется для стархы чек-боксов

    }

    void assertions_examples() {
        $("").shouldBe(visible);// далее проверки положительные и атрицательные, на наличие объектов и отсутствие\ запрограмирован таймаут 4 секунды
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);


        //longer timeouts
        $("").shouldBe(visible, Duration.ofSeconds(30)); //можно сделать таймаут больше 4 секунд, либо меньше

    }

    void conditions_examples() {
        $("").shouldBe(visible);
        $("").shouldBe(hidden);

        $("").shouldHave(text("abc"));
        $("").shouldHave(exactText("abc"));//точный поиск текста
        $("").shouldHave(textCaseSensitive("abc"));
        $("").shouldHave(exactTextCaseSensitive("abc"));
        $("").should(matchText("[0-9]abc$"));

        $("").shouldHave(cssClass("red")); //поиск наличие класса
        $("").shouldHave(cssValue("font-size", "12")); //поиск объекта с определенными свойствами

        $("").shouldHave(value("25")); //проверить, что написано в поле
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty);//проверить, что поле пустое

        $("").shouldHave(attribute("disabled"));//проверка атрибута, что он существует
        $("").shouldHave(attribute("name", "example"));//провекрка элемента по значениям
        $("").shouldHave(attributeMatching("name", "[0-9]abc$")); //ввести точное название атрибута

        $("").shouldBe(checked); // for checkboxes чек-бокс включен
        $("").shouldNotBe(checked); // чек-бокс не включен

        // Warning! Only checks if it is in DOM, not if it is visible! You don't need it in most tests!
        $("").should(exist);//проверить существует ли скрытый объект

        // Warning! Checks only the "disabled" attribute! Will not work with many modern frameworks
        $("").shouldBe(disabled); //определить кнопка кликабельная или нет
        $("").shouldBe(enabled); //определить кнопка кликабельная или нет
    }

    void collections_examples() {

        $$("div"); // does nothing!

        $$x("//div"); // by XPath

        // selections
        $$("div").filterBy(text("123")).shouldHave(size(1));
        $$("div").excludeWith(text("123")).shouldHave(size(1));

        $$("div").first().click();
        elements("div").first().click();
        // $("div").click();
        $$("div").last().click();
        $$("div").get(1).click(); // the second! (start with 0)
        $("div", 1).click(); // same as previous
        $$("div").findBy(text("123")).click(); //  finds first

        // assertions
        $$("").shouldHave(size(0));
        $$("").shouldBe(CollectionCondition.empty); // the same

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));

        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

        $$("").shouldHave(itemWithText("Gamma")); // only one text

        $$("").shouldHave(sizeGreaterThan(0));
        $$("").shouldHave(sizeGreaterThanOrEqual(1));
        $$("").shouldHave(sizeLessThan(3));
        $$("").shouldHave(sizeLessThanOrEqual(2));


    }

    void file_operation_examples() throws FileNotFoundException {

        File file1 = $("a.fileLink").download(); // only for <a href=".."> links
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid

        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt");
        // don't forget to submit!
        $("uploadButton").click();
    }

    void javascript_examples() {
        executeJavaScript("alert('selenide')");
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);

    }
}