Feature: My cucumber homework

  Scenario: Successfull login

    Given User is navigate to '' page
    When user login with username 'standard_user' and password 'secret_sauce'
    And user clicks login button

Scenario: Choose a product

  Given User is in Inventory page
  When user put one product in the cart
  And user go to cart
  Then user check is the product in the cart

  Scenario: Complete the order

    Given user navigates to checkout page
    When user enters name, surname and postal code
    Then user navigate to checkout overwiew page
    And check is data correct

    Scenario: Check if everything was ok and go back

      Given user navigates to finish page
      And check is everything was ok
      Then user navigates back to inventory page












