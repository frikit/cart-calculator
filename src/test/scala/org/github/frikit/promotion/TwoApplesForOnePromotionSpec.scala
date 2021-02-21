package org.github.frikit.promotion

import org.github.frikit.BaseSpec
import org.github.frikit.models.Item
import org.github.frikit.models.cart.{Cart, CartCost}
import org.github.frikit.promotions.TwoApplesForOnePromotion
import org.github.frikit.services.CartCalculator

class TwoApplesForOnePromotionSpec extends BaseSpec {

  private val apple = Item(name = "Apple", cost = 0.6d)
  private val orange = Item(name = "Orange", cost = 0.25d)

  private val emptyCart = Cart()
  private val emptyCartCost = emptyCart.copy(cartCost = CartCost(totalCost = 0d))

  private val cartWithOneItem = Cart(items = List(apple))
  private val cartCostWithOneItem = cartWithOneItem.copy(cartCost = CartCost(apple.price.price))

  private val cartWithMultipleItems = Cart(items = List(apple, orange, apple))
  private val cartCostWithMultipleItems = cartWithMultipleItems.copy(cartCost = CartCost(
    apple.price.price + orange.price.price
  ))

  private val applePromotion = new TwoApplesForOnePromotion()
  private val calculatorService = new CartCalculator()

  "Apples promotion" should "return same cart for empty cart" in {
    val cart = calculatorService.costOfCart(emptyCart)

    applePromotion.applyPromotion(cart) should be(emptyCartCost)
  }

  it should "return correct price for one item in cart" in {
    val cart = calculatorService.costOfCart(cartWithOneItem)

    applePromotion.applyPromotion(cart) should be(cartCostWithOneItem)
  }

  it should "return correct price for multiple items in cart" in {
    val cart = calculatorService.costOfCart(cartWithMultipleItems)

    applePromotion.applyPromotion(cart) should be(cartCostWithMultipleItems)
  }

}
