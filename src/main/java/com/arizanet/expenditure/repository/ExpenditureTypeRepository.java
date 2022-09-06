package com.arizanet.expenditure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arizanet.expenditure.data.ExpenditureType;

public interface ExpenditureTypeRepository extends JpaRepository<ExpenditureType, Long> {

	public List<ExpenditureType> findByType(String type);


}
