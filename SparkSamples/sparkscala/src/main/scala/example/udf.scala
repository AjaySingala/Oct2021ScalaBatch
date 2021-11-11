package example

// udf.scala
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{Row, SparkSession}

object SparkUDF extends App{

  val spark: SparkSession = SparkSession.builder()
    .master("local[1]")
    .appName("AjaySingala.com")
    .getOrCreate()

  import spark.implicits._
  println("Defining the schema...")
  val columns = Seq("Seqno","Quote")
  println("Defining the data...")
  val data = Seq(("1", "Be the change that you wish to see in the world"),
    ("2", "Everyone thinks of changing the world, but no one thinks of changing himself."),
    ("3", "The purpose of our lives is to be happy.")

  )
  println("Creating the DF with schema...")
  val df = data.toDF(columns:_*)
  df.show(false)

  println("Defining UDF to conver case...")
  val convertCase =  (str:String) => {
    val arr = str.split(" ")
    arr.map(f=>  f.substring(0,1).toUpperCase + f.substring(1,f.length)).mkString(" ")
  }

  //Using with DataFrame
  println("Create udf object...")
  val convertUDF = udf(convertCase)
  println("Call UDF on DF...")
  df.select(col("Seqno"),
    convertUDF(col("Quote")).as("Quote") ).show(false)

  // Using it on SQL
  println("Register the UDF for SQL syntax usage...")
  spark.udf.register("convertUDF", convertCase)
  println("Call UDF on on View using SQL syntax...")
  df.createOrReplaceTempView("QUOTE_TABLE")
  spark.sql("select Seqno, convertUDF(Quote) from QUOTE_TABLE").show(false)
  
}
