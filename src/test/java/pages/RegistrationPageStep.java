package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegistrationPageStep {
    public RegistrationPageStep registrationPageStep = new RegistrationPageStep();
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName");

    @Step
    public RegistrationPageStep openStep() {
        step("Открыть главную страницу", () -> {
            open("/automation-practice-form");
        });
        step("Проверить наличие заголовка Student Registration Form", () -> {
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });
        step("Удалить рекламу", () -> {
            executeJavaScript("$('.Google-Ad').remove()");
        });
        return this;
    }

    @Step
    public void setFirst(String value) {
        step("Открываем главную страницу, проверяем фразу, удаляем рекламу", () -> {
            firstNameInput.setValue(value);
        });
    }

    @Step
    public void setFirstTwo(String value) {
        step("Открываем главную страницу, проверяем фразу, удаляем рекламу", () -> {
            lastNameInput.setValue(value);
        });
    }
}


