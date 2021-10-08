// ExtendClass.scala
import java.io._

class Point2(val xc: Int, val yc: Int) {
   var x: Int = xc
   var y: Int = yc
   
   def move(dx: Int, dy: Int) {
      x = x + dx
      y = y + dy
      println ("Point x location : " + x);
      println ("Point y location : " + y);
   }
}

class Location(override val xc: Int, override val yc: Int,
   val zc :Int) extends Point2(xc, yc){
   var z: Int = zc

   // Using default values.
   def printloc(px: Int = xc, py:Int = zc, pz:Int = yc) {
      println ("Point x location : " + px);
      println ("Point y location : " + py);
      println ("Point z location : " + pz);
   }

   def move(dx: Int, dy: Int, dz: Int) {
      x = x + dx
      y = y + dy
      z = z + dz
      printloc(x,y,z);
   }
}

object Demo2 {
   def main(args: Array[String]) {
      val loc = new Location(10, 20, 15);

      println("Original location:")
      loc.printloc()

      // Move to a new location
      println("New location:")
      loc.move(10, 10, 5);
   }
}