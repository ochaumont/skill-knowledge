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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EXPERTISE")
public class Expertise {
	
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
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)	
	private Set<Category> categories = new HashSet<Category>();

	public Expertise() {
		
	}
	
	public Expertise(String name,String label, String description) {
		this.name = name;
		this.label = label;
		this.description = description;
	}
	
	public Expertise(String name, String description) {
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

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	public void addCategory(Category category) {
		this.categories.add(category);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
}
