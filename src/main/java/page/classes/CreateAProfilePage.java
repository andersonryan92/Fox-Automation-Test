package page.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;

public class CreateAProfilePage {

    public static WebElement element = null;


    public static WebElement firstNameField(WebDriver driver){
        element = driver.findElement(By.name("signupFirstName"));
        return element;
    }

    public static WebElement lasttNameField(WebDriver driver){
        element = driver.findElement(By.name("signupLastName"));
        return element;
    }

    public static WebElement emailField(WebDriver driver){
        element = driver.findElement(By.name("signupEmail"));
        return element;
    }

    public static WebElement passwordField(WebDriver driver){
        element = driver.findElement(By.name("signupPassword"));
        return element;
    }

    public static WebElement genderDropdown(WebDriver driver){
        element = driver.findElement(By.className("DropdownIcon_iconDown_btZKM"));
        return element;
    }

    public static WebElement maleOption(WebDriver driver){
        element = driver.findElement(By.xpath("//*[text()=\"Male\"]"));
        return element;
    }

    public static WebElement dateOfBirth(WebDriver driver){
        element = driver.findElement(By.cssSelector(".Account_signupBirthdayGenderContainer_1n0m8 > div:nth-child(2) > input"));
        return element;
    }

    public static WebElement createAccountButton(WebDriver driver){
        element = driver.findElement(By.cssSelector(".Account_signupButtonDesktop_1PCXs > button"));
        return element;
    }

    public static WebElement doneButton(WebDriver driver){
        element = driver.findElement(By.cssSelector(".Account_signupSuccessButton_1mM7y > button"));
        return element;
    }








}