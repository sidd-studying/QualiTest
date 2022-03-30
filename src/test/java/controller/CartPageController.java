package controller;

import dto.QualiTestDAO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.HelperUtils;
import utils.SeleniumInitializer;

/**
 * CartPageController class contains all the operations and verifications related to the Cart Page.
 *
 * @author siddharth_patil
 */
public class CartPageController extends HelperUtils {

    private final WebDriver driver;
    private QualiTestDAO qualiTestDAO;
    By cartPageButton = By.xpath("//div[@class='header-right col-md-3 hidden-xs']//i[@class='la la-shopping-bag']");

    public CartPageController(QualiTestDAO qualiTestDAO) {
        this.qualiTestDAO = qualiTestDAO;
        this.driver = SeleniumInitializer.driver;
    }

    /**
     * This method performs the operation of opening the cart page.
     */
    public void openCartPage() {
        performClickOperation(cartPageButton);
    }

    /**
     * This method verifies the cart contents. It verifies the product name and product price.
     */
    public void verifyCartItems() throws InterruptedException {
        waitForSomeTime(10);
        String productName;
        String productPriceText;
        float productPrice;
        for (int i = 1; i < qualiTestDAO.getLowestPricedProducts().size(); i++) {
            productName = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[@class='product-name']/a")).getText();
            productPriceText = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[@class='product-price']//span[@class='woocommerce-Price-amount amount']")).getText();
            productPrice = getAmount(productPriceText);
            Assert.assertTrue(qualiTestDAO.getLowestPricedProducts().containsKey(productName));
            Assert.assertTrue(qualiTestDAO.getLowestPricedProducts().containsValue(productPrice));
        }
    }
}
