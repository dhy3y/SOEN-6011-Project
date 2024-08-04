package com.example.d2;

public class TanFunction {
  /**
   * Calculates the tangent of the given angle in radians.
   *
   * @param x The angle in radians
   * @return The tangent of the angle x
   */
  public static double tan(double x) {
    x = normalize(x);

    if (x == 0) {
      return 0;
    }

    double sinX = sin(x);
    double cosX = cos(x);

    if (!isGreaterThan15Dig(cosX)) {
      throw new IllegalArgumentException("Tangent(x) is undefined for this input (too close to π/2 or its odd multiples).");
    }

    return sinX / cosX;
  }

  /**
   * Computes the sine of the given angle in radians using a Maclaurin series expansion.
   *
   * @param x The angle in radians
   * @return The sine of the angle x
   */
  private static double sin(double x) {
    double term = x;
    double sum = x;
    for (int i = 3; isGreaterThan15Dig(term); i += 2) {
      term = -term * x * x / (i * (i - 1));
      sum += term;
    }
    return sum;
  }

  /**
   * Computes the cosine of the given angle in radians using a Maclaurin series expansion.
   *
   * @param x The angle in radians
   * @return The cosine of the angle x
   */
  private static double cos(double x) {
    double term = 1;
    double sum = 1;
    for (int i = 2; isGreaterThan15Dig(term); i += 2) {
      term = -term * x * x / (i * (i - 1));
      sum += term;
    }
    return sum;
  }

  /**
   * Normalizes the angle x to be within the range of -π to +π radians.
   */
  private static double normalize(double x) {
    double PI = 3.14159265358979323846;
    int integerPart = (int) ((x + PI) / (2 * PI));
    double val;

    if (x == integerPart) {
      val = x;
    }
    if (x < 0) {
      val = integerPart - 1;
    } else {
      val = integerPart;
    }

    return x - 2 * PI * val;
  }

  /**
   * Checks if the given value is greater than 1e-15 in magnitude.
   */
  private static boolean isGreaterThan15Dig(double value) {
    return value >= 0 ? value > 1e-15 : -value > 1e-15;
  }
}
