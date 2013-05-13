package com.ochaumont.demo.skillknowledge.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SKILL")
public class Skill {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", length = 30, unique = true, nullable = false)
	private Long id; 
	
	@Column(name = "NAME", nullable = false, length = 255)
	private String name;
	
	@Column(name = "LABEL", nullable = false, length = 255)
	private String label;
	
	@ManyToOne(cascade = CascadeType.DETACH,fetch=FetchType.EAGER)	
	private SkillType type;
	
	@Column(name = "DESCRIPTION", length = 2000)
	private String description;

	public Skill() {
		
	}
	
	public Skill(String name,String label, String description) {
		this.name = name;
		this.label = label;
		this.description = description;
	}
	
	public Skill(String name, String description) {
		this.name = name;
		this.label = name;
		this.description = description;
	}

		
	public SkillType getType() {
		return type;
	}

	public void setType(SkillType type) {
		this.type = type;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
