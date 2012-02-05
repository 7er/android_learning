package com.tdd.example.test;

import java.util.HashMap;

import com.tdd.example.TemperatureConverter;

import junit.framework.TestCase;

public class TemperatureConverterTest extends TestCase {

	public TemperatureConverterTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	private static final HashMap<Double, Double> conversionTableDouble = new HashMap<Double, Double>();
	static {
		// initialize (c, f) pairs
		conversionTableDouble.put(0.0, 32.0);
		conversionTableDouble.put(100.0, 212.0);
		conversionTableDouble.put(-1.0, 30.20);
		conversionTableDouble.put(-100.0, -148.0);
		conversionTableDouble.put(32.0, 89.60);
		conversionTableDouble.put(-40.0, -40.0);
		conversionTableDouble.put(-273.0, -459.40);
	}

	public final void testFahrenheitToCelsius() {
		for (double c : conversionTableDouble.keySet()) {
			final double f = conversionTableDouble.get(c);
			final double ca = TemperatureConverter.fahrenheitToCelsius(f);
			final double delta = Math.abs(ca - c);
			final String msg = "" + f + "F -> " + c + "C but is " + ca
					+ " (delta " + delta + ")";
			assertTrue(msg, delta < 0.0001);
		}
	}

}
