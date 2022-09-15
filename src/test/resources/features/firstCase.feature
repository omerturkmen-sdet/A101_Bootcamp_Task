Feature: A101 Task

  @negative
  Scenario: User select random product with specific attributes and enter fake credit card information
    Given User on the A101 webpage
    When User navigate "Dizaltı Çorap" from "GİYİM"
    Then Verify that user on the products page
    When User select "SİYAH" from color filter
    Then Verify that only products in "SİYAH" displayed
    When User select random product
    Then Verify that user on the product page
    When User add product to cart and display
    Then Verify that product in cart is the same with selected from previous step
    When User confirm cart
    And User proceed without registration
    And User enter address information
    And User navigate payment page
    Then Verify that user on the payment page
    When User enter credit card information with fake data
    And User confirm order
    Then Verify that payment failed


  @positive
  Scenario: User select random product with specific attributes and enter valid credit card information
    Given User on the A101 webpage
    When User navigate "Dizaltı Çorap" from "GİYİM"
    And User select "SİYAH" from color filter
    Then Verify that only products in "SİYAH" displayed
    When User select random product
    Then Verify that user on the product page
    When User add product to cart and display
    Then Verify that product in cart is the same with selected from previous step
    When User confirm cart
    And User proceed without registration
    And User enter address information
    And User navigate payment page
    Then Verify that user on the payment page
    When User enter credit card information with valid data
    And User confirm order
    Then Verify that user shopped successfully