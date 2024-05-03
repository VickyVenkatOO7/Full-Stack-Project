package com.tri.service;

import com.stripe.exception.StripeException;
import com.tri.model.Order;
import com.tri.response.PaymentResponse;

public interface PaymentService {
	
	public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
