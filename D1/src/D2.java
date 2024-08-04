public class D2 {
    public static double tan(double x) {
        // Normalize x to be within the range [-PI, PI]
//        x = normalize(x);

        // Handle special cases
        if (x == 0) {
            return 0;
        }

        double sinX = sin(x);
        double cosX = cos(x);

        // Handle undefined cases (when cos(x) is very close to 0)
        if (Math.abs(cosX) < 1e-15) {
            throw new ArithmeticException("Tangent is undefined for this input.");
        }

        return sinX / cosX;
    }

    private static double sin(double x) {
        double term = x;
        double sum = x;
        for (int i = 3; Math.abs(term) > 1e-15; i += 2) {
            term = -term * x * x / (i * (i - 1));
            sum += term;
        }
        return sum;
    }

    private static double cos(double x) {
        double term = 1;
        double sum = 1;
        for (int i = 2; Math.abs(term) > 1e-15; i += 2) {
            term = -term * x * x / (i * (i - 1));
            sum += term;
        }
        return sum;
    }

    private static double normalize(double x) {
        double PI = 3.14159265358979323846;
        return x - 2 * PI * Math.floor((x + PI) / (2 * PI));
    }

    public static void main(String[] args) {
        // Test the tan function
        double[] testValues = {0.0, 0.5, 1.0, 1.25, 1.5707, Math.PI/2, 3*Math.PI/2};
        for (double x : testValues) {
            try {
                System.out.println("tan("+ x +") = " + tan(x));
            } catch (ArithmeticException e) {
                System.out.println("tan(" + x + ") is undefined%n");
            }
        }
    }
}
