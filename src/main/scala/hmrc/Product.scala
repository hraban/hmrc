package hmrc

/**
 * All the products available to buy.
 *
 * @author Hraban Luyat <hraban@0brg.net>
 */
object Product extends Enumeration {
  type Product = Value

  val Apple = Value("Apple")
  val Orange = Value("Orange")

  def apply(name: String) = {
    if (name == null) {
      throw new NullPointerException
    }
    Product.withName(name)
  }
}
