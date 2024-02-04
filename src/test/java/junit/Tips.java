package junit;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

// this is not a full list, just the most common
public class Tips {

    //аннотации - метки
    @DisplayName("Имя теста, что он тестирует, ВАЖНО - аннотация интегррована с Allure")

    @Disabled("Исключает тест из проверки. Обычно прописывают почему, номер тиккета - бага, или задачи в рамках которой тест требуется поправить")

    @Tag("BLOCKER")

    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    // указывается функциональная серьёзность теста + через Gradle можно запустить определенный тест по тегу

    @CsvSource({
            "Велосипеды, www.velostrana.ru",
            "Мячики, www.ozon.ru"
    })
    //  @ParameterizedTest(name = "При вводе {0} отображается сайт {1}") - тест если участвует разная тест дата в рамках одного тест

    @CsvFileSource(resources = "/testDataBikesAppearWhenSearchingInChromeBrowser.csv")
    // - можно вытянуть данные для теста описанного сверху из файла в папке resources


    @BeforeAll // что-то выполняется до запуска всех тестов
    static void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @AfterAll // что-то выполняется после всех тестов
    static void tearDown() {
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
        // что-то выполняется перед запуском теста
    void beforeEachTest() {
        Configuration.browserSize = "1920x1080";
    }


    @AfterEach
        // что-то выполняется после каждого теста
    void afterEachTest() {
        Configuration.browserSize = "1920x1080";
    }

    // множество разных сравнений Assertions.assertTrue();

    //Дополнительно, проверить, что находимся на нужной странице:
    // какой-то код......
    // В конце проводим обязательно проверку чего-то, иначе тест не успеет отработать, например поиск текста на странице $().shouldHave(text();
    // после чего вводим следующее: String expectedUrl = "ссылка на сайт, которая должна быть равна нашей проверки";
    //                              String currentUrl = url();
    //                              assertEquals(expectedUrl, currentUrl);


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
        $("#mytext").click(); //аналогично верхнему, но сокращённо

        $(byClassName("red")).click(); //аналогично поиск по классу
        $(".red").click(); // аналогично верхнему, но сокращённо

        //Варианты работы с radio-button, если обычные селекторы не отрабатывают
        //$("#genterWrapper").$(byText("Male")).click(); - обратиться к общему контейнеру, найти там контейнер с кнопкой по названию
        //$("#radio_1").parent().click(); - вариант вернутся к родителю, часто срабатывает
        //$(byText("Male")).click(); - тоже часто срабатывает, выбрать сразу контейнер кнопки с тексом и кликнуть, не очень хороший вариант, bytext ищет первый текст с таким названием, возможно, такой текст может встретиться ранее
        $(".react-datepicker__year-select").selectOption("1992"); // отрабатывает в рамках выпадашек, например выбора даты рождения, нужно присутсвие двух селекторов родительского select и дочернего Option
        $(".selector_1:not(.selector_2)").click(); //ищет объект с селектором 1, но при этом в этом объекте не должно быть селектора 2
    }

    void actions_examples() {
        $("").click(); //клик
        $("").doubleClick(); //двойной клик
        $("").contextClick(); //клик правой кнопкой

        $("").hover();//поднести мышку и не кликать

        $("").setValue("text");//удаляет всё в поле, если там что-то есть и напишет заново
        $("").append("text");//добавит часть теста в конец уже написанного
        $("").clear();// очищает поле, не факт что сработает
        $("").setValue(""); // очищает поле тоже, может не сработать

        $("div").sendKeys("c"); // эмуляци нажатия на клавишу
        actions().sendKeys("c").perform(); //тоже эмуляция нажатия клавиши, но без привязки к элементу
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F камбинация клавишь без привязки к элементу
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));// как мне кажется, можно выбрать элемент и нажать комбинацию клавишь

        $("").pressEnter();//нажать enter
        $("").pressEscape();//нажать назад
        $("").pressTab();//нажать tab


        // complex actions with keybord and mouse, example
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();//перенести объект в нужное место
        $("#column-a").dragAndDropTo($("#column-b"));// перенос объекта на место другого объекта

        // old html actions don't work with many modern frameworks
        $("").selectOption("dropdown_option");// используется для старых дроуп даунов - выпадашек
        $("").selectRadio("radio_options");// используется для старых чек-боксов

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

        $$("div"); // does nothing! поиск колекции, если нет в конце операции, то команда бесполезная

        $$x("//div"); // by XPath то же самое но икспас

        // selections
        $$("div").filterBy(text("123")).shouldHave(size(1)); // filterBy ищет элементы которые удовлетворяют условия, которые написаны после него
        $$("div").excludeWith(text("123")).shouldHave(size(1)); // excludeWith ищет элементы которые НЕ удовлетворяют условия, которые написаны после него

        $$("div").first().click(); //выбрать первый элемент в коллекции
        elements("div").first().click(); // тоже самое, но через elements
        // $("div").click();
        $$("div").last().click();// выбрать последний элемент в коллекции
        $$("div").get(1).click(); // the second! (start with 0) выбрать конкретный элемент в колекции
        $("div", 1).click(); // same as previous сокращённо открыть первый элемент в коллекции
        $$("div").findBy(text("123")).click(); //  finds first findsBy находит самый первый элемент

        // assertions
        $$("").shouldHave(size(0)); //проверить размер коллекции
        $$("").shouldBe(CollectionCondition.empty); // the same думаю, что найти одинаковые коллекции

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma")); //найти текст, условие, если будет например 4 элемент "Threnama", то проверка НЕ сработает, а если например будет "Alfa3", "Beta2", то проверка сработает
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma")); // если нужен конретный текст, то воспользоваться этой командой

        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));// если порядок текста не важен, главное найти объекты в тексте, то воспользуемся этой командой
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));// проверяется с учётом регистра

        $$("").shouldHave(itemWithText("Gamma")); // only one text найти текст, не важно где он

        $$("").shouldHave(sizeGreaterThan(0)); // в колекции элементов больше чем  0
        $$("").shouldHave(sizeGreaterThanOrEqual(1)); //размер больше или равен 1
        $$("").shouldHave(sizeLessThan(3));// в колекции элементов меньше чем 3
        $$("").shouldHave(sizeLessThanOrEqual(2)); // меньше или равно 2


    }

    void file_operation_examples() throws Exception {

        File file1 = $("a.fileLink").download(); // only for <a href=".."> links простая ссылка для скачивания, кликнул - началась загрузка, на старых сайтах
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid более современная команда, работает в больших случаях

        File file = new File("src/test/resources/readme.txt"); //выбрать файл по конретному пути
        $("#file-upload").uploadFile(file); // загрузить файл который выбрал $("#uploadPicture").uploadFile(new File("src/test/resources/img/test.png"));
        $("#file-upload").uploadFromClasspath("img/test.png"); //автоматически выбрать файл из открывшейся папки
        // don't forget to submit!
        $("uploadButton").click(); // обязательно кликнуть кнопку


        open("https://torrent-games.link/8322-resident-evil-4-remake.html");
        File downloadedFile = $("#og-button1").download(); // пример, как скачать файл, ВНИМАНИЕ! - относится к атрибутам href=....  в этой же строчке требуется найти селектор!
        InputStream is = new FileInputStream(downloadedFile); // эта строчка и последующие открывает скаченный файл, трансформирует в стандарт кодировки UTF_8, после чего проверяет присутствие текста Test в файле
        byte[] bytes = is.readAllBytes();
        String textContent = new String(bytes, StandardCharsets.UTF_8);
        assertThat(textContent).contains("Test");
        is.close(); // если проверка успешна, закрывает файл, тест продолжается, если проверка не пройдена, тест упадёт (не принято использовать такой метод, но если потребуется)

        //  open("https://torrent-games.link/8322-resident-evil-4-remake.html");
        //  File downloadedFile = $("#og-button1").download(); // пример, как скачать файл
        //  try (InputStream is = new FileInputStream(downloadedFile)){ // эта строчка и последующие открывает скаченный файл, трансформирует в стандарт кодировки UTF_8, после чего проверяет присутствие текста Test в файле
        //     byte[] bytes = is.readAllBytes();
        //    String textContent = new String(bytes, StandardCharsets.UTF_8);
        //    assertThat(textContent).contains("Test"); //это считается правильная конструкция кода, пропускает шаг, если не проверка по текусту проходит не успешно
  //  }
}

    void javascript_examples() {
        executeJavaScript("alert('selenide')"); //команды которые запускают javascript
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);
        Selenide.executeJavaScript("$('#fixedban').remove()"); // - например такой командой можно удалить объект
    }
}