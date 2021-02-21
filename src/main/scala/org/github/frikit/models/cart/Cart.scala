package org.github.frikit.models.cart

import org.github.frikit.models.Item

import java.util.UUID

case class Cart(
                 id: UUID = UUID.randomUUID(),
                 items: List[Item] = Nil,
                 cartCost: CartCost = CartCost(0d)
               )
