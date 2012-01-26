package com.tdd.example.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.tdd.example.TemperatureConverterActivity;

public class TemperatureConverterTest extends
		ActivityInstrumentationTestCase2<TemperatureConverterActivity> {

	private TemperatureConverterActivity mActivity;
	private EditText mCelsius;
	private EditText mFahrenheit;
	public TemperatureConverterTest() {
		this("TemperatureConverterActivityTest");
	}

	public TemperatureConverterTest(String name) {
		super("com.tdd.example", TemperatureConverterActivity.class);
		setName(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mActivity = getActivity();
		mCelsius = (EditText)mActivity.findViewById(com.tdd.example.R.id.celsius);
		mFahrenheit = (EditText)mActivity.findViewById(com.tdd.example.R.id.fahrenheit);
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

}
