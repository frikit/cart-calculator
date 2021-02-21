package org.github.frikit.promotions

import org.github.frikit.models.cart.Cart

trait Promotion {
  def applyPromotion(cart: Cart): Cart
}
