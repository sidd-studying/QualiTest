package controller;

import dto.QualiTestDAO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.ActionFailedException;
import utils.HelperUtils;
import utils.SeleniumInitializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WishListPageController is the class where all the operations and verifications from Wishlist page are performed.
 *
 * @author siddharth_patil
 */
public class WishListPageController extends HelperUtils {
    private final WebDriver driver;
    private final QualiTestDAO qualiTestDAO;
    private int wishListSize;
    By wishListTable = By.xpath("//tbody[@class='wishlist-items-wrapper']//tr");

    public WishListPageController(QualiTestDAO qualiTestDAO) {
        this.qualiTestDAO = qualiTestDAO;
        this.driver = SeleniumInitializer.driver;
    }

    /**
     * This method gets the table rows so that we can count the number of items in the wish list.
     *
     * @param wishListItemCount : Expected number of items in the wish list.
     */
    public void getWishListTableContent(final int wishListItemCount) {
        waitUntilElementVisible(wishListTable, 10);
        List<WebElement> wishListContents = driver.findElements(wishListTable);
        verifyWishListSize(wishListContents.size(), wishListItemCount);
        wishListSize = wishListContents.size();
    }

    /**
     * This method asserts count of items in the wishList.
     *
     * @param actualSize   : Actual number of items present in the wish list.
     * @param expectedSize : Expected number of items in the wish list.
     */
    public void verifyWishListSize(final int actualSize, final int expectedSize) {
        Assert.assertEquals(actualSize, expectedSize);
    }

    /**
     * This method creates a list of products that are lowest in value and
     * stores them inside a {@link java.util.Map} present in {@link dto.QualiTestDAO} DAO class.
     */
    public void createListOfProductsWithLowestPrice() throws ActionFailedException {
        Map<String, Float> wishListProductsWithPrices = getWishListProductsWithPrices();
        Float lowestPrice = Collections.min(wishListProductsWithPrices.values());
        for (Map.Entry<String, Float> entry : wishListProductsWithPrices.entrySet()) {
            if (lowestPrice.equals(entry.getValue())) {
                qualiTestDAO.getLowestPricedProducts().put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * This method navigates through the wish list and creates a list of products present in it along with their prices.
     * All the wish list contents i.e ProductName and Product prices are maintained inside a {@link java.util.Map}
     * as key-value pair respectively.
     *
     * @return Object of type {@link java.util.Map}
     */
    public Map<String, Float> getWishListProductsWithPrices() throws ActionFailedException {
        Map<String, Float> productsWithLowestPrice = new HashMap<>();
        String amountText;
        By byProductName;
        By byAmountText;
        By byNewAmountText;
        for (int i = 1; i < wishListSize + 1; i++) {
            byProductName = By.xpath("//tbody[@class='wishlist-items-wrapper']//tr[" + i + "]//td[@class='product-name']");
            byNewAmountText = By.xpath("//tbody[@class='wishlist-items-wrapper']//tr[" + i + "]//td[@class='product-price']//ins//span[@class='woocommerce-Price-amount amount']");
            byAmountText = By.xpath("//tbody[@class='wishlist-items-wrapper']//tr[" + i + "]//td[@class='product-price']//span[@class='woocommerce-Price-amount amount']");
            String productName = getTextWithRetry(byProductName);
            //   Checking if a strike out price is present (Old price before offer/discount).
            boolean isMultiplePriceExist = driver.findElements(byNewAmountText).size() != 0;
            //  Checks if it is an offered product and then get the correct/present price of the product.
            amountText = (isMultiplePriceExist ? getTextWithRetry(byNewAmountText) : getTextWithRetry(byAmountText));
            productsWithLowestPrice.put(productName, getAmount(amountText));
        }
        return productsWithLowestPrice;
    }

    /**
     * This method adds the products to cart.
     * Products that are added to Cart are taken from the QualiTestDAO which was previously included to wishList.
     */
    public void addToCart() throws InterruptedException {
        By addToCartButton;
        for (Map.Entry<String, Float> entry : qualiTestDAO.getLowestPricedProducts().entrySet()) {
            addToCartButton = By.xpath("//a[contains(text()," + '"' + entry.getKey() + '"' + ")]/./.././../td[@class='product-add-to-cart']/a");
            performClickOperation(addToCartButton);
        }
        waitForSomeTime(5);
    }
}
