package hmrc

import org.scalatest._

/**
 * @author Hraban Luyat <hraban@0brg.net>
 */
class CheckoutSpec extends FlatSpec with Matchers {
  "Product enum" should "parse known strings as products" in {
    Product("Apple") should be (Product.Apple)
    Product("Orange") should be (Product.Orange)
  }

  it should "reject unknown product names" in {
    intercept[NoSuchElementException] {
      Product("")
    }
    intercept[NullPointerException] {
      Product(null)
    }
    intercept[NoSuchElementException] {
      Product("Passion-fruit")
    }
  }

  "A checkout system" should "compute prices correctly" in {
    val checkout = new Checkout(Pricing.standard)
    checkout.compute(List.empty[Product.Product]) should be (0)
    checkout.compute(List(Product.Apple)) should be (60)
    checkout.compute(List(Product.Orange)) should be (25)
    checkout.compute(List(Product.Apple, Product.Apple, Product.Orange, Product.Apple)) should be (205)
  }

  it should "compute from a list of strings using map" in {
    val checkout = new Checkout(Pricing.standard)
    checkout.compute(List("Apple").map(Product(_))) should be (60)
    checkout.compute(List("Orange", "Apple", "Orange").view.map(Product(_))) should be (110)
  }
}
