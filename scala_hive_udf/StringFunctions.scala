// StringFunctions.scala
package example;

import org.apache.hadoop.hive.ql.exec.UDF

class TrimString extends UDF {
  def evaluate(str: String): String = {
    str.trim
  }
}

class UpperCaseString extends UDF {
  def evaluate(str: String): String = {
    str.toUpperCase
  }
}

class LowerCaseString extends UDF {
  def evaluate(str: String): String = {
    str.toLowerCase
  }
}

