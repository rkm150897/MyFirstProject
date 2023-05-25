package com.example.MyFirstProject.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor 
@AllArgsConstructor
public class KycEmployee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long kycId;
	
	private Boolean isAprroved=false;

	private Boolean isVerified=false;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="application_form_application_id")
	ApplicationForm application;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="bank_employee_employee_id")
	BankEmployee issuer;

	public Long getKycId() {
		return kycId;
	}

	public void setKycId(Long kycId) {
		this.kycId = kycId;
	}

	public Boolean getIsAprroved() {
		return isAprroved;
	}

	public void setIsAprroved(Boolean isAprroved) {
		this.isAprroved = isAprroved;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public ApplicationForm getApplication() {
		return application;
	}

	public void setApplication(ApplicationForm application) {
		this.application = application;
	}

	public BankEmployee getIssuer() {
		return issuer;
	}

	public void setIssuer(BankEmployee issuer) {
		this.issuer = issuer;
	}
	
}
