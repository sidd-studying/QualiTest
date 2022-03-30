package stepdefs;

import controller.CartPageController;
import dto.QualiTestDAO;
import io.cucumber.java.en.Then;
import utils.SeleniumInitializer;

/**
 * CartPageStepDef contains the step definitions of the Cart Page.
 *
 * @author siddharth_patil
 */
public class CartPageStepDef extends SeleniumInitializer {

    private QualiTestDAO qualiTestDAO;
    private CartPageController cartPageController;

    public CartPageStepDef(QualiTestDAO qualiTestDAO) {
        this.qualiTestDAO = qualiTestDAO;
        cartPageController = new CartPageController(qualiTestDAO);
    }

    @Then("I am able to verify the item in my cart")
    public void iAmAbleToVerifyTheItemInMyCart() throws InterruptedException {
        cartPageController.openCartPage();
        cartPageController.verifyCartItems();
    }
}
