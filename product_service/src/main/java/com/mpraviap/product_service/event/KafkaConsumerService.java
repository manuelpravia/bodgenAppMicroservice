package com.mpraviap.product_service.event;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mpraviap.product_service.shared.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.mpraviap.product_service.application.service.ProductService;


import java.util.Map;

@Slf4j
@Service
public class KafkaConsumerService {

    @Autowired
    private ProductService productService;

    @KafkaListener(topics = "update_stock")
    public  void updateStockProduct(String message){
        var typeReference = new TypeReference<Map<String, Integer>>() {};

        var map = JsonUtil.fromJson(message, typeReference);
        productService.updateStockProduct(map)
                .subscribe();
        log.info("Mapa recibido: " + map);
    }
}
