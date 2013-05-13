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
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", length = 30, unique = true, nullable = false)
	private Long id; 
	
	@Column(name = "NAME", nullable = false, length = 255)
	private String name;
	
	@Column(name = "LABEL", nullable = false, length = 255)
	private String label;
	
	@Column(name = "DESCRIPTION", length = 2000)
	private String description;
	
	@ManyToMany(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)	
	private Set<Skill> skills = new HashSet<Skill>();

	public Category() {
	
	}
	
	public Category(String name, String label, String description) {
		this.name = name;
		this.label = label;
		this.description = description;
	}
	
	public Category(String name, String description) {
		this.name = name;
		this.label = name;
		this.description = description;
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

	public Set<Skill> getSkills() {
		return skills;
	}
	
	public void addSkill(Skill skill) {
		this.skills.add(skill);
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	
}
