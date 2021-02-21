package org.github.frikit.services

import org.github.frikit.models.cart.{Cart, CartCost}

class CartCalculator {

  def costOfCard(items: Cart): CartCost = {
    CartCost(items.items.map(e => e.price.price).sum)
  }

}
