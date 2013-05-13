package com.ochaumont.demo.skillknowledge.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SKILL_EXPERTISE")
public class SkillExpertise {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", length = 30, unique = true, nullable = false)
	private Long id; 
		
	@ManyToOne(cascade = CascadeType.DETACH,fetch=FetchType.EAGER)	
	private Skill skill = null;
	
	@Column(name = "LEVEL", nullable = false, length = 10)
	private int level = 0;
	
	public SkillExpertise() {
		
	}
	
	public SkillExpertise(Skill skill, int level) {	
		this.skill = skill;
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
}
