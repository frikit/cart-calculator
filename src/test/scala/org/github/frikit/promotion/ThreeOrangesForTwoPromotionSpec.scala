package org.github.frikit.promotion

import org.github.frikit.BaseSpec
import org.github.frikit.models.Item
import org.github.frikit.models.cart.{Cart, CartCost}
import org.github.frikit.promotions.ThreeOrangesForTwoPromotion
import org.github.frikit.services.CartCalculator

class ThreeOrangesForTwoPromotionSpec extends BaseSpec {

  private val apple = Item(name = "Apple", cost = 0.6d)
  private val orange = Item(name = "Orange", cost = 0.25d)

  private val emptyCart = Cart()
  private val emptyCartCost = emptyCart.copy(cartCost = CartCost(totalCost = 0d))

  private val cartWithOneItem = Cart(items = List(orange))
  private val cartCostWithOneItem = cartWithOneItem.copy(cartCost = CartCost(orange.price.price))

  private val cartWithTwoItems = Cart(items = List(orange, orange))
  private val cartCostWithTwoItems = cartWithTwoItems.copy(cartCost = CartCost(orange.price.price + orange.price.price))

  private val cartWithMultipleItems = Cart(items = List(apple, orange, apple, orange, orange, orange))
  private val cartCostWithMultipleItems = cartWithMultipleItems.copy(cartCost = CartCost(
    apple.price.price + orange.price.price + apple.price.price + orange.price.price + orange.price.price
  ))

  private val orangesPromotion = new ThreeOrangesForTwoPromotion()
  private val calculatorService = new CartCalculator()

  "Orange Promotion" should "return same cart for empty cart" in {
    val cart = calculatorService.costOfCart(emptyCart)

    orangesPromotion.applyPromotion(cart) should be(emptyCartCost)
  }

  it should "return correct price for one item in cart" in {
    val cart = calculatorService.costOfCart(cartWithOneItem)

    orangesPromotion.applyPromotion(cart) should be(cartCostWithOneItem)
  }

  it should "return correct price for two items in cart" in {
    val cart = calculatorService.costOfCart(cartWithTwoItems)

    orangesPromotion.applyPromotion(cart) should be(cartCostWithTwoItems)
  }

  it should "return correct price for multiple items in cart" in {
    val cart = calculatorService.costOfCart(cartWithMultipleItems)

    orangesPromotion.applyPromotion(cart) should be(cartCostWithMultipleItems)
  }

}
