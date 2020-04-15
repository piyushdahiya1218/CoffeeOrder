package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


/**

 This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public int numberOfCoffee = 2;

    public void increment(View view)
    {
        if (numberOfCoffee >= 100)
        {
            return;
        }
        else
        {
            numberOfCoffee = numberOfCoffee + 1;
            displayQuantity(numberOfCoffee);
        }

    }
    public void decrement(View view)
    {
        if (numberOfCoffee <= 1)
        {
            return;
        }
        else
        {
            numberOfCoffee = numberOfCoffee - 1;
            displayQuantity(numberOfCoffee);
        }

    }
    private int calculateprice(boolean haschocolate, boolean haswhippedcream)
    {
        int pricepercup = 5;
        if (haschocolate)
        {
            pricepercup = pricepercup + 2;
        }
        if (haswhippedcream)
        {
            pricepercup = pricepercup + 1;
        }

        int price = numberOfCoffee * pricepercup;
        return price;
    }
    /**
     This method is called when the order button is clicked.
     */
    public void SubmitOrder(View view) {
        EditText name = (EditText) findViewById(R.id.nameinput);
        String whatisyourname = name.getText().toString();

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolatecheckbox);
        Boolean haschocolate = chocolate.isChecked();

        CheckBox whippedcream = (CheckBox) findViewById(R.id.whippedcreamcheckbox);
        Boolean haswhippedcream = whippedcream.isChecked();

        int price = calculateprice(haschocolate, haswhippedcream);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "This is your order " + whatisyourname);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price, haswhippedcream, haschocolate, whatisyourname));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"divyansh.dahiya2105@gmail.com"});
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        //displayMessage(createOrderSummary(price, haswhippedcream, haschocolate, whatisyourname));
    }

    private String createOrderSummary(int price, boolean haswhippedcream, boolean haschoclate, String whatisyourname)
    {
        String priceMessage = "Name : " + whatisyourname + "\nChocolate : " + haschoclate + "\nWhipped Cream : " + haswhippedcream + "\nQuantity : " + numberOfCoffee + " \nTotal : $" + price + " \nThank You";
        return priceMessage;
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary__text_view);
//        orderSummaryTextView.setText(message);
//    }
}