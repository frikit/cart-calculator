package org.github.frikit.models

import java.util.UUID

case class Item(
                 id: UUID,
                 name: String,
                 price: ItemCost
               )

object Item {
  def apply(name: String, cost: Double): Item = {
    Item(id = UUID.randomUUID(), name = name, price = ItemCost(cost))
  }
}
