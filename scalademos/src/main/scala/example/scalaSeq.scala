// scalaSeq.scala
package example

import scala.collection.immutable._
object SeqDemos {
  def main(args: Array[String]) {
    // var seq: Seq[Int] = Seq(52, 85, 1, 8, 3, 2, 7)
    // seq.foreach((element: Int) => print(element + " "))
    // println("\nAccessing element by using index")
    // println(seq(5))

    //ops()
    mergeSeqs()
  }

  def mergeSeqs() {
    val games = Seq("Football", "Soccer", "Golf", "Baseball")
    val alphabets = Seq("B", "D", "Z", "A", "E")
    val mergedSeqs = games ++ alphabets
    println("number of elements in games: " + games.size)
    println("number of elements in alphabets: " + alphabets.size)
    println("number of elements in mergedSeqs: " + mergedSeqs.size)
    println(mergedSeqs)
  }

  def ops() {
    var seq: Seq[Int] = Seq(52, 85, 1, 8, 3, 2, 7)
    seq.foreach((element: Int) => print(element + " "))
    println("\nis Empty: " + seq.isEmpty)
    println("Ends with (2,7): " + seq.endsWith(Seq(2, 7)))
    println("contains 8: " + seq.contains(8))
    println("last index of 3 : " + seq.lastIndexOf(3))
    println("Reverse order of sequence: " + seq.reverse)

  }
}
