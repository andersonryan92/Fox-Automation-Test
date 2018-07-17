package page.classes;

        import org.openqa.selenium.By;
        import org.openqa.selenium.Keys;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;

public class FoxHomepage {

    public static WebElement element = null;


    public static WebElement accountButton(WebDriver driver){
        element = driver.findElement(By.id("path-1"));
        return element;
    }

    public static WebElement showsTab(WebDriver driver){
        element = driver.findElement(By.cssSelector("div.Header_navLinks_1WBD8 > a:nth-child(1)"));
        return element;
    }
}