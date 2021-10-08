package example

// HandlingFailures.scala
// scalac HandlingFailures.scala
// scala example.ExceptionHandling

//package com.ajaysingala.scala.handlingFailures
import scala.util.Try
import scala.util.{Success, Failure}

object ExceptionHandling {

  case class DivideByZero() extends Exception

  def divide(dividend: Int, divisor: Int): Int = {
    if (divisor == 0) {
      throw new DivideByZero
    }

    dividend / divisor
  }

  /** Exception handling by try/catch/finally
    */
  def divideByZero(a: Int): Any = {
    try {
      divide(a, 0)
    } catch {
      case e: DivideByZero => println(s"You are trying to divide by zero. $e")
    } finally {
      println("Finished")
    }
  }

  /** Exception handling by Try/Success/Failures
    */
  def divideWithTry(dividend: Int, divisor: Int): Try[Int] = Try(
    divide(dividend, divisor)
  )

  /** Exception handling by Option/Some/None
    */
  def divideWithOption(dividend: Int, divisor: Int): Option[Int] = {
    if (divisor == 0) {
      None
    } else {
      Some(dividend / divisor)
    }
  }

  /** Exception handling by Either/Left/Right
    */
  def divideWithEither(dividend: Int, divisor: Int): Either[String, Int] = {
    if (divisor == 0) {
      Left("Can't divide by zero")
    } else {
      Right(dividend / divisor)
    }
  }

  def main(args: Array[String]) {
    // val res1 = divide(20, 10);
    // println(res1);
    // // val res2 = divide(20, 0);
    // // println(res1);

    // println(divideByZero(20));

    // println(divideWithTry(20, 10))    // Success
    // println(divideWithTry(20, 0));    // Failure.

    // val result = divideWithTry(20,0) match {
    //   case Success(i) => i
    //   case Failure(DivideByZero()) => None
    // }
    // println(result)

    // val result2 = divideWithTry(20,10) match {
    //   case Success(i) => i
    //   case Failure(DivideByZero()) => None
    // }
    // println(result2)

    // println(divideWithOption(20, 0));
    // println(divideWithOption(20, 10))

    println(divideWithEither(20, 0));
    println(divideWithEither(20, 10))
  }

}
