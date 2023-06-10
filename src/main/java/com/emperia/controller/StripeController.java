package com.emperia.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StripeController {

    // This is your test secret API key.
    private static final String STRIPE_SECRET_KEY = "sk_test_51NBG3dJTQI7e9TUWZHXyPnd4nJHxUeT2Hr10j1hAm9TfjWzRLBdbDP3miQBvsNzGt7PfKLTwA39jXiO1uhqCm1Tp00F9VIXi4k";


    static class CreatePayment {
        //@SerializedName("items")
        Object[] items;

        public Object[] getItems() {
            return items;
        }
    }

//    static class CreatePaymentResponse {
//        private String clientSecret;
//        public CreatePaymentResponse(String clientSecret) {
//            this.clientSecret = clientSecret;
//        }
//    }


    @PostMapping("/create-payment-intent")
    public PaymentIntent createPaymentIntent(@RequestBody CreatePayment createPayment) throws StripeException {
        Stripe.apiKey = STRIPE_SECRET_KEY;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(calculateOrderAmount(createPayment.getItems()))
                .setCurrency("ron")
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(true).build()
                )
                .build();

        // Create a PaymentIntent with the order amount and currency
        return PaymentIntent.create(params);
    }

    // Helper method to calculate order amount
    static Long calculateOrderAmount(Object[] items) {
        // Replace this constant with a calculation of the order's amount
        // Calculate the order total on the server to prevent
        // people from directly manipulating the amount on the client
        return 1400L;
    }
}



