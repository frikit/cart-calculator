package org.github.frikit.services

import org.github.frikit.models.cart.{Cart, CartCost}

class CartCalculator {

  def costOfCart(items: Cart): Cart = {
    items.copy(
      cartCost = CartCost(items.items.map(e => e.price.price).sum)
    )
  }

}
