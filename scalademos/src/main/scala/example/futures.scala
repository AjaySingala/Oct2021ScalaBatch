package example

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.util.Try
import scala.util.{Success, Failure}

object futures {
    def main(args: Array[String]) {
        val magicNumFuture: Future[Int] = Future {
            generateMagicNumber()
        }
        magicNumFuture.onComplete(printResult)
        println(magicNumFuture)
    }

    // Callback.
    def printResult[T](result: Try[T]): Unit = result match {
        case Failure(exception) => println("Failed with: " + exception.getMessage)
        case Success(number) => println("Success with: " + number)
    }

    def generateMagicNumber(): Int = {
        println("Waiting....")
        Thread.sleep(3000)
        println("Done...")
        23
    }
}