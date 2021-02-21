package org.github.frikit

import org.github.frikit.models.Item
import org.github.frikit.models.cart.Cart
import org.github.frikit.promotions.{ThreeOrangesForTwoPromotion, TwoApplesForOnePromotion}
import org.github.frikit.services.{CartCalculator, PromotionService}

object Main extends App {

  //PART 1
  //add item in the system
  val apple = Item("Apple", 0.6)
  val oranges = Item("Orange", 0.25)

  //init cart
  val itemsInCart = List(
    apple.copy(),
    apple.copy(),
    oranges.copy(),
    oranges.copy(),
    oranges.copy(),
    oranges.copy(),
    apple.copy(),
  )

  val cart = Cart(items = itemsInCart)

  val costCalculator = new CartCalculator()
  val cartCost = costCalculator.costOfCart(items = cart)

  val roundPrice = BigDecimal(cartCost.cartCost.totalCost).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  println(s"Total cost is ${roundPrice}")

  //PART 2
  val applePromotion = new TwoApplesForOnePromotion()
  val promotion1 = applePromotion.applyPromotion(cart = cartCost)

  println(s"Total cost with apple promotion: ${promotion1.cartCost.totalCost}")

  val orangePromotion = new ThreeOrangesForTwoPromotion()
  val promotion2 = orangePromotion.applyPromotion(cart = cartCost)

  println(s"Total cost with orange promotion: ${promotion2.cartCost.totalCost}")

  //apply multiple promotions
  val promotionService = new PromotionService()
  val finalCart = promotionService.applyPromotions(cartCost, applePromotion, orangePromotion)

  //0.6 + 0 + 0.25 + 0.25 + 0 + 0.25 + 0.6
  println(s"Total cost with apples and oranges promotions: ${finalCart.cartCost.totalCost}")


}
