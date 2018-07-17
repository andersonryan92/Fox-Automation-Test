package NewPackage;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.classes.AccountPage;
import page.classes.CreateAProfilePage;
import page.classes.FoxHomepage;
import page.classes.SignInPage;
import org.assertj.core.api.SoftAssertions;

import static org.junit.jupiter.api.Assertions.*;




public class TestClass {

    public WebDriver driver;
    public String baseURL;


    // create static unique number for sign up
    static Date date = new Date();
    private static long uniqueNumber = date.getTime();

    @Test
    @DisplayName("navigate to fox.com and create an account")
    void testCase1() throws Exception {

        System.setProperty("webdriver.chrome.driver", "/Users/ryananderson/Downloads/chromedriver");
        driver = new ChromeDriver();
        baseURL = "https://www.fox.com";
        driver.manage().window().maximize();
        driver.get(baseURL);

        // create a WebDriverWait that we will reuse
        WebDriverWait wait = new WebDriverWait(driver, 5);

        // click on the account button on homepage
        wait.until(ExpectedConditions.elementToBeClickable(FoxHomepage.accountButton(driver))).click();

        // click on the sign up button
        wait.until(ExpectedConditions.elementToBeClickable(AccountPage.signupButton(driver))).click();

        // enter in name in name field
        wait.until(ExpectedConditions.elementToBeClickable(CreateAProfilePage.firstNameField(driver)));
        CreateAProfilePage.firstNameField(driver).sendKeys("Ryan");
        CreateAProfilePage.lasttNameField(driver).sendKeys("Anderson");

        // enter in an email address
        CreateAProfilePage.emailField(driver).sendKeys("ryan" + uniqueNumber + "@domain.com");

        // enter password in password field
        CreateAProfilePage.passwordField(driver).sendKeys("password123");

        // select gender from dropdown
        CreateAProfilePage.genderDropdown(driver).click();
        CreateAProfilePage.maleOption(driver).click();

        // enter date of birth
        wait.until(ExpectedConditions.elementToBeClickable(CreateAProfilePage.dateOfBirth(driver)));
        CreateAProfilePage.dateOfBirth(driver).sendKeys("02261992");

        // click the create account button
        CreateAProfilePage.createAccountButton(driver).click();
        Thread.sleep(2500);

        // click the done button on the next page
        wait.until(ExpectedConditions.elementToBeClickable(CreateAProfilePage.doneButton(driver)));
        CreateAProfilePage.doneButton(driver).click();

        driver.close();
        driver.quit();
    }

    @Test
    @DisplayName("log into fox.com, go to shows tab, capture last 4 shows and save it on " +
            "Excel sheet under column Title “Show”, save duplicate shows from tabs to excel sheet")
    void testCase2() throws Exception {

        SoftAssertions softAssertions = new SoftAssertions();

        System.setProperty("webdriver.chrome.driver", "/Users/ryananderson/Downloads/chromedriver");
        driver = new ChromeDriver();
        baseURL = "https://www.fox.com";
        driver.manage().window().maximize();
        driver.get(baseURL);

        // create a WebDriverWait that we will reuse
        WebDriverWait wait = new WebDriverWait(driver, 5);

        // click the account button on homepage
        wait.until(ExpectedConditions.elementToBeClickable(FoxHomepage.accountButton(driver)));
        FoxHomepage.accountButton(driver).click();

        // click the sign in button
        wait.until(ExpectedConditions.elementToBeClickable(AccountPage.signinButton(driver)));
        AccountPage.signinButton(driver).click();

        // enter in email address & password
        wait.until(ExpectedConditions.elementToBeClickable(SignInPage.emailField(driver)));
        SignInPage.emailField(driver).sendKeys("ryan" + uniqueNumber + "@domain.com");
        SignInPage.passwordField(driver).sendKeys("password123");

        //click the sign in button
        wait.until(ExpectedConditions.elementToBeClickable(SignInPage.signInButton(driver)));
        SignInPage.signInButton(driver).click();

        // click on the Shows tab
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(FoxHomepage.showsTab(driver)));
        FoxHomepage.showsTab(driver).click();

        // scroll down the page to load the other shows
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2000)");

        // give page 2 seconds to load shows after scrolling down page
        Thread.sleep(2000);

        // capture all the shows in a variable called "shows"
        List<WebElement> shows = driver.findElements(By.cssSelector(".TileGrid_grid_vrnLT > div"));

        // create a variable which will be used to store the last 4 shows on the page
        List<String> lastFourShows = new ArrayList<String>();

        // created a integer variable for the number of shows on the page
        int totalNumberOfShows = shows.size();

        // created an integer variable for the amount of shows on the page - 4
        int lastFour = (totalNumberOfShows - 4);

        // this for loop adds the last 4 shows on the page to the "lastFourShows" variable
        for (WebElement show : shows.subList(lastFour, totalNumberOfShows)) {
            lastFourShows.add(show.getText());
        }


        // get the excel file and create input stream for excel
        FileInputStream fis = new FileInputStream("/Users/ryananderson/Downloads/Book1.xlsx");

        // load the stream to a workbook object
        // Use XSSF for (.xlsx) excel file
        XSSFWorkbook wb = new XSSFWorkbook(fis);

        // get the sheet from the workbook by index
        XSSFSheet sheet1 = wb.getSheetAt(0);


        int rowcount = 0;

        // write out shows to excel file
        for (String show: lastFourShows) {
            XSSFRow row = sheet1.createRow(++rowcount);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(show);
        }

        FileOutputStream fout = new FileOutputStream("/Users/ryananderson/Downloads/Book1.xlsx");


        driver.findElement(By.cssSelector(".PageHeaderBrowseAltHeader_tabContainer_en4tN > a:nth-child(2)")).click();

        // verify the following shows are displayed
        // a.       24 Hours To Hell & Back
        // b.      So You Think You Can Dance
        // c.       Meghan Markle: An American Princess
        // d.      Hypnotize Me

        Thread.sleep(2500);

        List<String> allFourTabsShows = new ArrayList<String>();

        List<WebElement> FXshows = driver.findElements(By.cssSelector(".TileGrid_grid_vrnLT > div"));

        List<String> showsAsString = new ArrayList<String>();

        System.out.println("print out fx shows -----------" +
                "----------------" +
                "----------------");

        for (WebElement show : FXshows) {
            String text = show.getText();
            System.out.println(text);
            showsAsString.add(text);
            allFourTabsShows.add(text);
        }


        boolean doesShowOneExist = showsAsString.contains("24 Hours To Hell & Back");
        boolean doesShowTwoExist = showsAsString.contains("So You Think You Can Dance");
        boolean doesShowThreeExist = showsAsString.contains("Meghan Markle: An American Princess");
        boolean doesShowFourExist = showsAsString.contains("Hypnotize Me");

        softAssertions.assertThat(doesShowOneExist);
        softAssertions.assertThat(doesShowTwoExist);
        softAssertions.assertThat(doesShowThreeExist);
        softAssertions.assertThat(doesShowFourExist);


        driver.findElement(By.cssSelector(".PageHeaderBrowseAltHeader_tabContainer_en4tN > a:nth-child(3)")).click();

        Thread.sleep(2500);

        List<WebElement> NGshows = driver.findElements(By.cssSelector(".TileGrid_grid_vrnLT > div"));

        List<String> NGshowsAsString = new ArrayList<String>();

        System.out.println("print out fx shows -----------" +
                "----------------" +
                "----------------");

        for (WebElement show : NGshows) {
            String text = show.getText();
            System.out.println(text);
            showsAsString.add(text);
            allFourTabsShows.add(text);
        }

        boolean doesNGShowOneExist = showsAsString.contains("24 Hours To Hell & Back");
        boolean doesNGShowTwoExist = showsAsString.contains("So You Think You Can Dance");
        boolean doesNGShowThreeExist = showsAsString.contains("Meghan Markle: An American Princess");
        boolean doesNGShowFourExist = showsAsString.contains("Hypnotize Me");

        softAssertions.assertThat(doesNGShowOneExist);
        softAssertions.assertThat(doesNGShowTwoExist);
        softAssertions.assertThat(doesNGShowThreeExist);
        softAssertions.assertThat(doesNGShowFourExist);

        driver.findElement(By.cssSelector(".PageHeaderBrowse_active_1-pOT")).click();

        Thread.sleep(2000);

        List<WebElement> FoxSportsShows = driver.findElements(By.cssSelector(".TileGrid_grid_vrnLT > div"));

        List<String> foxSportsShowsAsString = new ArrayList<String>();

        System.out.println("print out fx shows -----------" +
                "----------------" +
                "----------------");

        for (WebElement show : FoxSportsShows) {
            String text = show.getText();
            System.out.println(text);
            foxSportsShowsAsString.add(text);
            allFourTabsShows.add(text);
        }

        boolean doesFoxSportsShowOneExist = showsAsString.contains("24 Hours To Hell & Back");
        boolean doesFoxSportsShowTwoExist = showsAsString.contains("So You Think You Can Dance");
        boolean doesFoxSportsShowThreeExist = showsAsString.contains("Meghan Markle: An American Princess");
        boolean doesFoxSportsShowFourExist = showsAsString.contains("Hypnotize Me");


        softAssertions.assertThat(doesFoxSportsShowOneExist);
        softAssertions.assertThat(doesFoxSportsShowTwoExist);
        softAssertions.assertThat(doesFoxSportsShowThreeExist);
        softAssertions.assertThat(doesFoxSportsShowFourExist);


        driver.findElement(By.cssSelector(".PageHeaderBrowseAltHeader_tabContainer_en4tN > a:nth-child(6)")).click();

        Thread.sleep(2500);

        List<WebElement> allShows = driver.findElements(By.cssSelector(".TileGrid_grid_vrnLT > div"));

        List<String> allShowsAsString = new ArrayList<String>();

        System.out.println("print out all shows -----------" +
                "----------------" +
                "----------------");

        for (WebElement show : allShows) {
            String text = show.getText();
            System.out.println(text);
            allShowsAsString.add(text);
            allFourTabsShows.add(text);
        }

        boolean doesAllShowOneExist = showsAsString.contains("24 Hours To Hell & Back");
        boolean doesAllShowTwoExist = showsAsString.contains("So You Think You Can Dance");
        boolean doesAllShowThreeExist = showsAsString.contains("Meghan Markle: An American Princess");
        boolean doesAllShowFourExist = showsAsString.contains("Hypnotize Me");

        softAssertions.assertThat(doesAllShowOneExist);
        softAssertions.assertThat(doesAllShowTwoExist);
        softAssertions.assertThat(doesAllShowThreeExist);
        softAssertions.assertThat(doesAllShowFourExist);


        Set<String> duplicateShows = findDuplicateShows(allFourTabsShows);


        XSSFSheet sheet2 = wb.getSheetAt(1);


        int rowcount1 = 0;

        for (String show: duplicateShows) {
            XSSFRow row = sheet2.createRow(++rowcount1);
            XSSFCell cell = row.createCell(1);
            cell.setCellValue(show);
        }

        wb.write(fout);

        wb.close();

    }

    // this method finds duplicate shows from the 4 tabs
    public static Set<String> findDuplicateShows(List<String> listContainingDuplicates) {

        final Set<String> setToReturn = new HashSet<String>();
        final Set<String> set1 = new HashSet<String>();

        for (String title: listContainingDuplicates) {
            if (!set1.add(title)) {
                setToReturn.add(title);
            }
        }
        return setToReturn;
    }
}
