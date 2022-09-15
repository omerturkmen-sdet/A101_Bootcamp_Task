package com.a101.stepDefinitions;

import com.a101.pages.*;
import com.a101.utilities.CreditCardInformation;
import com.a101.utilities.Driver;
import com.a101.utilities.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class A101ShoppingStepDefs {

    A101HomePage homePage = new A101HomePage();
    ProductsPage productsPage = new ProductsPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    PaymentPage paymentPage = new PaymentPage();

    static String selectedCategoryName;
    String productTitleFromProductPage;
    String unitPriceFromProductPage;
    String quantityFromProductPage;
    @Given("User on the A101 webpage")
    public void user_on_the_a101_webpage() {
        Driver.get().get("https://www.a101.com.tr/");
    }

    @When("User navigate {string} from {string}")
    public void user_navigate_from(String subCategoryName, String categoryName) {
        selectedCategoryName = categoryName.toLowerCase();
        A101HomePage homePage = new A101HomePage();
        homePage.acceptCookies();
        homePage.navigateProduct(categoryName,subCategoryName);
    }

    @Then("Verify that user on the products page")
    public void verify_that_user_on_the_products_page() {
        assertTrue(productsPage.getFiltersElement().isDisplayed());
    }

    @When("User select {string} from color filter")
    public void user_select_from_color_filter(String selectedColor) {
        productsPage.selectColor(selectedColor);
    }
    @Then("Verify that only products in {string} displayed")
    public void verify_that_only_products_in_displayed(String selectedColor) {
        assertTrue(productsPage.getSelectedFilter().contains(selectedColor));
    }

    @When("User select random product")
    public void userSelectRandomProduct() {
        productsPage.selectRandomProduct();
    }

    @Then("Verify that user on the product page")
    public void verify_that_user_on_the_product_page() {
        assertTrue(productPage.getAddToBasketButton().isDisplayed());
    }

    @And("User add product to cart and display")
    public void userAddProductToCartAndDisplay() {
        productTitleFromProductPage = productPage.getProductTitle();
        unitPriceFromProductPage = productPage.getProductUnitPrice();
        quantityFromProductPage = productPage.getProductQuantity();
        productPage.addToBasketAndDisplay();
    }

    @Then("Verify that product in cart is the same with selected from previous step")
    public void verify_that_product_in_cart_is_the_same_with_selected_from_previous_step() {
        assertEquals(productTitleFromProductPage,cartPage.getItemTitle());
        assertEquals(unitPriceFromProductPage,cartPage.getItemUnitPriceAsString());
        assertEquals(quantityFromProductPage,cartPage.getQuantityAsString());
    }
    @When("User confirm cart")
    public void user_confirm_cart() {
        cartPage.confirmCart();
    }
    @When("User proceed without registration")
    public void user_proceed_without_registration() {
        checkoutPage.proceedWithoutRegistration();
    }

    @When("User enter address information")
    public void user_enter_address_information() {
        checkoutPage.fillAddressForm();
    }
    @When("User navigate payment page")
    public void user_navigate_payment_page() {
        checkoutPage.navigatePayment();
    }

    @Then("Verify that user on the payment page")
    public void verifyThatUserOnThePaymentPage() {
        Utils.waitForVisibility(paymentPage.getPaymentPageSubtitle());
        assertTrue(paymentPage.getPaymentPageSubtitle().isDisplayed());
    }


    @When("User enter credit card information with valid data")
    public void userEnterCreditCardInformationWithValidData() {
        paymentPage.makePaymentWithValidData(new CreditCardInformation());
    }

    @When("User enter credit card information with fake data")
    public void userEnterCreditCardInformationWithFakeData() {
        paymentPage.makePaymentWithFakeData();
    }
    @When("User confirm order")
    public void user_confirm_order() {
        paymentPage.acceptingContractAndCompleteOrder();
    }

    /**
     * It will pass only if credit card information is correct
     */
    @Then("Verify that user shopped successfully")
    public void verify_that_user_shopped_successfully() {
        paymentPage.lastAssertionAfterPaymentConfirmation();
    }


    @Then("Verify that payment failed")
    public void verifyThatPaymentFailed() {
        assertTrue(paymentPage.getCreditCardErrorMessage().isDisplayed());
    }
}
