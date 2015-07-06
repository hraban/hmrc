package hmrc

import org.scalatest._

/**
 * @author Hraban Luyat <hraban@0brg.net>
 */
class CheckoutSpec extends FlatSpec with Matchers {
  import Product._

  "Product enum" should "parse known strings as products" in {
    Product("Apple") should be (Apple)
    Product("Orange") should be (Orange)
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
    checkout.compute(List(Apple)) should be (60)
    checkout.compute(List(Orange)) should be (25)
    checkout.compute(List(Apple, Apple, Orange, Apple)) should be (205)
  }

  it should "compute from a list of strings using map" in {
    val checkout = new Checkout(Pricing.standard)
    checkout.compute(List("Apple").map(Product(_))) should be (60)
    checkout.compute(List("Orange", "Apple", "Orange").view.map(Product(_))) should be (110)
  }

  it should "compute prices for deals correctly" in {
    val checkout = new Checkout(Pricing.deal)
    checkout.compute(List.empty[Product.Product]) should be (0)
    checkout.compute(List(Apple)) should be (60)
    checkout.compute(List(Apple, Apple)) should be (60)
    checkout.compute(List(Apple, Apple, Apple)) should be (120)
    checkout.compute(List(Apple, Apple, Apple, Apple)) should be (120)
    checkout.compute(List(Orange)) should be (25)
    checkout.compute(List(Orange, Orange)) should be (50)
    checkout.compute(List(Orange, Orange, Orange)) should be (50)
    checkout.compute(List(Orange, Orange, Orange, Orange)) should be (75)
    checkout.compute(List(Orange, Orange, Orange, Orange, Orange)) should be (100)
    checkout.compute(List(Orange, Orange, Orange, Orange, Orange, Orange)) should be (100)
  }

  it should "compute prices for deals correctly even when added out of order" in {
    val checkout = new Checkout(Pricing.deal)
    checkout.compute(List(Apple, Orange)) should be (85)
    checkout.compute(List(Apple, Orange, Apple)) should be (85)
    checkout.compute(List(Orange, Apple, Orange, Apple, Orange)) should be (110)
  }
}
