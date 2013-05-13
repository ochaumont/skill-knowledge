package com.ochaumont.demo.skillknowledge.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ConsultantJobProfil {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", length = 30, unique = true, nullable = false)
	private Long id; 
	
	// poste occupe
	
	@Column(name = "JOB_TITLE", nullable = false)
	private JobTitle jobTitle;
	
	//management
	
	@Column(name = "MANAGER", nullable = false)
	private boolean manager;
	
	@Column(name = "NBR_PEOPLE_MANAGED", nullable = false)
	private boolean nbrPeopleManage;
	
	//salaire
	
	@Column(name = "SALARY_AMOUNT", nullable = false)
	private int salaryAmount;
	
    //company
	
	@Column(name = "COMPANY_SIZE", nullable = false)
	private int companySize;
	
	@Column(name = "COMPANY_SECTOR", nullable = false)
	private CompanySector companySector;
	
	@Column(name = "ENTRY_DATE", nullable = false)
	private Date entryDate;
	
	@Column(name = "COMPANY_DATE_CREATION", nullable = false)
	private Date companyDateCreation;
	
	//geographie
	
	@Column(name = "COUNTRY", nullable = false, length = 255)
	private String country;
	
	@Column(name = "REGION", nullable = false, length = 255)
	private String region;
	
	@Column(name = "DEPARTEMENT", nullable = false, length = 255)
	private String departement;
	
	@Column(name = "CITY", nullable = false, length = 255)
	private String city;
	
	
}
