package junit;

import org.junit.jupiter.api.Test;

public class PageObjectsTest extends TestBase {

    @Test
    void fistTest() {

        registrationPage.openPage()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setEmail(data.email)
                .setGender("Male")
                .setPhone("9080551234")
                .setBathDate("06", "July", "1992")
                .setSubjects("Hindi")
                .setHobbies("Sports")
                .setUploadPicture("test.png")
                .setAddress(data.streetAddress)
                .setState("Haryana")
                .setCity("Karnal")
                .submitClick()
                .verifyResultsModalAppears()
                .verifyResult("Student Name", data.firstName + " " + data.lastName)
                .verifyResult("Student Email", data.email)
                .verifyResult("Gender", "Male")
                .verifyResult("Mobile", "9080551234")
                .verifyResult("Subjects", "Hindi")
                .verifyResult("Hobbies", "Sports")
                .verifyResult("Picture", "test.png")
                .verifyResult("Address", data.streetAddress)
                .verifyResult("State and City", "Haryana Karnal");
    }
}

