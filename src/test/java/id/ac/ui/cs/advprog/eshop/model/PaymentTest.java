package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest{
    private  Map<String, String> subFeature;

    @BeforeEach
    void setUp(){
        this.subFeature = new HashMap<>();
        subFeature.put("voucherCode", "ESHOPABC12345678");
    }

    @Test
    void testAddPaymentVoucherDefaultStatus() {
        Payment payment = new Payment("123456789","voucher", subFeature);

        assertSame(this.payment, payment.getPayment());
        assertTrue(payment.getPaymentData().containsKey("VoucherCode"));

        assertEquals("123456789", payment.getId());
        assertEquals("voucher", payment.getMethod());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("VoucherCode"));
        assertEquals(PaymentStatus.CHECK_PAYMENT.getValue(), payment.getStatus());
    }

    @Test
    void testAddPaymentCODDefaultStatus() {
        Map<String, String> paymentCOD = new HashMap<>();
        paymentCOD.put("deliveryFee", "10");
        paymentCOD.put("address", "Bintaro");
        Payment payment = new Payment("2", "COD", paymentCOD);
        assertTrue(payment.getPaymentCOD().containsKey("deliveryFee"));
        assertTrue(payment.getPaymentCOD().containsKey("address"));
        assertEquals("CHECK_PAYMENT", payment.getStatus());
        assertEquals("2", payment.getId());
        assertEquals("COD", payment.getMethod());
        assertEquals("Bintaro", payment.getPaymentData().get("address"));
        assertEquals("10", payment.getPaymentData().get("deliveryFee"));
    }

    @Test
    void testAddPaymentSuccessStatus(){
        Payment payment = new Payment("123456789","voucher", subFeature);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testAddPaymentInvalidStatus(){
        assertThrows (IllegalArgumentException.class, () -> {
            Payment payment = new Payment("123456789", "voucher", "meow" , subFeature);
        });
    }

    @Test
    void testSetStatusToReject(){
        Payment payment = new Payment("123456789","voucher", subFeature);
        payment.setStatus("REJECTED");
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("123456789","voucher", subFeature);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("INVALID_STATUS");
        });
    }
}