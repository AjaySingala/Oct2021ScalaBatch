// transform.scala
package example

// Scala program of transform() method
 
// Creating object
object transformDemo
{ 
    // Main method
    def main(args:Array[String])
    {
        // Creating a map
        val m1 = Map(3 -> "samples", 4 -> "for", 2 -> "scala")
          
        // Applying transform method
        val result = m1.transform((key,value) => value.toUpperCase)
          
        // Displays output
        println(result)
    }
}

