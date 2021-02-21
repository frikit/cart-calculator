package org.github.frikit.promotions

import org.github.frikit.models.cart.{Cart, CartCost}

class ThreeOrangesForTwoPromotion extends Promotion {
  override def applyPromotion(cart: Cart): Cart = {
    val orangesInCart = cart.items.count(e => e.name.trim.toLowerCase().capitalize == "Orange")
    val orangeInCart = cart.items.find(e => e.name.trim.toLowerCase().capitalize == "Orange")

    if (orangeInCart.isDefined) {
      val priceOfAnOrange = orangeInCart.get.price.price
      val pairs = Math.floor(orangesInCart / 3)

      if (pairs > 0) {
        val promotionReduction = priceOfAnOrange * pairs
        val promotionReductionRound = BigDecimal(promotionReduction).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
        val newCost = BigDecimal(cart.cartCost.totalCost - promotionReductionRound).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
        cart.copy(cartCost = CartCost(newCost))
      } else {
        cart
      }
    } else {
      cart
    }
  }
}
