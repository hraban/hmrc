package hmrc

/**
 * @author Hraban Luyat
 */
object StandardPricing extends Pricing {
  def price(product: Product.Product): Long = product match {
    case Product.Apple => 60
    case Product.Orange => 25
  }

  def total(cart: Cart): Long = {
    cart.items.view.map {
      case (product, n) => price(product) * n
    }.sum
  }
}
