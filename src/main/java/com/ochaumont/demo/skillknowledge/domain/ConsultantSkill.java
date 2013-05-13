package com.ochaumont.demo.skillknowledge.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONSULTANT_SKILL")
public class ConsultantSkill {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", length = 30, unique = true, nullable = false)
	private Long id; 
	
	@Column(name = "NAME", nullable = false, length = 255)
	private String name;
	
	@Column(name = "FIRST_NAME", nullable = false, length = 255)
	private String firstname;
			
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)	
	private Set<SkillExpertise> skillExps = new HashSet<SkillExpertise>();
	
	
	public ConsultantSkill() {
		
	}
	
	public ConsultantSkill(String name, String firstname) {	
		this.name = name;
		this.firstname = firstname;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public Set<SkillExpertise> getSkillExps() {
		return skillExps;
	}


	public void setSkillExps(Set<SkillExpertise> skillExps) {
		this.skillExps = skillExps;
	}
	
	
}
