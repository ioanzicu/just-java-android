package com.christmasapp.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private static int numberOfCoffees = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String orderSummary = createOrderSummary(price);
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
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(int price) {
        return "Name: Ioan\n" +
                "Quantity: " + numberOfCoffees +
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
     */
    public void decrementQuantity(View view) {
        if (numberOfCoffees > 1) {
            numberOfCoffees--;
            displayQuantity(numberOfCoffees);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}