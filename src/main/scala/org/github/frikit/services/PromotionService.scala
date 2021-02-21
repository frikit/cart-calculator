package org.github.frikit.services

import org.github.frikit.models.cart.Cart
import org.github.frikit.promotions.Promotion

class PromotionService {

  def applyPromotions(cart: Cart, promotions: Promotion*): Cart = {
    var r = cart
    for (p <- promotions) {
      r = p.applyPromotion(r)
    }
    r
  }
}
