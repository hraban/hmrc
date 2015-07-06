package hmrc

/**
 * Extension of standard pricing, with some deals.
 *
 * @author Hraban Luyat
 */
object DealPricing extends Pricing {
  private val standard = StandardPricing

  def total(cart: Cart): Long = {
    cart.items.view.map {
      case (product, n) =>
        val deal = product match {
          case Product.Apple => (n / 2) + (n % 2)
          case Product.Orange => (n / 3) * 2 + (n % 3)
          case _ => n
        }
        standard.price(product) * deal
    }.sum
  }
}
