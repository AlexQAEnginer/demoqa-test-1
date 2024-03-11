package junit;

import org.junit.jupiter.api.Test;
import pages.RegistrationPageStep;

public class RegistrationPageObjectStep extends RegistrationPageStep {
    @Test
    void testStep() {
        registrationPageStep.openStep()
                .setFirst("Alex");
    }

}
