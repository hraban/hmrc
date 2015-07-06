package hmrc

/**
 * Shopping cart holding products
 *
 * @author Hraban Luyat <hraban@0brg.net>
 */
case class Cart(items: Map[Product.Product, Long] = Map.empty) {
  def add(product: Product.Product) = {
    val n = items.getOrElse(product, 0L) + 1
    copy(items + (product -> n))
  }
}

object Cart {
  def fromItems(items: Traversable[Product.Product]): Cart = {
    items.foldLeft(new Cart)(_.add(_))
  }
}
