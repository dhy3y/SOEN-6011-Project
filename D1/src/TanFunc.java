import java.util.Scanner;

public class TanFunc {

    /**
     * Compute Tangent
     * @param x
     * @return result of tangent of x
     */
    public static double computeTan(double x) {
        double result = 0;
        if (isUndefined(x)) {
            System.out.println("Error: The tangent of " + x + " is undefined.");
            return result;
        } else {
            result = Math.tan(x);
            System.out.println("The tangent of " + x + " is: " + result);
        }
        return result;
    }

    /**
     * Is Undefined
     * @param x
     * @return return if the value is undefined for calculating tangent
     */
    private static boolean isUndefined(double x) {
        //checks when function reaches odd multiples of pi/2
        final double PI = Math.PI;
        double k = (x - PI / 2) / PI;
        return Math.abs(k - Math.round(k)) < 1e-10;
    }

    /**
     * Main Function
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tangent Examples \n");
        System.out.println("Small values");
        computeTan(0.0);
        computeTan(0.5);

        System.out.println("\nMedium values");
        computeTan(1.0);
        computeTan(1.25);

        System.out.println("\nVery Large positive value");
        computeTan(1.5707); //Closer to pi/2

        System.out.println("\nEdge cases:");
        computeTan(Math.PI/2);
        computeTan(3*Math.PI/2);

        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("Welcome to the Tangent Function Calculator!");
        System.out.println("Please enter an angle in radians:");

        while (true) {
            try {
                double x = scanner.nextDouble();
                double result = computeTan(x);
                System.out.println("Enter another angle in radians or type 'exit' to quit:");
            }
            catch (Exception e) {
                String input = scanner.next();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Thank you!");
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a valid number or 'exit' to quit:");
                }
            }
        }
        scanner.close();
    }
}