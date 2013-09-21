package ingsoftware.curconver.test;

import ingsoftware.currencyConverter.CurrencyList;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import ingsoftware.curconver.R;

public class CurrencyListTest extends
		ActivityInstrumentationTestCase2<CurrencyList> {

	Activity act;
	ListView lv;

	public CurrencyListTest() {
		super(CurrencyList.class);
	}

	public void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(true);

		// Components initialization
		act = getActivity();
		lv = (ListView) act.findViewById(R.id.listview);
	}

}
