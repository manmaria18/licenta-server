package com.imobiliare360.stripe_server;



import java.nio.file.Paths;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.port;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class StripeServer {
    private static Gson gson = new Gson();

    static class CreatePayment {
        @SerializedName("items")
        Object[] items;

        public Object[] getItems() {
            return items;
        }
    }

    static class CreatePaymentResponse {
        private String clientSecret;
        public CreatePaymentResponse(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }

    static int calculateOrderAmount(Object[] items) {
        // Replace this constant with a calculation of the order's amount
        // Calculate the order total on the server to prevent
        // people from directly manipulating the amount on the client
        return 1400;
    }

    public static void main(String[] args) {
        port(4242);
        staticFiles.externalLocation(Paths.get("public").toAbsolutePath().toString());

        // This is your test secret API key.
        Stripe.apiKey = "sk_test_51NBG3dJTQI7e9TUWZHXyPnd4nJHxUeT2Hr10j1hAm9TfjWzRLBdbDP3miQBvsNzGt7PfKLTwA39jXiO1uhqCm1Tp00F9VIXi4k";

        post("/create-payment-intent", (request, response) -> {
            response.type("application/json");

            CreatePayment postBody = gson.fromJson(request.body(), CreatePayment.class);
            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount(new Long(calculateOrderAmount(postBody.getItems())))
                            .setCurrency("ron")
                            .setAutomaticPaymentMethods(
                                    PaymentIntentCreateParams.AutomaticPaymentMethods
                                            .builder()
                                            .setEnabled(true)
                                            .build()
                            )
                            .build();

            // Create a PaymentIntent with the order amount and currency
            PaymentIntent paymentIntent = PaymentIntent.create(params);

            CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret());
            return gson.toJson(paymentResponse);
        });
    }
}
