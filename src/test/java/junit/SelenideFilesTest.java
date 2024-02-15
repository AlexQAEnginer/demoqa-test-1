package junit;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFilesTest {
    
    ClassLoader cl = SelenideFilesTest.class.getClassLoader();
    @Test
    void SelenideDownloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("[data-testid=raw-button]").download();
        try (InputStream is = new FileInputStream(downloadedFile)) {
            byte[] bytes = is.readAllBytes();
            String textContent = new String(bytes, StandardCharsets.UTF_8);
            assertThat(textContent).contains("Ask JUnit 5 related questions on [StackOverflow] or chat with the community on [Gitter].");
        }
    }
    @Test
    void SelenideUploadTest() throws Exception {
        open("https://xn--d1aqf.xn--p1ai/career/vacancies/?city=all&company=%D0%94%D0%9E%D0%9C.%D0%A0%D0%A4&department=all&query=&utm_source=domrfbank.ru&utm_medium=referral&utm_campaign=ref_606_dom.rf_rf_page.link&utm_content=domrfbank.ru/about/");
        $("input[type=\"file\"]").uploadFromClasspath("example/Резюме_Тестировщик_ПО_QA_Enginner_Алексей_Владимирович_Кожарин_от.pdf");
        $(".drop-uploaded__name").shouldHave(text("Резюме_Тестировщик_ПО_QA_Enginner_Алексей_Владимирович_Кожарин_от"));
    }
    @Test
    void SelenideDownloadPDF() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloadedPdf = $("[href='junit-user-guide-5.10.2.pdf']").download();
        PDF content = new PDF(downloadedPdf);
        assertThat(content.text).contains("Sam");
    }
    @Test
    void SelenideDownloadXls() throws Exception {
        open("https://itsm365.com/documents_rus/web/Content/import/import_org_file.htm");
        File downloadedXml = $("[href='../Resources/doc/import_ou_xlsx.xlsx']").download();
        XLS content = new XLS(downloadedXml);
        assertThat(content.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue()).contains("Коммерческий департамент");
    }
    @Test
    void SelenideDownloadCsv() throws Exception {
        open("https://itsm365.com/documents_rus/web/Content/import/import_org_file.htm");
        File downloadedCsv = $("[href='../Resources/doc/import_ou_csv.csv']").download();
        try (InputStream resource = new FileInputStream(downloadedCsv)) {
            CSVReader reader = new CSVReader(new InputStreamReader(resource));
            List<String[]> content = reader.readAll();
            assertThat(content.get(0)[0]).contains("Вышестоящий отдел");
        }
    }
    @Test
    void SelenideDownloadResourcesCsv() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("example/testReader.csv");
                CSVReader reader = new CSVReader(new InputStreamReader(resource))
        ) {
            List<String[]> content = reader.readAll();
            assertThat(content.get(0)[1]).contains("второй");
        }
    }
}