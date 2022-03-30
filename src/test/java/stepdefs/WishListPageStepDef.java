package stepdefs;

import controller.WishListPageController;
import dto.QualiTestDAO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ActionFailedException;
import utils.SeleniumInitializer;

/**
 * WishListPageStepDef class contains step definitions for the cucumber steps for checking the wish list page.
 * It contains step definitions for verifying the wish list contents and adding products to the cart.
 *
 * @author siddharth_patil
 */
public class WishListPageStepDef extends SeleniumInitializer {

    private QualiTestDAO qualiTestDAO;
    public WishListPageController wishListPageController;

    public WishListPageStepDef(QualiTestDAO qualiTestDAO) {
        this.qualiTestDAO = qualiTestDAO;
        wishListPageController = new WishListPageController(qualiTestDAO);
    }

    @Then("I find total {int} selected items in my Wishlist")
    public void iFindTotalSelectedItemsInMyWishlist(final int wishListSIze) {
        wishListPageController.getWishListTableContent(wishListSIze);
    }

    @When("I search for lowest price product")
    public void iSearchForLowestPriceProduct() throws ActionFailedException {
        wishListPageController.createListOfProductsWithLowestPrice();
    }

    @And("I am able to add the lowest price item to my cart")
    public void iAmAbleToAddTheLowestPriceItemToMyCart() throws InterruptedException {
        wishListPageController.addToCart();
    }
}
