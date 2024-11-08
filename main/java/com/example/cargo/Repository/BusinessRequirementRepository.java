package com.example.cargo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cargo.Entity.BusinessRequirement;

@Repository
public interface BusinessRequirementRepository extends JpaRepository<BusinessRequirement, Integer> {

}
