package write;

import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class StatsCalculatorTest {

 @Test
 public void testProcessFileValid() throws IOException {
     File tempFile = createTempFile("1.0\n2.0\n3.0\n");
     StatsCalculator calc = new StatsCalculator();
     calc.processFile(tempFile);

     // Since outputArea is private, in real test, we might need to expose or use reflection.
     // For this example, assume we add a getter for outputArea in StatsCalculator for testing.
     // String output = calc.getOutputArea().getText();
     // Assert output contains expected values.
     // Mean: 2.00, Std Dev: ~1.00 (sqrt(2/2) = 1)
 }

 @Test
 public void testProcessFileInvalid() throws IOException {
     File tempFile = createTempFile("1.0\nabc\n3.0\n");
     StatsCalculator calc = new StatsCalculator();
     calc.processFile(tempFile);
     // Check output for invalid count == 1, valid == 2
 }

 @Test
 public void testProcessFileEmpty() throws IOException {
     File tempFile = createTempFile("");
     StatsCalculator calc = new StatsCalculator();
     calc.processFile(tempFile);
     // Check output for no valid numbers
 }

 @Test
 public void testProcessFileOneLine() throws IOException {
     File tempFile = createTempFile("5.0\n");
     StatsCalculator calc = new StatsCalculator();
     calc.processFile(tempFile);
     // Mean: 5.00, Std Dev: NaN or 0 (since n-1=0, handle appropriately)
     // In code, if size < 2, stdDev = 0 or note.
 }

 @Test
 public void testProcessFileEmptyLines() throws IOException {
     File tempFile = createTempFile("1.0\n\n3.0\n");
     StatsCalculator calc = new StatsCalculator();
     calc.processFile(tempFile);
     // Should skip empty, valid == 2
 }

 private File createTempFile(String content) throws IOException {
     File temp = File.createTempFile("test", ".txt");
     try (BufferedWriter writer = new BufferedWriter(new FileWriter(temp))) {
         writer.write(content);
     }
     return temp;
 }
}