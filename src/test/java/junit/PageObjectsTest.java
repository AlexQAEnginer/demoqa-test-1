package junit;

import org.junit.jupiter.api.Test;

public class PageObjectsTest extends TestBase {
    String setFirstName = "Alex";

    @Test
    void fistTest() {

        registrationPage.openPage()
                .setFirstName("Alex")
                .setLastName("Kozharin")
                .setEmail("Lekseu2007@yandex.ru")
                .setGender("Male")
                .setPhone("9080551234")
                .setBathDate("06", "July", "1992")
                .setSubjects("Hindi")
                .setHobbies("Sports")
                .setUploadPicture("test.png")
                .setAddress("information")
                .setState("Haryana")
                .setCity("Karnal")
                .submitClick()
                .verifyResultsModalAppears()
                .verifyResult("Student Name", "Alex Kozharin")
                .verifyResult("Student Email", "Lekseu2007@yandex.ru")
                .verifyResult("Gender", "Male")
                .verifyResult("Mobile", "9080551234")
                .verifyResult("Subjects", "Hindi")
                .verifyResult("Hobbies", "Sports")
                .verifyResult("Picture", "test.png")
                .verifyResult("Address", "information")
                .verifyResult("State and City", "Haryana Karnal");
    }
}

