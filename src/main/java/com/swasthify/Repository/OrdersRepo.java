package com.swasthify.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swasthify.Entities.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Long> {

}
