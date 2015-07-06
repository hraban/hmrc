package hmrc

/**
 * Pricing schemes for products. Currently only one pricing scheme is defined.
 *
 * Prices are in GBP pennies.
 *
 * @author Hraban Luyat <hraban@0brg.net>
 */
object Pricing {
  /**
   * Default pricing.
   */
  def standard(product: Product.Product): Long = product match {
    case Product.Apple => 60
    case Product.Orange => 25
  }
}
