package com.khaliullov.love_nail.repo;

import com.khaliullov.love_nail.api.OrderRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRequestRepo extends CrudRepository<OrderRequest, Integer> {
}
