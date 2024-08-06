package com.example.d3.tests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TanFunctionTest {
  @Test
  // Helper method to compare floating-point numbers with tolerance
  private static void assertApproximatelyEquals(double expected, double actual, double tolerance) {
    assertTrue(Math.abs(expected - actual) < tolerance,
        "Expected: " + expected + ", but got: " + actual);
  }

  @Test
  void testTanZero() {
    assertApproximatelyEquals(0.0, TanFunction.tan(0), 1e-15);
  }

  @Test
  void testTanPiOver4() {
    assertApproximatelyEquals(1.0, TanFunction.tan(Math.PI / 4), 1e-15);
  }

  @Test
  void testTanPi() {
    assertApproximatelyEquals(0.0, TanFunction.tan(Math.PI), 1e-15);
  }

  @Test
  void testTanPiOver2() {
    assertThrows(IllegalArgumentException.class, () -> TanFunction.tan(Math.PI / 2));
  }

  @Test
  void testTanNegativePiOver2() {
    assertThrows(IllegalArgumentException.class, () -> TanFunction.tan(-Math.PI / 2));
  }

  @Test
  void testTanSmallAngle() {
    double angle = 1e-10; // Small angle approximation
    assertApproximatelyEquals(angle, TanFunction.tan(angle), 1e-15);
  }
}