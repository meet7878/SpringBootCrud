package com.example.cargo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cargo.Entity.Details;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Integer> {

}
