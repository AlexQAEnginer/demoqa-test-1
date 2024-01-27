package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalenderComponet;
import pages.components.RegistrationResultsModal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    CalenderComponet calenderComponet = new CalenderComponet();
    RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();

    private SelenideElement
            dateOfBirthInput = $("#dateOfBirthInput"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('.Google-Ad').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        $("#userEmail").setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        $("#genterWrapper").$(byText(value)).click();

        return this;
    }

    public RegistrationPage setPhone(String value) {
        $("#userNumber").setValue(value);

        return this;
    }

    public RegistrationPage setBathDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calenderComponet.setDate(day, month, year);
        return this;
    }
    public RegistrationPage verifyResultsModalAppears (){
        registrationResultsModal.verifyModalAppears();
        return this;
    }
    public RegistrationPage verifyResult(String key, String value){
        registrationResultsModal.verifyResult(key,value);
        return this;
    }
}
