package com.ochaumont.demo.skillknowledge.dao;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ochaumont.demo.skillknowledge.domain.ConsultantSkill;
import com.ochaumont.demo.skillknowledge.domain.Skill;
import com.ochaumont.demo.skillknowledge.domain.SkillExpertise;

@Component("ConsultantService")
public class ConsultantService {

	
	@Autowired
	private ConsultantSkillDAO consultantSkillDAO;
	
	@Autowired
	private SkillDAO skillDAO;
	
	private SkillExpertise findExpertiseByConsultant(ConsultantSkill consultantSkill, String skillName) {
		for (SkillExpertise skillExp : consultantSkill.getSkillExps()) {
			if(skillExp.getSkill().getName().equals(skillName)){
				return skillExp;
			}
		}
		return null;
	}
	
	@Transactional
	public void save(ConsultantSkill cs, Map<String,Integer> dtos) {
		
		ConsultantSkill consultantSkill =  consultantSkillDAO.getBy("name", cs.getName()).get(0);
		
		Iterator<Entry<String, Integer>> it = dtos.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, Integer> e = it.next();
			String skillName = e.getKey();
			int level = e.getValue();
			SkillExpertise skillExp = findExpertiseByConsultant(consultantSkill, skillName);  
			
			if(skillExp == null && level !=0) {
				Skill skill = skillDAO.getBy("name", skillName).get(0);
				skillExp = new SkillExpertise(skill,level);
				consultantSkill.getSkillExps().add(skillExp);
			}else if(level == 0 & skillExp != null) {
				consultantSkill.getSkillExps().remove(skillExp);
			}else if(skillExp != null){
				skillExp.setLevel(level);
			}
		}
				
		consultantSkillDAO.save(consultantSkill);
		
	}
	
}
