package com.ochaumont.demo.skillknowledge.pres.controller;

import com.ochaumont.demo.skillknowledge.domain.SkillExpertise;
import com.ochaumont.demo.skillknowledge.domain.SkillType;
import com.ochaumont.demo.skillknowledge.util.IPredicate;

public class PredicateSkillByType implements IPredicate<SkillExpertise> {

	private SkillType skilltype = null;
	
	public PredicateSkillByType(SkillType skilltype) {
		this.skilltype = skilltype;
	}
	
	public boolean apply(SkillExpertise elt) {
	
		if(elt.getSkill().getType().getName().equals(skilltype.getName())) {
			return true;
		}
		
		return false;
	}

}
