package write;

//StatsCalculator.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * GUI application for reading numbers from a file, storing in a custom LinkedList,
 * calculating mean and standard deviation, and displaying results.
 */
public class StatsCalculator extends JFrame {
    private JButton chooseFileButton;
    private JTextArea outputArea;
    private MyLinkedList numbers;

    public StatsCalculator() {
        setTitle("Stats Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chooseFileButton = new JButton("Choose File");
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(chooseFileButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        numbers = new MyLinkedList();

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(StatsCalculator.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    processFile(selectedFile);
                }
            }
        });
    }

    /**
     * Processes the selected file: reads lines, parses doubles, handles errors,
     * calculates stats, and updates output.
     * @param file the file to process
     */
    private void processFile(File file) {
        numbers = new MyLinkedList(); // Reset list
        int invalidCount = 0;
        int lineNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }
                try {
                    double value = Double.parseDouble(line);
                    numbers.add(value);
                } catch (NumberFormatException ex) {
                    invalidCount++;
                    // Log or handle invalid input, but continue
                }
            }
        } catch (IOException ex) {
            outputArea.setText("Error reading file: " + ex.getMessage());
            return;
        }

        if (numbers.size() == 0) {
            outputArea.setText("No valid numbers found in the file.\nInvalid entries: " + invalidCount);
            return;
        }

        double sum = numbers.sum();
        double mean = sum / numbers.size();

        double sumSq = numbers.sumOfSquares(mean);
        double variance = sumSq / (numbers.size() - 1); // Sample standard deviation
        double stdDev = Math.sqrt(variance);

        StringBuilder output = new StringBuilder();
        output.append("Mean: ").append(String.format("%.2f", mean)).append("\n");
        output.append("Standard Deviation: ").append(String.format("%.2f", stdDev)).append("\n");
        output.append("Valid numbers: ").append(numbers.size()).append("\n");
        output.append("Invalid entries: ").append(invalidCount).append("\n");

        outputArea.setText(output.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StatsCalculator().setVisible(true);
            }
        });
    }
}
