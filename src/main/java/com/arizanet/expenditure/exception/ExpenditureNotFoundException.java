package com.arizanet.expenditure.exception;

@SuppressWarnings("serial")
public class ExpenditureNotFoundException extends RuntimeException {

	public ExpenditureNotFoundException(Long id) {
        super("Could not find expenditure " + id);
    }
}