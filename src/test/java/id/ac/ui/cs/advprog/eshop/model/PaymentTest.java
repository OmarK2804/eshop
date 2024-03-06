package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest{
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp(){
        this.paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOPABC12345678");
    }

    @Test
    void testAddPaymentVoucherDefaultStatus() {
        Payment payment = new Payment("123456789","voucher", paymentData);

        assertTrue(payment.getPaymentData().containsKey("voucherCode"));

        assertEquals("123456789", payment.getId());
        assertEquals("voucher", payment.getMethod());
        assertEquals(PaymentStatus.CHECK_PAYMENT.getValue(), payment.getStatus());
    }

    @Test
    void testAddPaymentCodDefaultStatus() {
        Map<String, String> paymentCOD = new HashMap<>();
        paymentCOD.put("deliveryFee", "10");
        paymentCOD.put("address", "Bintaro");
        Payment payment = new Payment("2", "COD", paymentCOD);
        assertTrue(payment.getPaymentData().containsKey("deliveryFee"));
        assertTrue(payment.getPaymentData().containsKey("address"));
        assertEquals("CHECK_PAYMENT", payment.getStatus());
        assertEquals("2", payment.getId());
        assertEquals("COD", payment.getMethod());
        assertEquals("Bintaro", payment.getPaymentData().get("address"));
        assertEquals("10", payment.getPaymentData().get("deliveryFee"));
    }

    @Test
    void testAddPaymentSuccessStatus(){
        Payment payment = new Payment("123456789","voucher", paymentData);
        payment.setStatus("SUCCESS");
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testAddPaymentInvalidStatus(){
        assertThrows (IllegalArgumentException.class, () -> {
            Payment payment = new Payment("123456789", "voucher", "meow" , paymentData);
        });
    }

    @Test
    void testSetStatusToReject(){
        Payment payment = new Payment("123456789","voucher", paymentData);
        payment.setStatus("REJECTED");
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("123456789","voucher", paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("INVALID_STATUS");
        });
    }
}