package com.khaliullov.love_nail.service;

import com.khaliullov.love_nail.api.OrderRequest;
import com.khaliullov.love_nail.api.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(String request);

}
