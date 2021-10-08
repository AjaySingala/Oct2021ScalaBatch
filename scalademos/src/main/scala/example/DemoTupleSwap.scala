package example

object DemoTupleSwap {
   def main(args: Array[String]) {
      val t = new Tuple2("Scala", "hello")
      
      println("Before Swapping Tuple: " + t )
      println("Swapped Tuple: " + t.swap )
   }
}
