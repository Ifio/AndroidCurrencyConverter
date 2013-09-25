package ingsoftware.currencyConverter;

import ingsoftware.curconver.R;

import java.text.NumberFormat;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {

	// WebService configuration variables
	private final String NAMESPACE = "http://www.webserviceX.NET/";
	private final String URL = "http://www.webserviceX.NET/CurrencyConvertor.asmx";
	private final String METHOD_NAME = "ConversionRate";
	private final String SOAP_ACTION = "http://www.webserviceX.NET/ConversionRate";

	// GUI Variables
	Button bttConvert;
	Button bttFrom;
	Button bttTo;
	EditText txtFrom;
	EditText txtTo;

	// From and To Currency
	String fromCurrency;
	String toCurrency;
	ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Initialize GUI Variables
		bttConvert = (Button) findViewById(R.id.bttConvert);
		bttFrom = (Button) findViewById(R.id.bttFrom);
		bttTo = (Button) findViewById(R.id.bttTo);
		txtFrom = (EditText) findViewById(R.id.editText1);
		txtTo = (EditText) findViewById(R.id.editText2);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (data.getExtras().containsKey("CURRENCY_FROM")) {
			String fromCurrency = data.getStringExtra("CURRENCY_FROM");
			bttFrom.setText(fromCurrency.substring(0, 3));

		} else if (data.getExtras().containsKey("CURRENCY_TO")) {
			String toCurrency = data.getStringExtra("CURRENCY_TO");
			bttTo.setText(toCurrency.substring(0, 3));
		}
	}

	public void currencyFrom(View v) {

		Intent intent = new Intent(this, CurrencyList.class);
		intent.putExtra("currency", "from");
		startActivityForResult(intent, 1);

	}

	public void currencyTo(View v) {
		Intent intent = new Intent(this, CurrencyList.class);
		intent.putExtra("currency", "to");
		startActivityForResult(intent, 1);

	}

	public void convert(View view) {
		// Check if text is not empty
		if (txtFrom.getText().toString() != ""
				&& txtFrom.getText().length() != 0) {
			txtTo.setVisibility(View.VISIBLE);
			AsyncCallWS task = new AsyncCallWS();
			task.execute();
		} else {
			showMessage("Warning", "Please enter some value");
			txtTo.setText(null);
		}
	}

	// Show simple messages to the user
	public void showMessage(String title, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.show();
	}

	public String getConvertion() {
		// Initialize from and to currency
		fromCurrency = bttFrom.getText().toString();
		toCurrency = bttTo.getText().toString();
		double amount = Double.parseDouble(txtFrom.getText().toString());

		// Create request
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		// FromCurrency
		PropertyInfo propInfo = new PropertyInfo();
		propInfo.name = "FromCurrency";
		propInfo.type = PropertyInfo.STRING_CLASS;
		propInfo.setValue(fromCurrency);

		// ToCurrency
		PropertyInfo propInfo2 = new PropertyInfo();
		propInfo2.name = "ToCurrency";
		propInfo2.type = PropertyInfo.STRING_CLASS;
		propInfo2.setValue(toCurrency);

		// Add the properties to request object
		request.addProperty(propInfo);
		request.addProperty(propInfo2);

		// Create envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		// Set output SOAP object
		envelope.setOutputSoapObject(request);
		// Create HTTP call object
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			// Involve web service
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			double rate = Double.parseDouble(response.toString());
			double result = amount * rate;
			NumberFormat nm = NumberFormat.getNumberInstance();
			return nm.format(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	private class AsyncCallWS extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... params) {
			return getConvertion();
		}

		@Override
		protected void onPostExecute(String result) {
			pd.dismiss();
			if (!result.equals("error")) {
				txtTo.setText(result);
			} else {
				showMessage("Error",
						"An error occurred establishing connection to an external service");
			}

		}

		@Override
		protected void onPreExecute() {

			pd = new ProgressDialog(Main.this);
			pd.setTitle("Calculating");
			pd.setMessage("Please Wait");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();

		}
	}

}
