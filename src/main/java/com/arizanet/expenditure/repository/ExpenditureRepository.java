package com.arizanet.expenditure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arizanet.expenditure.data.Expenditure;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {

}
