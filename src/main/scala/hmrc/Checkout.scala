package hmrc

/**
 * A checkout system usable to compute prices for baskets of products.
 *
 * @author Hraban Luyat <hraban@0brg.net>
 */
class Checkout(pricing: Pricing) {
  def compute(cart: Cart): Long = {
    pricing.total(cart)
  }

  def compute(products: Traversable[Product.Product]): Long = compute(Cart.fromItems(products))
}
