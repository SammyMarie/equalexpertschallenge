Feature: Shopping basket can contain several items and calculate item tax

  Scenario: Add products to the shopping cart.
    Given An empty shopping cart
    And A product, Dove Soap with a unit price of 39.99
    When The user adds 5 Dove Soaps to the shopping cart
    Then The shopping cart should contain 5 Dove Soaps each with a unit price of 39.99
    And The shopping cart’s total price should equal 199.95

