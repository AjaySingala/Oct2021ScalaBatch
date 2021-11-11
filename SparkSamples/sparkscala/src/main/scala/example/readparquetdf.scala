// readparquetdf.scala
package example

import org.apache.spark.sql.SparkSession

object ParquetExample {

  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession
      .builder()
      .master("local[1]")
      .appName("AjaySingala.com")
      .getOrCreate()

    println("Define the data...")
    //  Create a Spark DataFrame from Seq object.
    val data = Seq(
      ("James ", "", "Smith", "36636", "M", 3000),
      ("Michael ", "Rose", "", "40288", "M", 4000),
      ("Robert ", "", "Williams", "42114", "M", 4000),
      ("Maria ", "Anne", "Jones", "39192", "F", 4000),
      ("Jen", "Mary", "Brown", "", "F", -1)
    )

    println("Define the schema...")
    val columns =
      Seq("firstname", "middlename", "lastname", "dob", "gender", "salary")

    println("Create the DF...")
    import spark.sqlContext.implicits._
    val df = data.toDF(columns: _*)
    df.show()
    df.printSchema()

    // Write DataFrame to Parquet file format.
    println("Write the DF to a Parquet file...")
    df.write.parquet("/tmp/output/people.parquet")

    // Read Parquet file into DataFrame.
    println("Read the Parquet file...")
    val parqDF = spark.read.parquet("/tmp/output/people.parquet")
    // parqDF.printSchema()
    // parqDF.show()

    // Append to existing Parquet file.
    println("Append to an existing Parquet...")
    df.write.mode("append").parquet("/tmp/output/people.parquet")

    // Using SQL queries on Parquet.
    println("Create View for Parquet...")
    parqDF.createOrReplaceTempView("ParquetTable")
    println("Explain the SQL statement to be exeucted on the Parquet View...")
    spark.sql("select * from ParquetTable where salary >= 4000").explain()
    println("Fetch data from the view using SQL syntax...")
    val parkSQL = spark.sql("select * from ParquetTable where salary >= 4000")
    parkSQL.show()
    parkSQL.printSchema()

    // Spark parquet partition – Improving performance.
    println("Creating parquet with partitions...")
    df.write
      .partitionBy("gender", "salary")
      .parquet("/tmp/output/people2.parquet")

    // Execution of this query is significantly faster than the query without partition.
    // It filters the data first on gender and then applies filters on salary.
    println("Read the paritioned parquet...")
    val parqDF2 = spark.read.parquet("/tmp/output/people2.parquet")
    println("Fetch data using SQL syntax from the partitioned parquet DF...")
    parqDF2.createOrReplaceTempView("ParquetTable2")
    val df3 =
      spark.sql(
        "select * from ParquetTable2 where gender='M' and salary >= 4000"
      )
    df3.explain()
    df3.printSchema()
    df3.show()

    // Read a specific Parquet partition.
    println("Read data from a specifc parition...")
    val parqDF3 = spark.read.parquet("/tmp/output/people2.parquet/gender=M")
    parqDF3.show()
  }
}
