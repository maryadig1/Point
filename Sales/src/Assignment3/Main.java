package Assignment3;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static SalesSlip salesSlip = new SalesSlip();
    private static DefaultListModel<String> listModel = new DefaultListModel<>();
    private static JLabel totalLabel = new JLabel("Total Sales: $0.00");

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sales List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
        JButton addButton = new JButton("Add Item to the Sales List");

        inputPanel.add(new JLabel("Item Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Item Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(addButton);

        // List Panel
        JList<String> itemList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(itemList);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(totalLabel);

        // Add panels to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Button Action
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());

                    if (price >= 100) {
                        JOptionPane.showMessageDialog(frame, "Price must be less than $100.");
                        return;
                    }

                    SalesItem item = new SalesItem(name, price, quantity);
                    salesSlip.addItem(item);
                    listModel.addElement(item.toString());
                    totalLabel.setText("Total Sales: $" + String.format("%.2f", salesSlip.getTotalSales()));

                    nameField.setText("");
                    priceField.setText("");
                    quantityField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid price and quantity.");
                }
            }
        });

        frame.setVisible(true);
    }
}