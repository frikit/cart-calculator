package org.github.frikit.services

import org.github.frikit.BaseSpec
import org.github.frikit.models.Item
import org.github.frikit.models.cart.{Cart, CartCost}
import org.github.frikit.promotions.{ThreeOrangesForTwoPromotion, TwoApplesForOnePromotion}

class PromotionServiceSpec extends BaseSpec {

  private val apple = Item(name = "Apple", cost = 0.6d)
  private val orange = Item(name = "Orange", cost = 0.25d)

  private val emptyCart = Cart()
  private val emptyCartCost = emptyCart.copy(cartCost = CartCost(totalCost = 0d))

  private val cartWithMultipleItems = Cart(items = List(
    apple,
    apple,
    orange,
    orange,
    orange,
    orange,
    apple
  ))
  private val cartCostWithMultipleItems = cartWithMultipleItems.copy(cartCost = CartCost(
    apple.price.price +
      0 +
      orange.price.price +
      orange.price.price +
      0 +
      orange.price.price +
      apple.price.price +
      //round value
      -0.0000000000000002
  ))

  private val calculatorService = new CartCalculator()
  private val applePromotion = new TwoApplesForOnePromotion()
  private val orangePromotion = new ThreeOrangesForTwoPromotion()

  private val promotionService = new PromotionService()

  "Promotion service" should "return 0 price for empty cart" in {
    val cart = calculatorService.costOfCart(emptyCart)

    promotionService.applyPromotions(cart, applePromotion, orangePromotion) should be(emptyCartCost)
  }

  it should "return correct price for multiple items in cart" in {
    val cart = calculatorService.costOfCart(cartWithMultipleItems)

    promotionService.applyPromotions(cart, applePromotion, orangePromotion) should be(cartCostWithMultipleItems)
  }

}
