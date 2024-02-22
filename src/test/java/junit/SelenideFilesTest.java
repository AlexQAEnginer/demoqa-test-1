package junit;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import junit.model.Glossary;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.as;
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

    @Test
    void SelenideDownloadZip() throws Exception {
        try (InputStream resource = cl.getResourceAsStream("example/Test.rar");
             ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                if (entry.getName().endsWith(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(0)[0]).contains("John");

                } else if (entry.getName().endsWith(".pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("Windows & Linux keymap");

                } else if (entry.getName().contains(".xlsx")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(0).getCell(0)
                            .getStringCellValue()).contains("1. Внутренняя экономика (S1)");

                }
            }
        }
    }

    @Test
    void SelenideDownloadJson() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("example/test.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            assertThat(jsonObject.get("title").getAsString()).isEqualTo("example glossary");
            assertThat(jsonObject.get("GlossDiv").getAsJsonObject().get("title").getAsString()).isEqualTo("S");
            assertThat(jsonObject.get("GlossDiv").getAsJsonObject().get("flag").getAsBoolean()).isTrue();
        }
    }

    @Test
    void SelenideDownloadImprovedJson() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("example/test.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            Glossary jsonObject = gson.fromJson(reader, Glossary.class);
            assertThat(jsonObject.title).isEqualTo("example glossary");
            assertThat(jsonObject.glossDiv.title).isEqualTo("S");
            assertThat(jsonObject.glossDiv.flag).isTrue();
        }
    }
    @Test
    void SelenideDownloadChromeJson() throws Exception {
        Gson gson = new Gson();
        open("https://filesamples.com/formats/json");
        File downloadedJson = $("[href='/samples/code/json/sample1.json']").download();
        try (
                InputStream resource = new FileInputStream(downloadedJson);
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            assertThat(jsonObject.get("fruit").getAsString()).isEqualTo("Apple");
        }
    }
}
