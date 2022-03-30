package stepdefs;

import controller.LandingPageController;
import dto.QualiTestDAO;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.SeleniumInitializer;

import java.util.List;

/**
 * LandingPageStepDef is the class that contains the step definition steps for
 * navigating the products and adding them products to wishlist.
 *
 * @author siddharth_patil
 */
public class LandingPageStepDef extends SeleniumInitializer {

    public LandingPageController landingPageController;
    private QualiTestDAO qualiTestDAO;

    public LandingPageStepDef(QualiTestDAO qualiTestDAO) {
        this.qualiTestDAO = qualiTestDAO;
        landingPageController = new LandingPageController(qualiTestDAO);
    }

    @Given("I add following products to my wish list")
    public void iAddFollowingProductsToMyWishList(final List<String> userInputs) {
        landingPageController.addItemsToWishList(userInputs);
    }

    @When("I view my wishlist table")
    public void iViewMyWishlistTable() {
        landingPageController.openWishListPage();
    }

    @AfterAll
    public static void tearDownWebDriver() {
        SeleniumInitializer.tearDownWebDriver();
    }
}
