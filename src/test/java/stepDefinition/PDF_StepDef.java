package stepDefinition;

import Utils.Constants;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;

import static Utils.Constants.*;


public class PDF_StepDef {
      public WebDriver driver;
    public String pdfFilePath;
    public String expectedText;

    @Given("I have a {string} PDF URL")
    public void iHaveAPDFURL(String filename) {
        if (filename.equals(Constants.SOC_369_Agency_Relative_Guardianship_Disclosure))
        {             pdfFileURL = Constants.SOC_369_Agency_Relative_Guardianship_Disclosure_Path; }
        else if (filename.equals(Constants.STRTP_Admission_Agreement_By_Agency))
        {             pdfFileURL = Constants.STRTP_Admission_Agreement_By_Agency_Path; }

    }

    @Then("I validate the PDF content of {string}")
    public void i_validate_the_PDF_content(String fileName) throws IOException {

        switch (fileName)
        {
            case SOC_369_Agency_Relative_Guardianship_Disclosure:
                Constants.pageCount = 4;
                headerValue = SOC_369_Agency_Relative_Guardianship_Disclosure_Header;
                break;
            case STRTP_Admission_Agreement_By_Agency:
                Constants.pageCount = 8;
                headerValue =STRTP_Admission_Agreement_By_Agency_Header;
                break;
        }
        File file1 = new File(pdfFileURL);
        PDDocument document = PDDocument.load(file1);

        int totalpageofDocument1 = getPageCount(document);
        if (totalpageofDocument1 == pageCount) {
            System.out.println("Page count tallied");
        }

        boolean headerValidated = validateHeader(document, headerValue);
        if (headerValidated) {
            System.out.println("Header validated successfully");
        } else {
            System.out.println("Header validation failed");
        }

        document.close();
    }

    public static int getPageCount(PDDocument doc) {
        //get the total number of pages in the pdf document
        int pageCount = doc.getNumberOfPages();
        System.out.println("Page count is " +pageCount);
        return pageCount;
    }
    public static boolean validateHeader(PDDocument doc, String expectedHeader) throws IOException, IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        StringBuilder allText = new StringBuilder();

        for (int i = 1; i <= doc.getNumberOfPages(); i++) {
            pdfStripper.setStartPage(i);
            pdfStripper.setEndPage(i);
            String pageText = pdfStripper.getText(doc).trim();
            allText.append(pageText);
        }

        return allText.toString().contains(expectedHeader);
    }

}
