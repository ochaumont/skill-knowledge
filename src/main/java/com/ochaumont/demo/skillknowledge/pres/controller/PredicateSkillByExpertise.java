package com.ochaumont.demo.skillknowledge.pres.controller;

import com.ochaumont.demo.skillknowledge.domain.Category;
import com.ochaumont.demo.skillknowledge.domain.Expertise;
import com.ochaumont.demo.skillknowledge.domain.Skill;
import com.ochaumont.demo.skillknowledge.domain.SkillExpertise;
import com.ochaumont.demo.skillknowledge.util.IPredicate;

public class PredicateSkillByExpertise implements IPredicate<SkillExpertise> {

	private Expertise expertise = null;
	
	public PredicateSkillByExpertise(Expertise expertise) {
		this.expertise = expertise;
	}
	
	public boolean apply(SkillExpertise elt) {
	
		for(Category cat: expertise.getCategories()) {
			for(Skill skill: cat.getSkills()) {
				if(skill.getName().equals(elt.getSkill().getName())) {
					return true;
				}
			}			
		}
				
		return false;
	}

}
