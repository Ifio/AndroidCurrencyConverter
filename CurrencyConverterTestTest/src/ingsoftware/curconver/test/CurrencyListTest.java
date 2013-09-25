package ingsoftware.curconver.test;

import ingsoftware.currencyConverter.CurrencyList;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import ingsoftware.curconver.R;

public class CurrencyListTest extends
		ActivityInstrumentationTestCase2<CurrencyList> {

	Activity act;
	EditText et;

	public CurrencyListTest() {
		super(CurrencyList.class);
	}

	public void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(true);

		// Components initialization
		act = getActivity();
		et = (EditText) act.findViewById(R.id.etSearch);
	}

	public void testStringSearch() {
		final String txtF = act.getString(R.string.find_hint);
		assertEquals(txtF, et.getText().toString());
	}

}
