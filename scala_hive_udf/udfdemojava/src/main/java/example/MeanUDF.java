// MeanUDF.java
package example;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.hardik.letsdobigdata.MeanUDAF.MeanUDAFEvaluator.Column;

@Description(name = "Mean", value = "_FUNC(double) - computes mean", extended = "select col1, MeanFunc(value) from table group by col1;")

public class MeanUDAF extends UDAF {

// Define Logging
static final Log LOG = LogFactory.getLog(MeanUDAF.class.getName());

public static class MeanUDAFEvaluator implements UDAFEvaluator {

/**
 * Use Column class to serialize intermediate computation
 * This is our groupByColumn
 */
public static class Column {
 double sum = 0;
 int count = 0;
 }

private Column col = null;

public MeanUDAFEvaluator() {
 super();
 init();
 }
// A - Initalize evaluator - indicating that no values have been
 // aggregated yet.

public void init() {
 LOG.debug("Initialize evaluator");
 col = new Column();
 }

// B- Iterate every time there is a new value to be aggregated
 public boolean iterate(double value) throws HiveException {
 LOG.debug("Iterating over each value for aggregation");
 if (col == null)
 throw new HiveException("Item is not initialized");
 col.sum = col.sum + value;
 col.count = col.count + 1;
 return true;
 }
// C - Called when Hive wants partially aggregated results.
 public Column terminatePartial() {
 LOG.debug("Return partially aggregated results");
 return col;
 }
 // D - Called when Hive decides to combine one partial aggregation with another
 public boolean merge(Column other) {
 LOG.debug("merging by combining partial aggregation");
 if(other == null) {
 return true;
 }
 col.sum += other.sum;
 col.count += other.count;
 return true; 
}
 // E - Called when the final result of the aggregation needed.
 public double terminate(){
 LOG.debug("At the end of last record of the group - returning final result"); 
 return col.sum/col.count;
 }

 }
}
