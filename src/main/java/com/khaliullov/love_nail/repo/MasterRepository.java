package com.khaliullov.love_nail.repo;

import com.khaliullov.love_nail.entity.Master;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface MasterRepository extends CrudRepository<Master, Integer> {
    Master findMasterByName(String name);
}
