// tryCatch.scala

import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException

object DemoTryCatch {
   def main(args: Array[String]) {
      //var n = 25 / 0
      tryDivideByZero()
      println("This is after tryDivideByZero...")

      tryFile()
      println("This is after tryFile...")
   }

   def tryDivideByZero() {
      try {
         var n = 25 / 0
      } catch {
         case i: ArithmeticException => { println("Arithmetic exception occurred...") }
         //println("ERROR! You cannot divide a number by ZERO!!!!")
      }
   }

   def tryFile() {
      // define f here.
      try {
         val f = new FileReader("input.txt")
         println("Successfully read the file...")
      } catch {
         case ex: FileNotFoundException =>{
            println("Missing file exception")
         }
         
         case ex: IOException => {
            println("IO Exception")
         }
      }
      finally {
         println("Exiting finally...")
      }
   }

   def tryDb() {
      var dbConn = 0
      try {
         dbConn = 10
         // fetch data from a particular table
         // process the data one row at a time
         // insert data into the DB => an exception occurs.
      }
      catch {
         // Handle exception.
         case ex: FileNotFoundException =>{
            println("Missing file exception")
         }
      }
      finally {
         println(dbConn)
      }      
   }
}