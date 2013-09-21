package ingsoftware.currencyConverter;

import ingsoftware.curconver.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class CurrencyList extends Activity {
	private ArrayList<String> array_sort = new ArrayList<String>();
	int textlength = 0;

	// GUI Variables
	private EditText etSearch;
	private ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		listview = (ListView) findViewById(R.id.listview);
		etSearch = (EditText) findViewById(R.id.etSearch);

		final String[] values = new String[] { "ALL - Albanian Lek",
				"DZD - Algerian Dinar", "ARS - Argentine Peso",
				"AWG - Aruba Florin", "AUD - Australian Dollar",
				"BSD - Bahamian Dollar", "BHD - Bahraini Dinar",
				"BDT - Bangladesh Taka", "BBD - Barbados Dollar",
				"BZD - Belize Dollar", "BMD - Bermuda Dollar",
				"BTN - Bhutan Ngultrum", "BOB - Bolivian Boliviano",
				"BWP - Botswana Pula", "BRL - Brazilian Real",
				"GBP - British Pound", "BND - Brunei Dollar",
				"BIF - Burundi Franc", "XOF - CFA Franc (BCEAO)",
				"XAF - CFA Franc (BEAC)", "KHR - Cambodia Riel",
				"CAD - Canadian Dollar", "CVE - Cape Verde Escudo",
				"KYD - Cayman Islands Dollar", "CLP - Chilean Peso",
				"CNY - Chinese Yuan", "COP - Colombian Peso",
				"KMF - Comoros Franc", "CRC - Costa Rica Colon",
				"HRK - Croatian Kuna", "CUP - Cuban Peso",
				"CYP - Cyprus Pound", "CZK - Czech Koruna",
				"DKK - Danish Krone", "DJF - Dijibouti Franc",
				"DOP - Dominican Peso", "XCD - East Caribbean Dollar",
				"EGP - Egyptian Pound", "SVC - El Salvador Colon",
				"EEK - Estonian Kroon", "ETB - Ethiopian Birr", "EUR - Euro",
				"FKP - Falkland Islands Pound", "GMD - Gambian Dalasi",
				"GHC - Ghanian Cedi", "GIP - Gibraltar Pound",
				"XAU - Gold Ounces", "GTQ - Guatemala Quetzal",
				"GNF - Guinea Franc", "GYD - Guyana Dollar",
				"HTG - Haiti Gourde", "HNL - Honduras Lempira",
				"HKD - Hong Kong Dollar", "HUF - Hungarian Forint",
				"ISK - Iceland Krona", "INR - Indian Rupee",
				"IDR - Indonesian Rupiah", "IQD - Iraqi Dinar",
				"ILS - Israeli Shekel", "JMD - Jamaican Dollar",
				"JPY - Japanese Yen", "JOD - Jordanian Dinar",
				"KZT - Kazakhstan Tenge", "KES - Kenyan Shilling",
				"KRW - Korean Won", "KWD - Kuwaiti Dinar", "LAK - Lao Kip",
				"LVL - Latvian Lat", "LBP - Lebanese Pound",
				"LSL - Lesotho Loti", "LRD - Liberian Dollar",
				"LYD - Libyan Dinar", "LTL - Lithuanian Lita",
				"MOP - Macau Pataca", "MKD - Macedonian Denar",
				"MGF - Malagasy Franc", "MWK - Malawi Kwacha",
				"MYR - Malaysian Ringgit", "MVR - Maldives Rufiyaa",
				"MTL - Maltese Lira", "MRO - Mauritania Ougulya",
				"MUR - Mauritius Rupee", "MXN - Mexican Peso",
				"MDL - Moldovan Leu", "MNT - Mongolian Tugrik",
				"MAD - Moroccan Dirham", "MZM - Mozambique Metical",
				"MMK - Myanmar Kyat", "NAD - Namibian Dollar",
				"NPR - Nepalese Rupee", "ANG - Neth Antilles Guilder",
				"NZD - New Zealand Dollar", "NIO - Nicaragua Cordoba",
				"NGN - Nigerian Naira", "KPW - North Korean Won",
				"NOK - Norwegian Krone", "OMR - Omani Rial",
				"XPF - Pacific Franc", "PKR - Pakistani Rupee",
				"XPD - Palladium Ounces", "PAB - Panama Balboa",
				"PGK - Papua New Guinea Kina", "PYG - Paraguayan Guarani",
				"PEN - Peruvian Nuevo Sol", "PHP - Philippine Peso",
				"XPT - Platinum Ounces", "PLN - Polish Zloty",
				"QAR - Qatar Rial", "ROL - Romanian Leu",
				"RUB - Russian Rouble", "WST - Samoa Tala",
				"STD - Sao Tome Dobra", "SAR - Saudi Arabian Riyal",
				"SCR - Seychelles Rupee", "SLL - Sierra Leone Leone",
				"XAG - Silver Ounces", "SGD - Singapore Dollar",
				"SKK - Slovak Koruna", "SIT - Slovenian Tolar",
				"SBD - Solomon Islands Dollar", "SOS - Somali Shilling",
				"ZAR - South African Rand", "LKR - Sri Lanka Rupee",
				"SHP - St Helena Pound", "SDD - Sudanese Dinar",
				"SRG - Surinam Guilder", "SZL - Swaziland Lilageni",
				"SEK - Swedish Krona", "TRY - Turkey Lira",
				"CHF - Swiss Franc", "SYP - Syrian Pound",
				"TWD - Taiwan Dollar", "TZS - Tanzanian Shilling",
				"THB - Thai Baht", "TOP - Tonga Pa'anga",
				"TTD - Trinidad & Tobago Dollar", "TND - Tunisian Dinar",
				"TRL - Turkish Lira", "USD - U.S. Dollar", "AED - UAE Dirham",
				"UGX - Ugandan Shilling", "UAH - Ukraine Hryvnia",
				"UYU - Uruguayan New Peso", "VUV - Vanuatu Vatu",
				"VEB - Venezuelan Bolivar", "VND - Vietnam Dong",
				"YER - Yemen Riyal", "YUM - Yugoslav Dinar",
				"ZMK - Zambian Kwacha", "ZWD - Zimbabwe Dollar" };

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}
		final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);

		// Search currencies
		etSearch.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// Abstract Method of TextWatcher Interface.
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Abstract Method of TextWatcher Interface.
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				textlength = etSearch.getText().length();
				array_sort.clear();
				for (int i = 0; i < values.length; i++) {
					if (textlength <= values[i].length()) {
						String searchText = (String) etSearch.getText()
								.toString();
						if (((String) values[i].toLowerCase())
								.contains(searchText.toLowerCase())) {
							array_sort.add(values[i]);
						}
					}
				}
				listview.setAdapter(new ArrayAdapter<String>(CurrencyList.this,
						android.R.layout.simple_list_item_1, array_sort));
			}
		});

		// List functionalities
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				String item = (String) parent.getItemAtPosition(position);
				// Toast.makeText(getApplicationContext(), item+" "+position,
				// Toast.LENGTH_SHORT).show();

				Intent intent = getIntent();
				String currency = intent.getStringExtra("currency");
				if (currency.equals("from")) {
					intent.putExtra("CURRENCY_FROM", item);

				} else {
					intent.putExtra("CURRENCY_TO", item);

				}

				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = getIntent();
			setResult(RESULT_OK, intent);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}

}