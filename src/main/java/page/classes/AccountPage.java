package page.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;

public class AccountPage {

    public static WebElement element = null;


    public static WebElement signupButton(WebDriver driver){
        element = driver.findElement(By.className("Account_signUp_3SpTs"));
        return element;
    }

    public static WebElement signinButton(WebDriver driver){
        element = driver.findElement(By.cssSelector(".Account_signIn_Q0B7n"));
        return element;
    }
}