// scalaMap.scala
package example

object MapDemos {
  def main(args: Array[String]) {
    var map = Map(("A", "Apple"), ("B", "Ball"))
    var map2 = Map("A" -> "Aple", "B" -> "Ball")
    var emptyMap: Map[String, String] = Map.empty[String, String]
    println(map)
    println(map2)
    println("Empty Map: " + emptyMap)

    addRemoveElements
  }

  def addRemoveElements() {
    var map = Map("A" -> "Apple", "B" -> "Ball") // Creating map
    println(map("A")) // Accessing value by using key
    var newMap = map + ("C" -> "Cat") // Adding a new element to map
    println(newMap)
    var removeElement = newMap - ("B") // Removing an element from map
    println(removeElement)
  }
}
