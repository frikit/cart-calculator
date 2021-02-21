package org.github.frikit.services

import org.github.frikit.models.cart.{Cart, CartCost}

class CartCalculator {

  def costOfCart(cart: Cart): Cart = {
    val sum = cart.items.map(e => e.price.price).sum
    val newCost = BigDecimal(sum).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
    cart.copy(cartCost = CartCost(newCost))
  }
}
