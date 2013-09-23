package ingsoftware.currencyConverter;

import ingsoftware.curconver.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
		Resources res = getResources();
		final String[] values = res.getStringArray(R.array.currenciesArray);

		

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