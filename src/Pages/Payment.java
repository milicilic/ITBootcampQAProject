package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Payment {
    private WebDriver webDriver;
    public Payment(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }
    @FindBy(xpath = "/html[1]/body[1]/section[1]/div[1]/div[3]/div[1]/div[2]/form[1]/div[1]/div[1]/input[1]")
    private WebElement nameOnCard;
    @FindBy(xpath = "/html[1]/body[1]/section[1]/div[1]/div[3]/div[1]/div[2]/form[1]/div[2]/div[1]/input[1]")
    private WebElement cardNumber;
    @FindBy(xpath = "/html[1]/body[1]/section[1]/div[1]/div[3]/div[1]/div[2]/form[1]/div[3]/div[1]/input[1]")
    private WebElement cvc;
    @FindBy(xpath = "/html[1]/body[1]/section[1]/div[1]/div[3]/div[1]/div[2]/form[1]/div[3]/div[2]/input[1]")
    private WebElement monthOnCard;
    @FindBy(xpath = "/html[1]/body[1]/section[1]/div[1]/div[3]/div[1]/div[2]/form[1]/div[3]/div[3]/input[1]")
    private WebElement yearCard;
    @FindBy(xpath = "/html[1]/body[1]/section[1]/div[1]/div[3]/div[1]/div[2]/form[1]/div[5]/div[1]/button[1]")
    WebElement payButton;
    @FindBy(xpath = "/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/h2[1]/b[1]")
    WebElement mesage;
    @FindBy(linkText = "Continue")
    WebElement continuetoShoping;
    public void inputNameOnCard(String name){
        nameOnCard.sendKeys(name);
    }
    public void cardNumber(){
        cardNumber.sendKeys("1424235");
    }
    public void cvcNumber(){
        cvc.sendKeys("142");
    }
    public void monthOnCard(){
        monthOnCard.sendKeys("11");
    }
    public void yearOnCard(){
        yearCard.sendKeys("1424235");
    }
    public void payAndCofirm(){
        payButton.click();
    }
    public String mesage(){
        return mesage.getText();
    }


    public WebElement getMesage() {
        return mesage;
    }
    public void backToHomePage(){
        continuetoShoping.click();
    }
}
