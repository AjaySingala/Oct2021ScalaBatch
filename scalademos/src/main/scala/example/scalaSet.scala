// scalaSet.scala
package example

import scala.collection.immutable._
object SetDemos {
  def main(args: Array[String]) {
    // // Ex.1
    // val set1 = Set() // An empty set
    // val games = Set(
    //   "Cricket",
    //   "Football",
    //   "Hockey",
    //   "Golf"
    // ) // Creating a set with elements
    // println(set1)
    // println(games)

    // fn2()
    // fn3()
    // fn4()
    fn5()
    // fn6()
    // fn7()
    // fn8()
    // fn9()
  }

  def fn2() {

    // Ex.2: get first or last element of Set and many more.
    val games = Set("Cricket", "Football", "Hockey", "Golf")
    println(games.head) // Returns first element present in the set
    println(games.tail) // Returns all elements except first element.
    println(games.isEmpty) // Returns either true or false
  }

  def fn3() {
    // Ex.3: merge two sets into a single set. Use ++.
    val games = Set("Cricket", "Football", "Hockey", "Golf")
    val alphabet = Set("A", "B", "C", "D", "E")
    val mergeSet = games ++ alphabet // Merging two sets
    println(
      "Elements in games set: " + games.size
    ) // Return size of collection
    println("Elements in alphabet set: " + alphabet.size)
    println("Elements in mergeSet: " + mergeSet.size)
    println(mergeSet)
  }

  def fn4() {
    // Ex.4: check whether element is present in the set or not.
    val games = Set("Cricket", "Football", "Hocky", "Golf")
    println(games)
    println("Elements in set: " + games.size)
    println("Golf exists in the set : " + games.contains("Golf"))
    println("Racing exists in the set : " + games.contains("Racing"))
  }

  def fn5() {
    // Ex.5: Adding / Removing elements.
    var games = Set("Cricket", "Football", "Hocky", "Golf")   // mutable.
    println(games)
    games += "Racing" // Adding new element
    println(games)
    games += "Cricket" // Adding new element, it does not allow duplicacy.
    println(games)
    games -= "Golf" // Removing element
    println(games)
  }

  def fn6() {
    // Ex.6: Iterating Set Elements using for loop
    var games = Set("Cricket", "Football", "Hocky", "Golf")
    for (game <- games) {
      println(game)
    }
  }

  def fn7() {
    // Ex.7: Iterating Elements using foreach loop.
    var games = Set("Cricket", "Football", "Hocky", "Golf")
    games.foreach((element: String) => println(element))
  }

  def fn8() {
    // Ex.8: Set operations.
    var games = Set("Cricket", "Football", "Hocky", "Golf", "C")
    var alphabet = Set("A", "B", "C", "D", "E", "Golf")
    var setIntersection = games.intersect(alphabet)
    println("Intersection by using intersect method: " + setIntersection)
    println("Intersection by using & operator: " + (games & alphabet))
    var setUnion = games.union(alphabet)
    println(setUnion)
  }

  def fn9() {
    // Ex.9: Sorted Set.
    var numbers: SortedSet[Int] = SortedSet(5, 8, 1, 2, 9, 6, 4, 7, 2)
    numbers.foreach((element: Int) => println(element))
  }
}
