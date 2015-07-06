package hmrc

/**
 * A checkout system usable to compute prices for baskets of products.
 *
 * @author Hraban Luyat <hraban@0brg.net>
 */
class Checkout(price: Product.Product => Long) {
  def compute(products: Iterable[Product.Product]): Long = {
    products.view.map(price).sum
  }
}
