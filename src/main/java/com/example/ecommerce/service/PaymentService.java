package com.example.ecommerce.service;

import com.iyzipay.Options;
import com.iyzipay.model.CheckoutFormInitialize;
import com.iyzipay.request.CreateCheckoutFormInitializeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    @Value("${iyzico.api.key}")
    private String apiKey;

    @Value("${iyzico.secret.key}")
    private String secretKey;

    @Value("${iyzico.base.url}")
    private String baseUrl;

    // İyzico API çağrıları için gerekli ayarları içeren Options nesnesini oluşturur.
    private Options getOptions() {
        Options options = new Options();
        options.setApiKey(apiKey);
        options.setSecretKey(secretKey);
        options.setBaseUrl(baseUrl);
        return options;
    }

    /**
     * Ödeme işlemi için checkout formunu başlatır.
     *
     * @param basketId Sepetin benzersiz ID'si
     * @param price Ödenecek fiyat (örneğin, ürün toplamı)
     * @param buyerId Alıcının benzersiz ID'si
     * @return CheckoutFormInitialize nesnesi, ödeme formu bilgilerini içerir.
     */
    public CheckoutFormInitialize initializePayment(String basketId, double price, String buyerId) {
        CreateCheckoutFormInitializeRequest request = new CreateCheckoutFormInitializeRequest();
        request.setLocale("tr");
        request.setConversationId("123456789"); // Örnek sabit değer; gerçek senaryoda dinamik olabilir.

        // Fiyat bilgilerini BigDecimal olarak gönderiyoruz.
        request.setPrice(BigDecimal.valueOf(price));
        request.setPaidPrice(BigDecimal.valueOf(price));

        request.setBasketId(basketId);
        request.setPaymentGroup("PRODUCT");

        // İyzico ödeme formu initialize çağrısı yapılıyor.
        CheckoutFormInitialize checkoutFormInitialize = CheckoutFormInitialize.create(request, getOptions());
        return checkoutFormInitialize;
    }
}
