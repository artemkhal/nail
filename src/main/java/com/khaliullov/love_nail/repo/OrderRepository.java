package com.khaliullov.love_nail.repo;

import com.khaliullov.love_nail.entity.Client;
import com.khaliullov.love_nail.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    Order findBySecretKey(long secretKey);
}
