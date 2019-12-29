package com.christmasapp.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private static int numberOfCoffees = 2;
    private static boolean hasWhippedCream = false;
    private static boolean hasChocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText inputLayout = findViewById(R.id.name_input);
        String name = inputLayout.getText().toString();

        int price = calculatePrice();

        String orderSummary = createOrderSummary(name, price);
        String emailSubject = "JustJava order for " + name;

        // Create an Intent object type ACTION_SENDTO
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/html");
        // mailto: specify that only email apps should handle this intent
        intent.setData(Uri.parse("mailto:"));
        // Email subject
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        // Email body
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        // Check if there is an activity on device to handle the intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice() {
        int basePrice = 5;
        // Add $1 if the user wants whipped cream
        if (hasWhippedCream) {
            basePrice += 1;
        }
        // Add $2 if the user wants chocolate
        if (hasChocolate) {
            basePrice += 2;
        }
        // Calculate the total price per order
        return numberOfCoffees * basePrice;
    }

    /**
     * Create order summary message based on the price.
     *
     * @param price of the order
     * @param name
     * @return text summary
     */
    private String createOrderSummary(String name, int price) {
        return getString(R.string.order_summary_name, name) +
                "\n" + getString(R.string.order_summary_whipped_cream,  hasWhippedCream) +
                "\n" + getString(R.string.order_summary_chocolate,  hasChocolate) +
                "\n" + getString(R.string.order_summary_quantity,numberOfCoffees) +
                "\n" + getString(R.string.order_summary_price, price) +
                "\n" + getString(R.string.thank_you);
    }

    /**
     * This method is called when the plus button is clicked.
     *
     * @param view
     */
    public void incrementQuantity(View view) {
        if (numberOfCoffees == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            // Exit
            return;
        }

        numberOfCoffees++;
        displayQuantity(numberOfCoffees);
    }

    /**
     * This method is called when the minus button is clicked.
     *
     * @param view
     */
    public void decrementQuantity(View view) {
        if (numberOfCoffees == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit
            return;
        }

        numberOfCoffees--;
        displayQuantity(numberOfCoffees);

    }

    /**
     * Displays the given quantity value on the screen.
     *
     * @param number
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Add Whipped Cream
     *
     * @param view
     */
    public void addWhippedCream(View view) {
        hasWhippedCream = ((CheckBox) view).isChecked();
    }

    /**
     * Add Chocolate
     *
     * @param view
     */

    public void addChocolate(View view) {
        hasChocolate = ((CheckBox) view).isChecked();
    }
}