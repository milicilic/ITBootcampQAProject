package Tests;

import Pages.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class TestHomePage {
    private WebDriver webDriver;
     LogIn login;
     HomePage homePage;
     SignUp signUp;
     CartPage cartPage;
     Payment payment;
     ProductPage productPage;
     final String CORECTEMAIL="newUsserr@usser.com";
     final String CORECTNAME="newUser";
     final String CORECTPASSWORD="newUser";


    @BeforeMethod
    public void configure() {
        System.out.println("Ovo se izvrsva pre pokretanja test metode");
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\milic\\Desktop\\chromedriver_win32\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        homePage=new HomePage(webDriver);
        login=new LogIn(webDriver);
        signUp =new SignUp(webDriver);
        cartPage=new CartPage(webDriver);
        payment=new Payment(webDriver);
        productPage=new ProductPage(webDriver);


        webDriver.get("https://www.automationexercise.com/");

    }

    @DataProvider(name = "incorectEmail")
    public Object[][] createData1() {

        return new Object[][] {
                { "ovajEmailneceproci@gmail.com", Faker.instance().internet().password()},//not registred email
                { "neceniovaj@yaada.com", Faker.instance().internet().password()},//mail
                { "ovajnemanikakvesanse@jdjd", Faker.instance().internet().password()},//mail without .com
                {"a ovajtek @h ", Faker.instance().internet().password()}

        };

    }

    @Test
    public void backToHomePage(){
        homePage.goOnProductsPage();
        homePage.clickOnHomePageIcon();

        homePage.goOnCartPage();
        homePage.clickOnHomePageIcon();

        homePage.goOnContactUsPage();
        homePage.clickOnHomePageIcon();

        homePage.goOnLogIn_SignUpPage();
        homePage.clickOnHomePageIcon();
    }

    @Test
    public void scrollUp()  {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("window.scrollBy(0,500)", "");
        homePage.clickOnScrollUpButton();


    }

    @Test(dataProvider = "incorectEmail")
    public void logWithIcorectEmail(String email,String password){
        homePage.goOnLogIn_SignUpPage();
        login.inputEmail(email);
        login.inputPassword(password);
        login.logInButtonClick();
        Assert.assertEquals(login.messageForIncorectLogIn(),"Your email or password is incorrect!");

    }

    @Test
    public void createNewUser(){
        homePage.goOnLogIn_SignUpPage();
        signUp.inputname(CORECTNAME);
        signUp.inputEmail(CORECTEMAIL);
        signUp.signInButtonClick();
        signUp.inputPassword(CORECTPASSWORD);
        signUp.selectGender();
        signUp.selectDays(2);
        signUp.selectMonths("January");
        signUp.selectYear(1996);
        signUp.inputFirstName(CORECTNAME);
        signUp.inputLastName(CORECTNAME);
        signUp.inputAddress("Djusina 5 ");
        signUp.selectCountry("India");
        signUp.inputState("India");
        signUp.inputCity("City");
        signUp.inputZipcode("121324");
        signUp.inputMobile("1323142");
        signUp.createAccount();
        String message=webDriver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/h2[1]/b[1]")).getText();
        Assert.assertEquals(message,"ACCOUNT CREATED!");
        signUp.clickContinue();
        String URL= webDriver.getCurrentUrl();
        Assert.assertEquals(URL,"https://www.automationexercise.com/");
        Assert.assertEquals(homePage.logedName(),CORECTNAME);


    }

   @Test
    public  void logInWithCorectData(){
        homePage.goOnLogIn_SignUpPage();
        login.inputEmail(CORECTEMAIL);
        login.inputPassword(CORECTPASSWORD);
        login.logInButtonClick();
       String URL= webDriver.getCurrentUrl();
       Assert.assertEquals(URL,"https://www.automationexercise.com/");
       Assert.assertEquals(homePage.logedName(),CORECTNAME);
   }

   @Test
   public void addItemInCart(){
        logInWithCorectData();
        homePage.goOnProductsPage();
        homePage.scrollToDress(2);
        homePage.hoverToDress(2);
        homePage.clickToAddCartButton();
        homePage.clickToViewCartButton();
        String Url= webDriver.getCurrentUrl();
        Assert.assertEquals(Url,"https://www.automationexercise.com/view_cart");
       WebElement product=webDriver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]"));
        Assert.assertTrue(product.isDisplayed());


   }

   @Test
   public void removeItemFromCart(){
        addItemInCart();
        cartPage.removeItemFromCart();
       WebElement product=webDriver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[2]/table[1]"));
       Assert.assertFalse(product.getCssValue("style").equals("display: none;"));
      //proveriti ovo sa Markom
   }
   @Test
   public void continueShoping(){
       logInWithCorectData();
       homePage.goOnProductsPage();
       homePage.scrollToDress(2);
       homePage.hoverToDress(2);
       homePage.clickToAddCartButton();
       homePage.continueToShoping();
       homePage.scrollToDress(2);
       homePage.hoverToDress(2);
       homePage.clickToAddCartButton();
       homePage.continueToShoping();
       String Url= webDriver.getCurrentUrl();
       Assert.assertEquals(Url,"https://www.automationexercise.com/products");

   }
   @Test
   public void checkNumberofSameProducts(){

        continueShoping();
        homePage.goOnCartPage();
        Assert.assertEquals(cartPage.numberOfProducts.getText(),"2");

   }
   @Test
   public void checkPlaceOrder(){
        checkNumberofSameProducts();
        cartPage.clickProcesToCheckout();
        cartPage.placeOrder();
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.automationexercise.com/payment");

   }
   @Test
   public void fillPayFormAndSubmit(){
        checkPlaceOrder();
        payment.inputNameOnCard(CORECTNAME);
        payment.cardNumber();
        payment.cvcNumber();
        payment.monthOnCard();
        payment.yearOnCard();
        payment.payAndCofirm();

        Assert.assertEquals(payment.getMesage().getText(),"ORDER PLACED!");
        payment.backToHomePage();
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.automationexercise.com/");

   }
   @Test
   public void logOut(){
    logInWithCorectData();
    homePage.logOut();
    Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.automationexercise.com/login");
   }
   @Test
   public void searchProducts(){
        homePage.goOnProductsPage();
        productPage.search("top");
       JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
       javascriptExecutor.executeScript("window.scrollBy(0,500)", "");
        Assert.assertTrue(productPage.nameOfProduct().contains("top"));

   }


    @AfterMethod
    public void close(){
        webDriver.close();



    }

}
