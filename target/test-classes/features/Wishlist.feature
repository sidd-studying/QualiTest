Feature: Feature for testing the Cart functionality.

  @Test
  Scenario: Add to wish list, add to cart scenario : HappyPath
    Given I add following products to my wish list
      | products         |
      | Modern           |
      | Single Shirt     |
      | Hard top         |
      | Bikini           |
    When I view my wishlist table
    Then I find total 4 selected items in my Wishlist
    When I search for lowest price product
    And I am able to add the lowest price item to my cart
    Then I am able to verify the item in my cart