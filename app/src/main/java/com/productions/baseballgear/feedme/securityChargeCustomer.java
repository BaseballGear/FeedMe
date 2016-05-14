package com.productions.baseballgear.feedme;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;


/**
 * Created by BaseballGear on 4/30/2016.
 */
public class securityChargeCustomer extends Activity
{
    Button button_paywithpaypal;
    EditText editText_friend_name;
    EditText editText_amount;
    private static final int REQUEST_CODE_PAYMENT = 1;


    // PayPal configuration
    /* private static PayPalConfiguration paypalConfig = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(
                    Util.paypal_sdk_id);*/
    private static PayPalConfiguration paypalConfig=new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId( paypalHelper.paypal_sdk_id)
            .acceptCreditCards(true)
                    // The following are only used in PayPalFuturePaymentActivity.
            .merchantName("Code_Crash")
            .merchantPrivacyPolicyUri(
                    Uri.parse("https://www.paypal.com/webapps/mpp/ua/privacy-full"))
            .merchantUserAgreementUri(
                    Uri.parse("https://www.paypal.com/webapps/mpp/ua/useragreement-full"));


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sec_charge_customer);

        // Starting PayPal service
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);
        startService(intent);

        editText_friend_name= (EditText) findViewById(R.id.venderEmail);
        editText_amount= (EditText) findViewById(R.id.amountToPay);

        button_paywithpaypal= (Button) findViewById(R.id.makePaymentBtn);

        button_paywithpaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //call pay pal sdk method
                launchPayPalPayment();
            }
        });
    }

    private void launchPayPalPayment()
    {

        PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal(editText_amount.getText().toString()),"USD", editText_friend_name.getText().toString(),
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(securityChargeCustomer.this, PaymentActivity.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig);

        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

        startActivityForResult(intent, REQUEST_CODE_PAYMENT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PAYMENT)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                findRestaurants();
            }
            else if (resultCode == Activity.RESULT_CANCELED)
            {

                Toast.makeText(getApplicationContext(), "Payment Canceled , Try again ", Toast.LENGTH_LONG).show();
                //Make an alert pop up

            }
            else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            {

                Toast.makeText(getApplicationContext(), "Payment failed , Try again ", Toast.LENGTH_LONG).show();

            }
        }
    }

    private void findRestaurants()
    {
        AlertDialog.Builder emailAlert = new AlertDialog.Builder(this);
        emailAlert.setMessage("Ready to begin?")
                .setPositiveButton("Yes, show restaurants", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                        Intent i = new Intent (securityChargeCustomer.this, findResturants.class);
                        startActivity(i);
                    }
                })
                .setTitle("Security Charge Successful")
                .create();
        emailAlert.show();
    }


}
