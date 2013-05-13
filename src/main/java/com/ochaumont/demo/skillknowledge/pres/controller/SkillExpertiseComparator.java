package com.ochaumont.demo.skillknowledge.pres.controller;

import java.util.Comparator;

import com.ochaumont.demo.skillknowledge.domain.SkillExpertise;

public class SkillExpertiseComparator implements Comparator<SkillExpertise>{

	public int compare(SkillExpertise o1, SkillExpertise o2) {
		
		int levelCompare = o1.getLevel()-o2.getLevel();
		if(levelCompare == 0) {		
			return o1.getSkill().getLabel().compareTo(o2.getSkill().getLabel());
		}else {
			return levelCompare;
		}
	}

}
