import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class D2Try2 extends JFrame{
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton calculateButton;

    public D2Try2() {
        setTitle("Tangent Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Enter angle (in radians):"));
        inputField = new JTextField(10);
        inputPanel.add(inputField);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTan();
            }
        });
        inputPanel.add(calculateButton);

        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void calculateTan() {
        try {
            double input = Double.parseDouble(inputField.getText());
            double result = tan(input);
            outputArea.setText(String.format("tan("+ input + ") = " + result));
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Please enter a valid number.");
        } catch (ArithmeticException e) {
            outputArea.setText("Error: " + e.getMessage());
        } catch (Exception e) {
            outputArea.setText("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static double tan(double x) throws ArithmeticException {
        x = normalize(x);

        if (x == 0) {
            return 0;
        }

        double sinX = sin(x);
        double cosX = cos(x);

        if (!isGreaterThan15Dig(cosX)) {
            throw new ArithmeticException("Tangent is undefined for this input (too close to π/2 or its multiples).");
        }

        return sinX / cosX;
    }

    private static double sin(double x) {
        double term = x;
        double sum = x;
        for (int i = 3; isGreaterThan15Dig(term); i += 2) {
            term = -term * x * x / (i * (i - 1));
            sum += term;
        }
        return sum;
    }

    private static double cos(double x) {
        double term = 1;
        double sum = 1;
        for (int i = 2; isGreaterThan15Dig(term); i += 2) {
            term = -term * x * x / (i * (i - 1));
            sum += term;
        }
        return sum;
    }

    private static double normalize(double x) {
//        double PI = 3.14159265358979323846;
//        return x - 2 * PI * Math.floor((x + PI) / (2 * PI));

        double PI = 3.14159265358979323846;
        int integerPart = (int) ((x + PI) / (2 * PI));
        double val;

        if (x == integerPart) {
            val = x;
        }
        if (x < 0) {
            val = integerPart - 1;
        }
        else {
            val = integerPart;
        }

        return x - 2 * PI * val;
    }

    private static boolean isGreaterThan15Dig(double value) {
        return value >= 0 ? value > 1e-15 : -value > 1e-15;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new D2Try2().setVisible(true);
            }
        });
    }
}
