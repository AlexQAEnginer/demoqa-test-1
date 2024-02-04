package junit;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFilesTest {

    @Test
    void SelenideDownloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("[data-testid=raw-button]").download();
        try (InputStream is = new FileInputStream(downloadedFile)){
            byte[] bytes = is.readAllBytes();
            String textContent = new String(bytes, StandardCharsets.UTF_8);
            assertThat(textContent).contains("Ask JUnit 5 related questions on [StackOverflow] or chat with the community on [Gitter].");
        }
    }
}
