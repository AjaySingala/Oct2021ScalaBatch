// lazyval.scala
package example

// Scala program of Lazy val
// Creating object 
object GFG 
{ 
    // Main method 
    def main(args:Array[String]) { 
        lazy val geek = {
               
            println ("Initialization for the first time")
            12
        }
        // Part 1
        println("Calling 'geek' first time...")
        println(geek)
           
        // Part 2
        println("Calling 'geek' again...")
        print(geek)
    } 
}
