package com.example.ecommerce.controller;

import com.example.ecommerce.service.PaymentService;
import com.iyzipay.model.CheckoutFormInitialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Ödeme formunu başlatan endpoint.
     * Örneğin, sepetteki toplam fiyat, sepet ID’si ve kullanıcı ID’si parametre olarak gönderilir.
     *
     * @param basketId Sepet ID
     * @param price Toplam fiyat
     * @param buyerId Alıcı (kullanıcı) ID
     * @return İyzico tarafından oluşturulan ödeme formu bilgileri.
     */
    @PostMapping("/initialize")
    public CheckoutFormInitialize initializePayment(@RequestParam String basketId,
                                                    @RequestParam double price,
                                                    @RequestParam String buyerId) {
        return paymentService.initializePayment(basketId, price, buyerId);
    }
}
