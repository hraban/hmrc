package hmrc

/**
 * Compute the price of a cart.
 *
 * Prices are in GBP pennies.
 *
 * @author Hraban Luyat <hraban@0brg.net>
 */
trait Pricing {
  def total(cart: Cart): Long
}



