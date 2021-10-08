// traitPrintable.scala
package example

trait Printable{  
    def print()  
}  

trait Orientation {
    def set(format: String)
}

class Document extends Printable with Orientation {
    def print() {
        println("This is a document to be printed.")
    }

    def set(format: String) {
        println(s"It will be in $format")
    }
}

class A4 extends Printable{  
    def print(){  
        println("Hello from A4")  
    }  
}  

class Legal extends Printable {
    def print() {
        println("This is legal size paper.")
    }
}

object MainObject{  
    def main(args:Array[String]){  
        var a = new A4()  
        a.print()

        var leg = new Legal()
        leg.print()

        var doc = new Document()
        doc.print()
        doc.set("Portrait")        
    }  
}  
