package com.arizanet.expenditure.data;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Expenditure {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expenditure_id_sequence")
	@Column(name = "expenditure_id")
	private Long expenditureId;
	
	private Account account;
	
	private Double value;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="expenditure_type")
	private ExpenditureType expenditureType;
	
	private Boolean monthlyExpense;
	
	private Date expenseDate;
	
	public Expenditure() {}

	public Expenditure(Account account, Double value, String description, ExpenditureType expenditureType,
			Boolean monthlyExpense, Date expenseDate) {
		super();
		this.account = account;
		this.value = value;
		this.description = description;
		this.expenditureType = expenditureType;
		this.monthlyExpense = monthlyExpense;
		this.expenseDate = expenseDate;
	}

	public Long getExpenditureId() {
		return expenditureId;
	}

	public void setExpenditureId(Long expenditureId) {
		this.expenditureId = expenditureId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ExpenditureType getExpenditureType() {
		return expenditureType;
	}

	public void setExpenditureType(ExpenditureType expenditureType) {
		this.expenditureType = expenditureType;
	}

	public Boolean getMonthlyExpense() {
		return monthlyExpense;
	}

	public void setMonthlyExpense(Boolean monthlyExpense) {
		this.monthlyExpense = monthlyExpense;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, description, expenditureId, expenditureType, expenseDate, monthlyExpense, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expenditure other = (Expenditure) obj;
		return account == other.account && Objects.equals(description, other.description)
				&& Objects.equals(expenditureId, other.expenditureId)
				&& Objects.equals(expenditureType, other.expenditureType)
				&& Objects.equals(expenseDate, other.expenseDate)
				&& Objects.equals(monthlyExpense, other.monthlyExpense) && Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "Expenditure [expenditureId=" + expenditureId + ", account=" + account + ", value=" + value
				+ ", description=" + description + ", expenditureType=" + expenditureType + ", monthlyExpense="
				+ monthlyExpense + ", expenseDate=" + expenseDate + "]";
	}

}
