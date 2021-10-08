package example

object FunctionWithVarArgs {
    def main(args: Array[String]) {
        printStrings("Hello", "there")
        printStrings("Hello", "there", "How", "are", "you", "doing?")
        printStrings("This", "is", "another", "call", "to", "the" , "method")
    }

    def printStrings(messages: String*) = {
        var i: Int = 0
        for(aMessage <- messages) {
            println(s"Message number $i is : $aMessage")
            i += 1
        }
    }
}

