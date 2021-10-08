package example

// greet.scala
object greet {
	def greet(prefix: String, name: String) = s"$prefix $name"

	def main(args: Array[String]) {
		val greeting1 = greet("Ms", "Brown")
		println(greeting1)

		val greeting2 = greet(name = "Brown", prefix = "Mr")
		println(greeting2)

		val greeting3 = greet("Ms", name = "Chloe")
		println(greeting3)

		// This will give an error:
		//val greeting4 = greet( name= "Chloe", "Mr.")
	}
}
