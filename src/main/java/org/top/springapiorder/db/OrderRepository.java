package org.top.springapiorder.db;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderTEntity,Integer> {

}
