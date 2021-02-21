package org.github.frikit.promotions

import org.github.frikit.models.cart.{Cart, CartCost}

class TwoApplesForOnePromotion extends Promotion {
  override def applyPromotion(cart: Cart): Cart = {
    val applesInCart = cart.items.count(e => e.name.trim.toLowerCase().capitalize == "Apple")
    val appleInCart = cart.items.find(e => e.name.trim.toLowerCase().capitalize == "Apple")

    if (appleInCart.isDefined) {
      val priceOfAnApple = appleInCart.get.price.price
      val pairs = Math.floor(applesInCart / 2)

      if (pairs > 0) {
        val promotionReduction = priceOfAnApple * pairs
        val newCost = BigDecimal(cart.cartCost.totalCost - promotionReduction).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
        cart.copy(cartCost = CartCost(newCost))
      } else {
        cart
      }
    } else {
      cart
    }
  }
}
