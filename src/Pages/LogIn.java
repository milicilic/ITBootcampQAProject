package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LogIn {
    private WebDriver webDriver;
    private final String LOG_IN_EMAIL = "/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[1]/form[1]/input[2]";
    private final String LOG_IN_PASSWORD = "/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[1]/form[1]/input[3]";
    private final String LOG_IN_BUTTON= "/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[1]";
    public LogIn(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this); // ucitaj sve elemente na web stranici
    }

    public void inputEmail(String email){
        WebElement emailLabel= webDriver.findElement(By.xpath(LOG_IN_EMAIL));
        emailLabel.sendKeys(email);
    }
    public void inputPassword(String password){
        WebElement passwordlLabel= webDriver.findElement(By.xpath(LOG_IN_PASSWORD));
        passwordlLabel.sendKeys(password);
    }
    public void logInButtonClick(){
        WebElement button= webDriver.findElement(By.xpath(LOG_IN_BUTTON));
        button.click();

    }
    public String messageForIncorectLogIn(){

        return webDriver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[1]/form[1]/p[1]")).getText();
    }


}
