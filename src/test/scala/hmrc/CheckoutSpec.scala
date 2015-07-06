package hmrc

import org.scalatest._

/**
 * Created by hraban on 04/07/15.
 */
class CheckoutSpec extends FlatSpec {
  private def price(product: Product): Long = product match {
    case Product.Apple => 60
    case Product.Orange => 25
  }

  "Product enum" should "parse known strings as products" in {
    Product("Apple") should be (Product.Apple)
    Product("Orange") should be (Product.Orange)
  }

  it should "reject unknown product names" in {
    intercept[IllegalArgumentException] {
      Product("")
    }
    intercept[NullPointerException] {
      Product(null)
    }
    intercept[IllegalArgumentException] {
      Product("Passion-product")
    }
  }

  "A checkout system" should "compute prices correctly" in {
    val checkout = new Checkout(price)
    checkout.compute(List.empty) should be (0)
    checkout.compute(List(Product.Apple)) should be (60)
    checkout.compute(List(Product.Orange)) should be (25)
    checkout.compute(List(Product.Apple, Product.Apple, Product.Orange, Product.Apple)) should be (205)
  }

  it should "accept a list of strings" in {
    val checkout = new Checkout(price)
    checkout.compute(List("Apple")) should be (60)
    checkout.compute(List("Orange")) should be (25)
    checkout.compute(List("Orange", "Apple", "Orange")) should be (110)
  }
}
