package controller;

import dto.QualiTestDAO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.HelperUtils;
import utils.SeleniumInitializer;

import java.util.List;

/**
 * LandingPageController is the class where all the operations related to product searching, adding the products to wishlist
 * is performed.
 *
 * @author siddharth_patil
 */
public class LandingPageController extends HelperUtils {

    private final WebDriver driver;
    private QualiTestDAO qualiTestDAO;
    By addToWishList = By.xpath("//div[@class='summary entry-summary'] // i [@class='yith-wcwl-icon fa fa-heart-o']");
    By searchBar = By.xpath("//input[@class='header-search-input' or @name='s' or @placeholder='Search products...']");
    By searchButton = By.xpath("//i[@class='la la-search']");
    By wishListPageButton = By.xpath("//div[@class='header-right col-md-3 hidden-xs']//i[@class='lar la-heart']");

    public LandingPageController(QualiTestDAO qualiTestDAO) {
        this.qualiTestDAO = qualiTestDAO;
        this.driver = SeleniumInitializer.driver;
    }

    /**
     * This method add the list of given products into the wishlist.
     * First it searches the product.
     * Then adds the product to wishlist
     *
     * @param userInputs : User Inputs from Cucumber BDD steps.
     */
    public void addItemsToWishList(final List<String> userInputs) {
        for (int i = 1; i < userInputs.size(); i++) {
            performSearchOperation(searchBar, userInputs.get(i));
            performClickOperation(searchButton);
            performClickOperation(addToWishList);
        }
    }

    /**
     * This method performs the operation to click WishList page button.
     */
    public void openWishListPage() {
        performClickOperation(wishListPageButton);
    }
}
