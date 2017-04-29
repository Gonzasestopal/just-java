package com.gonzasestopal.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    int quantity = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String getName() {
        EditText nameTextView = (EditText) findViewById(R.id.name_edit_view);
        return nameTextView.getText().toString();
    }

    private boolean getToppings() {
        CheckBox toppings = (CheckBox) findViewById(R.id.cream_checkbox);
        return toppings.isChecked();
    }

    private boolean getChocolate() {
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        return chocolate.isChecked();
    }

    public void submitOrder(View view) {
        int price = calculatePrice(getToppings(), getChocolate());
        displayMessage(createOrderSummary(getName(), price, getToppings(), getChocolate()));
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:"));
//        intent.putExtra(Intent.EXTRA_EMAIL, "gonzasestopal@gmail.com");
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Que pex prro");
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView =  (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_LONG).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_LONG).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int toppings = 0;
        if (addWhippedCream) {
            toppings ++;
        }
        if (addChocolate    ) {
            toppings += 2;
        }
        Log.i("MainActivity", toppings + "");
        return quantity * (5 + toppings);
    }

    private String createOrderSummary(String name, int number, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + number;
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }
}
