package org.github.frikit.services

import org.github.frikit.BaseSpec
import org.github.frikit.models.Item
import org.github.frikit.models.cart.{Cart, CartCost}

class CartCalculatorSpec extends BaseSpec {

  private val apple = Item(name = "Apple", cost = 0.6d)
  private val orange = Item(name = "Orange", cost = 0.25d)

  private val emptyCart = Cart()
  private val emptyCartCost = CartCost(totalCost = 0d)

  private val cartWithOneItem = Cart(List(apple))
  private val cartCostWithOneItem = CartCost(apple.price.price)

  private val cartWithMultipleItems = Cart(List(apple, orange))
  private val cartCostWithMultipleItems = CartCost(apple.price.price + orange.price.price)

  private val cartCalculator = new CartCalculator()

  "Cart Calculator" should "return 0 price for empty cart" in {
    cartCalculator.costOfCard(emptyCart) should be(emptyCartCost)
  }

  it should "return correct price for one item in cart" in {
    cartCalculator.costOfCard(cartWithOneItem) should be(cartCostWithOneItem)
  }

  it should "return correct price for multiple items in cart" in {
    cartCalculator.costOfCard(cartWithMultipleItems) should be(cartCostWithMultipleItems)
  }

}
