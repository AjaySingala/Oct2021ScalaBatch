package example

object NewExceptionHandling {
    def main(args: Array[String]) {
        foo(100)
        
        try {
            foo(-1)
        }
        catch {
            case ex: MyCustomException => println("ERROR! You provided a wrong value...")
        }

        try {
            foo(0)
        }
        catch {
            case ex: MyCustomException => println("ERROR! You provided a wrong value...")
            case ex: AnotherCustomException => println("ERROR! You provided 0...")
        }

    }

    case class MyCustomException() extends Exception
    case class AnotherCustomException() extends Exception

    def foo(i: Int): Unit = {
        if(i == -1) {
            throw new MyCustomException
        }
        else if(i == 0) {
            throw new AnotherCustomException
        }

        println(s"You have passed $i as a value here...")

    }
}