package com.ochaumont.demo.skillknowledge.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SKILL_TYPE")
public class SkillType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", length = 30, unique = true, nullable = false)
	private Long id; 
	
	@Column(name = "NAME", nullable = false, length = 255)
	private String name;
	
	@Column(name = "LABEL", nullable = false, length = 255)
	private String label;
	


	public SkillType() {
		
	}
	
	public SkillType(String name,String label) {
		this.name = name;
		this.label = label;
		
	}
			

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
