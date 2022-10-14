package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {
    private WebDriver webDriver;

    @FindBy(xpath = "/html[1]/body[1]/section[1]/div[1]/input[1]")
    WebElement searchBar;
    @FindBy(xpath = "/html[1]/body[1]/section[1]/div[1]/button[1]")
    WebElement searchButton;

    public ProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public void search(String productType){
    searchBar.sendKeys(productType);
    searchButton.click();
    }
    public List<WebElement> vratiListuElemenata() {
        search("top");
        return webDriver.findElements(By.xpath("/html[1]/body[1]/section[2]/div[1]/div[1]/div[2]/div[1]/"));

    }


}



