package example

object ReturnMultipleValues {  
    def main(args:Array[String]){  
        var tupleValues = tupleFunction()  
        println("Iterating values: ")  
        tupleValues.productIterator.foreach(println)    // Iterating tuple values using productIterator  
    }
    
    def tupleFunction()={  
        var tuple = (1,2.5,"India")  
        tuple  
    }  
}  
