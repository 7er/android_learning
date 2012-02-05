package com.tdd.example.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import static android.test.ViewAsserts.assertOnScreen;
import static android.test.ViewAsserts.assertLeftAligned;

import com.tdd.example.EditNumber;
import com.tdd.example.TemperatureConverter;
import com.tdd.example.TemperatureConverterActivity;

public class TemperatureConverterActivityTest extends
		ActivityInstrumentationTestCase2<TemperatureConverterActivity> {

	private TemperatureConverterActivity mActivity;
	private EditNumber mCelsius;
	private EditNumber mFahrenheit;
	private TextView mCelsiusLabel;
	private TextView mFahrenheitLabel;

	public TemperatureConverterActivityTest() {
		this("TemperatureConverterActivityTest");
	}

	public TemperatureConverterActivityTest(String name) {
		super("com.tdd.example", TemperatureConverterActivity.class);
		setName(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mActivity = getActivity();
		mCelsius = (EditNumber) mActivity
				.findViewById(com.tdd.example.R.id.celsius);
		mFahrenheit = (EditNumber) mActivity
				.findViewById(com.tdd.example.R.id.fahrenheit);
		mCelsiusLabel = (TextView) mActivity
				.findViewById(com.tdd.example.R.id.celsius_label);
		mFahrenheitLabel = (TextView) mActivity
				.findViewById(com.tdd.example.R.id.fahrenheit_label);

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPreconditions() {
		assertNotNull(mActivity);
	}

	public void testHasInputFields() {
		assertNotNull(mCelsius);
		assertNotNull(mFahrenheit);
	}

	public void testThatFieldsAreInitiallyEmpty() {
		assertEquals("", mCelsius.getText().toString());
		assertEquals("", mFahrenheit.getText().toString());
	}

	public final void testFieldsOnScreen() {
		final View origin = mActivity.getWindow().getDecorView();
		assertOnScreen(origin, mCelsius);
		assertOnScreen(origin, mFahrenheit);
	}

	public final void testAlignment() {
		assertLeftAligned(mCelsiusLabel, mCelsius);
		assertLeftAligned(mFahrenheitLabel, mFahrenheit);
	}

	public final void testCelsiusInputFieldCoverEntireScreen() {
		final int expected = LayoutParams.MATCH_PARENT;
		final LayoutParams lp = mCelsius.getLayoutParams();
		assertEquals("mCelsius layout width is not MATCH_PARENT", expected,
				lp.width);
	}

	public final void testFahrenheitInputFieldCoverEntireScreen() {
		final int expected = LayoutParams.MATCH_PARENT;
		final LayoutParams lp = mFahrenheit.getLayoutParams();
		assertEquals("mFahrenheit layout width is not MATCH_PARENT", expected,
				lp.width);
	}

	public final void testFontSizes() {
		final float expected = 24.0f;
		assertEquals(expected, mCelsiusLabel.getTextSize());
		assertEquals(expected, mFahrenheitLabel.getTextSize());
	}

	@UiThreadTest
	public final void testFahrenheitToCelsiusConversion() {
		mCelsius.clear();
		mFahrenheit.clear();
		final double f = 32.5;
		mFahrenheit.requestFocus();
		mFahrenheit.setNumber(f);
		mCelsius.requestFocus();
		final double expectedC = TemperatureConverter.fahrenheitToCelsius(f);
		final double actualC = mCelsius.getNumber();
		final double delta = Math.abs(expectedC - actualC);
		final String msg = "" + f + "F -> " + expectedC + "C but was "
				+ actualC + "C (delta " + delta + ")";
		assertTrue(msg, delta < 0.005);
	}

}
