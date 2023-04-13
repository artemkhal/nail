package com.khaliullov.love_nail.repo;

import com.khaliullov.love_nail.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    Client findByChatId(long id);
}
