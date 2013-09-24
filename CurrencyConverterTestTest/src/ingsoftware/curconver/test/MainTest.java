package ingsoftware.curconver.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ingsoftware.currencyConverter.Main;
import ingsoftware.curconver.R;


public class MainTest extends ActivityInstrumentationTestCase2<Main> {

	// GUI variables for test
	public Activity activity;
	public Button btnConvert;
	public Button btnFrom;
	public Button btnTo;
	public TextView tvFrom;
	public TextView tvTo;
	public EditText txtFrom;
	public EditText txtTo;

	public MainTest() {
		super(Main.class);
	}

	public void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(true);

		// components initialization
		activity = getActivity();
		btnConvert = (Button) activity.findViewById(R.id.bttConvert);
		btnFrom = (Button) activity.findViewById(R.id.bttFrom);
		btnTo = (Button) activity.findViewById(R.id.bttTo);
		tvFrom = (TextView) activity.findViewById(R.id.tvFrom);
		tvTo = (TextView) activity.findViewById(R.id.tvTo);
		txtFrom = (EditText) activity.findViewById(R.id.editText1);
		txtTo = (EditText) activity.findViewById(R.id.editText2);

	}

	// to test for non-nullified component
	public void testPreconditions() {
		assertNotNull(activity);
		assertNotNull(btnConvert);
		assertNotNull(btnFrom);
		assertNotNull(btnTo);
		assertNotNull(tvFrom);
		assertNotNull(tvTo);
		assertNotNull(txtFrom);
		assertNotNull(txtTo);
	}

	// --------------To test for non-hard-coded strings -------------------

	public void testStringBttConvert() {
		final String txt = activity.getString(R.string.bttConvert);
		assertEquals(txt, btnConvert.getText().toString());
	}

	public void testStringLabelFrom() {
		final String txtF = activity.getString(R.string.titleFrom);
		assertEquals(txtF, tvFrom.getText().toString());
	}

	public void testStringLabelTo() {
		final String txtTo = activity.getString(R.string.titleTo);
		assertEquals(txtTo, tvTo.getText().toString());
	}

	public void testStringBttFrom() {
		final String bttF = activity.getString(R.string.bttDefaultFrom);
		assertEquals(bttF, btnFrom.getText().toString());
	}

	public void testStringBttTo() {
		final String txtTo = activity.getString(R.string.bttDefaultTo);
		assertEquals(txtTo, btnTo.getText().toString());
	}

	// --------------End test for non-hard-coded strings -------------------

	// ---------------------------

	public void testEmptyTxtTo() {
		TouchUtils.clickView(this, btnConvert);
		String actualText = txtTo.getText().toString();
		assertEquals("", actualText);
	}

	public void testBtnConvert() {
		TouchUtils.clickView(this, btnConvert);
		String txtF = txtTo.getText().toString();
		assert(!txtF.equals(null));
	}

	public void testBtnFrom() {
		TouchUtils.clickView(this, btnFrom);
		
	}

	public void testBtnTo() {
		//TouchUtils.clickView(this, btnTo);
	}
	// ---------------------------
}
