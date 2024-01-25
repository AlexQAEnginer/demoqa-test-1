package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    public RegistrationPage openPage(){

        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('.Google-Ad').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value){
        $("#firstName").setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value){
        $("#lastName").setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value){
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

}
