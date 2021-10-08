// Underscore.scala
package example

object Underscore {
  def main(args: Array[String]) {
    testitemTransaction()

  }

  def testitemTransaction() {
    println(itemTransaction(130)) // Buy
    println(itemTransaction(150)) // Sell
    println(itemTransaction(70)) // Need approval
    println(itemTransaction(400)) // Need approval
  }

  def itemTransaction(price: Double): String = {
    price match {
      case 130 => "Buy"
      case 150 => "Sell"

      // if price is not any of 130 and 150, this case is executed
      case _ => "Need approval"
    }
  }
}
