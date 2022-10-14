package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    private WebDriver webDriver;

    @FindBy(xpath = "/html[1]/body[1]/section[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[6]/a[1]")
    public WebElement removeButton;
    @FindBy(xpath = "/html[1]/body[1]/section[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]/button[1]")
    public WebElement numberOfProducts;
    @FindBy(linkText = "Proceed To Checkout")
    public WebElement procesToCheckout;
    @FindBy(linkText = "Place Order")
    public WebElement placedOrder;
    @FindBy(xpath = "/html[1]/body[1]/section[1]/div[1]/div[5]/table[1]/tbody[1]/tr[3]/td[4]/p[1]")
    WebElement totalPrice;
    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public void removeItemFromCart(){
        removeButton.click();
    }

    public void clickProcesToCheckout(){
        procesToCheckout.click();
    }
    public void placeOrder(){
        placedOrder.click();
    }

}
