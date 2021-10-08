// UnderscoreSpec.scala
package example

import org.scalatest.FunSuite

class UnderscoreSpec extends FunSuite {
  test("UnderscoreTest.Existential Types") {
    def getLength(x: List[List[_]]): Int = x.length

    assert(getLength(List(List(8), List("str"))) == 2)
    //assert(getLength(List(List(8), List("str"))) == 1)
    assert(getLength(List(List(5.00), List("str"))) == 2)
    assert(getLength(List(List(Array(7)), List("str"))) == 2)

  }

  test("UnderscoreTest.Ignoring things #1") {
    val ints = (1 to 4).map(_ => "Int")
    assert(ints == Vector("Int", "Int", "Int", "Int"))
  }

  test("UnderscoreTest.Ignoring things #2") {
    val prices = Seq(10.00, 23.38, 49.82)
    val pricesToInts = prices.map(_.toInt)
    assert(pricesToInts == Seq(10, 23, 48))
  }

  test("UnderscoreTest.Access Nested Collections") {
    val items = Seq(
      ("candy", 2, true),
      ("cola", 7, false),
      ("apple", 3, false),
      ("milk", 4, true)
    )
    val itemsToBuy = items
      .filter(_._3) // filter in only available items (true)
      .filter(_._2 > 3) // filter in only items with price greater than 3
      .map(_._1) // return only the first element of the tuple; the item name
    assert(itemsToBuy == Seq("milk"))
  }

  test("UnderscoreTest.Ignored Variable #1") {
    // we want only the first element in a split string.
    val text = "a,b"
    val Array(a, _) = text.split(",")
    assert(a == "a")

    // we want only the second one.
    val Array(_, b) = text.split(",")
    assert(b == "b")

    // extend this to more than two entries.
    // To ignore the rest of the entries after the first, we use the underscore together with *.
    val text2 = "x,a,b,c,d,e"
    val Array(x, _*) = text2.split(",")
    assert(x == "x")
  }

  test("UnderscoreTest.Ignored Variable #2") {
    // ignore randomly using the underscore for any one entry we don’t want.
    val text = "a,b,c,d,e"
    val Array(a, b, _, d, e) = text.split(",")
    assert(a == "a")
    assert(b == "b")
    assert(d == "d")
    assert(e == "e")
  }

  test("UnderscoreTest.Conversions - Function Reassignment (Eta expansion)") {
    def multiplier(a: Int, b: Int): Int = a * b

    val times = multiplier _ // reassign multiplier to times
    assert(multiplier(8, 13) == times(8, 13))
  }

  test("UnderscoreTest.Conversions - Variable Argument Sequence") {
    def sum(args: Int*): Int = {
      args.reduce(_ + _)
    }
    val sumable = Seq(4, 5, 10, 3)
    
    val sumOfSumable = sum(
      sumable: _*
    ) // convert the sequence sumable to varargs using sumable: _*
    assert(sumOfSumable == 22)
  }

  test("UnderscoreTest.Conversions - Partially-Applied Function") {
    def sum2(x: Int, y: Int): Int = x + y
    val sumToTen = sum2(10, _: Int)   // Partially Applied Function.
    val sumFiveAndTen = sumToTen(25)

    assert(sumFiveAndTen == 35)

  }

  test(
    "UnderscoreTest.Conversions - Assignment Operators - Setters overriding"
  ) {
    class Product {
      private var a = 0
      def price = a
      def price_=(i: Int): Unit = {
        require(i > 10)
        a = i
      }
    }

    val product = new Product
    product.price = 20
    assert(product.price == 20)

    // // First try this and see the exception.
    //product.price = 7 // will fail because 7 is not greater than 10

    // Then, comment the above line, uncomment these lines and run again.
    try {
      product.price = 7 // will fail because 7 is not greater than 10
      fail("Price must be greater than 10")
    } catch {
      case _: IllegalArgumentException => assert(product.price != 7)
    }
  }

  test(
    "UnderscoreTest.Miscellaneous Usages - Joining Letters to Operators and Punctuation"
  ) {
    def list_++(list: List[_]): List[_] = List.concat(list, list)
    val concatenatedList = list_++(List(2, 5))
    assert(concatenatedList == List(2, 5, 2, 5))
  }

  test("UnderscoreTest.Miscellaneous Usages - Higher-Kinded Type") {
    trait ObjectContainer[T[_]] { // higher-kinded type parameter
      def checkIfEmpty(collection: T[_]): Boolean
    }
    object SeqContainer extends ObjectContainer[Seq] {
      override def checkIfEmpty(collection: Seq[_]): Boolean =
        !collection.nonEmpty
    }

    var seqIsEmpty = SeqContainer.checkIfEmpty(Seq(7, "anything"))
    assert(seqIsEmpty == false)
    seqIsEmpty = SeqContainer.checkIfEmpty(Seq())
    assert(seqIsEmpty == true)

    object ListContainer extends ObjectContainer[List] {
      override def checkIfEmpty(collection: List[_]): Boolean =
        !collection.nonEmpty
    }

    var listIsempty = ListContainer.checkIfEmpty(List(1,2,3,4,5))
    var listIsempty2 = ListContainer.checkIfEmpty(List())


  }
}
