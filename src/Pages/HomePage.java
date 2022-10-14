package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
    private WebDriver webDriver;

    @FindBy(xpath = "/html[1]/body[1]/a[1]")
    private WebElement scrollUpButton;
    @FindBy(xpath = "/html[1]/body[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[2]/a[1]")
    private WebElement productsPage;
    @FindBy(xpath = "/html[1]/body[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/a[1]")
    private WebElement cartPage;
    @FindBy(xpath = "/html[1]/body[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[4]/a[1]")
    private WebElement logIn_SignUpPage;
    @FindBy(xpath = "/html[1]/body[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[8]/a[1]")
    private WebElement contactUsPage;
    @FindBy(xpath = "/html[1]/body[1]/header[1]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/img[1]")
    private WebElement homePageIcon;
    @FindBy(xpath = "/html[1]/body[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[10]/a[1]/b[1]")
    private WebElement logName;
    @FindBy(xpath = "/html[1]/body[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[4]/a[1]")
    private WebElement logOut;
    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public void clickOnScrollUpButton(){
       if( scrollUpButton.getCssValue("display").equals("block")){
            scrollUpButton.click();
        }

    }
    public void clickOnHomePageIcon(){
        homePageIcon.click();
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.automationexercise.com/");
    }
    public void goOnProductsPage(){
        productsPage.click();
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.automationexercise.com/products");

    }


    public void goOnCartPage(){
        cartPage.click();
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.automationexercise.com/view_cart");
    }
    public void goOnLogIn_SignUpPage(){
        logIn_SignUpPage.click();
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.automationexercise.com/login");
    }
    public void goOnContactUsPage(){
        contactUsPage.click();
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.automationexercise.com/contact_us");
    }
    public String logedName (){
        return logName.getText();
    }
    private WebElement getDress(int dressNumber) {
        if (dressNumber < 2 || dressNumber > 35) {
            throw new IllegalArgumentException(String.format("The dress number %d is not available", dressNumber));
        }
        return webDriver.findElement(By.xpath("/html[1]/body[1]/section[2]/div[1]/div[1]/div[2]/div[1]/div["+dressNumber+"]/div[1]/div[1]/div[2]"));
    }
    public void scrollToDress(int dressNumber) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", getDress(dressNumber));
    }

    public void hoverToDress(int dressNumber) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(getDress(dressNumber)).perform();
    }

    public void clickToAddCartButton() {
        WebElement addToCard = webDriver.findElement(By.xpath("/html[1]/body[1]/section[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/a[1]"));
        addToCard.click();
    }
    public void continueToShoping(){
        WebElement addToCard = webDriver.findElement(By.xpath("/html[1]/body[1]/section[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/button[1]"));
        addToCard.click();
    }

    public void clickToViewCartButton() {
        WebElement viewCart = webDriver.findElement(By.xpath("/html[1]/body[1]/section[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/p[2]/a[1]/u[1]"));
        viewCart.click();
    }
    public void logOut(){
        logOut.click();
    }

}
