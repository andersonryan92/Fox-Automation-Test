package page.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;

public class SignInPage {

    public static WebElement element = null;


    public static WebElement emailField(WebDriver driver){
        element = driver.findElement(By.cssSelector(".Account_signupFormInputContainer_vOm8h > input"));
        return element;
    }

    public static WebElement passwordField(WebDriver driver){
        element = driver.findElement(By.cssSelector(".Account_signupPasswordContainer_3LRRa > input"));
        return element;
    }

    public static WebElement signInButton(WebDriver driver){
        element = driver.findElement(By.cssSelector(".Account_signinButtonDesktop_2SO1g > button"));
        return element;
    }
}