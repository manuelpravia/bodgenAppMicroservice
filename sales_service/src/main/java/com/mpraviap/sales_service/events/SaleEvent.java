package com.mpraviap.sales_service.events;

import java.util.Map;

public record SaleEvent(String saleId, Map<String,Integer> products){

}



