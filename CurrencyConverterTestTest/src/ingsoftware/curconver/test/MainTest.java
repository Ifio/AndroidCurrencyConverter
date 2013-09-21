package ingsoftware.curconver.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ingsoftware.currencyConverter.Main;
import ingsoftware.curconver.R;

public class MainTest extends ActivityInstrumentationTestCase2<Main> {

	// GUI variables for test
	public Activity activity;
	public Button bttConvert;
	public Button bttFrom;
	public Button bttTo;
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
		bttConvert = (Button) activity.findViewById(R.id.bttConvert);
		bttFrom = (Button) activity.findViewById(R.id.bttFrom);
		bttTo = (Button) activity.findViewById(R.id.bttTo);
		tvFrom = (TextView) activity.findViewById(R.id.tvFrom);
		tvTo = (TextView) activity.findViewById(R.id.tvTo);
		txtFrom = (EditText) activity.findViewById(R.id.editText1);
		txtFrom = (EditText) activity.findViewById(R.id.editText2);

	}

	// to test for non-nullified component
	public void testPreconditions() {
		assertNotNull(activity);
		assertNotNull(bttConvert);
		assertNotNull(bttTo);
		assertNotNull(tvFrom);
		assertNotNull(tvTo);
		assertNotNull(txtFrom);
		assertNotNull(txtTo);
	}

	// --------------To test for non-hard-coded strings -------------------

	public void testRefConvert() {
		final String txt = activity.getString(R.string.bttConvert);
		assertEquals(txt, bttConvert.getText().toString());

	}

	// --------------End test for non-hard-coded strings -------------------
	
}
