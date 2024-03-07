package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentRepositoryTest {
    @InjectMocks
    PaymentRepository paymentRepository;
    List<Payment> payments;
    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("1", "voucher",paymentData);
        payments.add(payment);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("deliveryFee", "10");
        paymentData2.put("address", "Bintaro");
        Payment payment2 = new Payment("2", "COD", paymentData2);
        payments.add(payment2);
    }
    @Test
    void testSaveCreate(){
        Payment payment = payments.getFirst();
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.getPayment(payments.getFirst().getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), findResult.getStatus());
        assertEquals(16, findResult.getPaymentData().get("VoucherCode").length());
        assertEquals(payment.getPaymentData().get("VoucherCode"), findResult.getPaymentData().get("VoucherCode"));
    }
    @Test
    void testPaymentVoucherInvalid(){
        Map<String, String> invalidPayment = new HashMap<>();
        invalidPayment.put("VoucherCode", "INVALID");
        Payment tempPayment = new Payment("3", "voucher", invalidPayment);
        paymentRepository.save(tempPayment);

        Payment result = paymentRepository.getPayment(tempPayment.getId());
        assertEquals(tempPayment.getId(), result.getId());
        assertEquals(tempPayment.getMethod(), result.getMethod());
        assertEquals(tempPayment.getPaymentData().get("voucherId"), result.getPaymentData().get("voucherId") );
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
    }
    @Test
    void testPaymentCodAddressIsEmpty(){
        Map<String, String> invalidPayment = new HashMap<>();
        invalidPayment.put("deliveryFee", "10");
        invalidPayment.put("address", "");
        Payment tempPayment = new Payment("4", "COD", invalidPayment);
        paymentRepository.save(tempPayment);

        Payment result = paymentRepository.getPayment(tempPayment.getId());
        assertEquals(tempPayment.getId(), result.getId());
        assertEquals(tempPayment.getMethod(), result.getMethod());
        assertEquals(tempPayment.getPaymentData().get("address"), result.getPaymentData().get("address") );
        assertEquals(tempPayment.getPaymentData().get("deliveryFee"), result.getPaymentData().get("deliveryFee") );
        assertTrue(result.getPaymentData().get("address").isEmpty());
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
    }
    @Test
    void testPaymentCodDeliveryFeeIsEmpty(){
        Map<String, String> invalidPayment = new HashMap<>();
        invalidPayment.put("deliveryFee", "");
        invalidPayment.put("address", "Bintaro");
        Payment tempPayment = new Payment("5", "COD", invalidPayment);
        paymentRepository.save(tempPayment);

        Payment result = paymentRepository.getPayment(tempPayment.getId());
        assertEquals(tempPayment.getId(), result.getId());
        assertEquals(tempPayment.getMethod(), result.getMethod());
        assertEquals(tempPayment.getPaymentData().get("address"), result.getPaymentData().get("address") );
        assertEquals(tempPayment.getPaymentData().get("deliveryFee"), result.getPaymentData().get("deliveryFee") );
        assertTrue(result.getPaymentData().get("deliveryFee").isEmpty());
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
    }
    @Test
    void testGetPayment() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.getPayment(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
    }
    @Test
    void testGetPaymentIsEmpty() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.getPayment("meow");
        assertNull(findResult);
    }
    @Test
    void testGetPaymentAll() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List <Payment> payments1 = paymentRepository.getPaymentAll();
        assertEquals(2, payments1.size());
    }

    @Test
    void testGetPaymentAllIsEmpty() {
        List <Payment> payments1 = paymentRepository.getPaymentAll();
        assertTrue(payments1.isEmpty());
    }
}