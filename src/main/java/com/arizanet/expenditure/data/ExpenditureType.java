package com.arizanet.expenditure.data;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExpenditureType {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expenditure_type_id_sequence")
	@Column(name="expenditure_type_id")
	private long expeditureTypeId;
	
	private String type;
	
	public ExpenditureType() {}

	public ExpenditureType(String type) {
		super();
		this.type = type;
	}

	public long getExpeditureTypeId() {
		return expeditureTypeId;
	}

	public void setPaymentTypeId(long expeditureTypeId) {
		this.expeditureTypeId = expeditureTypeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expeditureTypeId, type);
	}

	@Override
	public String toString() {
		return "ExpenditureType [expeditureTypeId=" + expeditureTypeId + ", type=" + type + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExpenditureType other = (ExpenditureType) obj;
		return expeditureTypeId == other.expeditureTypeId && Objects.equals(type, other.type);
	}
	
	
}
