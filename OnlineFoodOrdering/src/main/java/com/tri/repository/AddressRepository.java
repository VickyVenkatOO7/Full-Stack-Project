package com.tri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tri.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
