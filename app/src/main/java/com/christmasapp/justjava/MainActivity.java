package com.christmasapp.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
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
        displayMessage(orderSummary);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice() {
        return numberOfCoffees * 5;
    }

    /**
     * Create order summary message based on the price.
     *
     * @param price of the order
     * @param name
     * @return text summary
     */
    private String createOrderSummary(String name, int price) {
        return "Name: " + name +
                "\nAdd whipped cream? " + hasWhippedCream +
                "\nAdd chocolate? " + hasChocolate +
                "\nQuantity: " + numberOfCoffees +
                "\nTotal: $" + price +
                "\nThank you!";
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void incrementQuantity(View view) {
        numberOfCoffees++;
        displayQuantity(numberOfCoffees);
    }

    /**
     * This method is called when the minus button is clicked.
     *
     * @param view
     */
    public void decrementQuantity(View view) {
        if (numberOfCoffees > 1) {
            numberOfCoffees--;
            displayQuantity(numberOfCoffees);
        }
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
     * Displays the given text on the screen.
     *
     * @param message
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
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