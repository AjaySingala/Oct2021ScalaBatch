package example

// Single line comments

/**
  * This Scala code is a simple demonstration
  * of how Tuples can be used. 
  */
object DemoTuple1 {
   /**
     * This is the entry point of the program.
     * And it receives arguments from the command-line.
     * Arguments are optional.
     * @param args
     */
   def main(args: Array[String]) {
      /*
         The following lines of code are to demonstrate
         how Tuples work. And specifically how to sum
         numbers in a tuple.
      */
      val t = (4,3,2,1)
      val sum = t._1 + t._2 + t._3 + t._4

      println( "Sum of elements: "  + sum )
   }

   /**
     * This method sums up 2 numnbers
     * passed as arguments.
     * @param i
     * @param j
     * @return
     */
   def add(i: Int, j: Int) : Int = {
      i + j
   }
}

