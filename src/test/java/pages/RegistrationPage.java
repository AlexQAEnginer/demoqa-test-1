package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalenderComponet;
import pages.components.RegistrationResultsModal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegistrationPage {
    private CalenderComponet calenderComponet = new CalenderComponet();
    private RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();
    private SelenideElement
            dateOfBirthInput = $("#dateOfBirthInput"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            uploadImage = $("#uploadPicture");

    public RegistrationPage openPage() {
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

        public RegistrationPage setFirstName (String value){
            firstNameInput.setValue(value);

            return this;
        }

        public RegistrationPage setLastName (String value){
            lastNameInput.setValue(value);

            return this;
        }

        public RegistrationPage setEmail (String value){
            $("#userEmail").setValue(value);

            return this;
        }

        public RegistrationPage setGender (String value){
            $("#genterWrapper").$(byText(value)).click();

            return this;
        }

        public RegistrationPage setPhone (String value){
            $("#userNumber").setValue(value);

            return this;
        }

        public RegistrationPage setBathDate (String day, String month, String year){
            dateOfBirthInput.click();
            calenderComponet.setDate(day, month, year);

            return this;
        }

        public RegistrationPage verifyResultsModalAppears () {
            registrationResultsModal.verifyModalAppears();

            return this;
        }

        public RegistrationPage verifyResult (String key, String value){
            registrationResultsModal.verifyResult(key, value);

            return this;
        }

        public RegistrationPage setSubjects (String value){
            $("#subjectsInput").setValue(value).pressEnter();

            return this;
        }

        public RegistrationPage setHobbies (String value){
            $("#hobbiesWrapper").$(byText(value)).click();

            return this;
        }

        public RegistrationPage setUploadPicture (String value){
            uploadImage.uploadFromClasspath("img/" + value);

            return this;
        }

        public RegistrationPage setAddress (String value){
            $("#currentAddress").setValue(value);

            return this;
        }

        public RegistrationPage setState (String value){
            $("#state").click();
            $("#stateCity-wrapper")
                    .$(byText(value)).click();

            return this;
        }

        public RegistrationPage setCity (String value){
            $("#city").click();
            $("#stateCity-wrapper")
                    .$(byText(value)).click();

            return this;
        }

        public RegistrationPage submitClick () {
            $("#submit").click();

            return this;
        }
}