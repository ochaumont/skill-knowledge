package com.ochaumont.demo.skillknowledge.init;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.ochaumont.demo.skillknowledge.dao.ConsultantSkillDAO;
import com.ochaumont.demo.skillknowledge.dao.ExpertiseDAO;
import com.ochaumont.demo.skillknowledge.dao.SkillDAO;
import com.ochaumont.demo.skillknowledge.dao.SkillTypeDAO;
import com.ochaumont.demo.skillknowledge.domain.Category;
import com.ochaumont.demo.skillknowledge.domain.ConsultantSkill;
import com.ochaumont.demo.skillknowledge.domain.Expertise;
import com.ochaumont.demo.skillknowledge.domain.Skill;
import com.ochaumont.demo.skillknowledge.domain.SkillExpertise;
import com.ochaumont.demo.skillknowledge.domain.SkillType;
import com.ochaumont.demo.skillknowledge.xml.Knowledge;
import com.ochaumont.demo.skillknowledge.xml.ParseXML;


public class InitDataSource {

	private boolean run = true;
	
	private static Logger logger = LoggerFactory.getLogger(InitDataSource.class);
	
	@Autowired
    ApplicationContext springContext;
	
	@Autowired
	private SkillDAO skillDao;
	
	@Autowired
	private SkillTypeDAO skillTypeDao;
	
	@Autowired
	private ExpertiseDAO expertiseDao;
	
	@Autowired
	private ConsultantSkillDAO consultantSkillDao;
	
	public InitDataSource() {
	}

	@Transactional
	public void init() {
		if(run) {				
			Knowledge know = ParseXML.parseInputStream();
			
			
			for (com.ochaumont.demo.skillknowledge.xml.SkillType skilltypeXML : know.getSkillType()) {
				SkillType skillType = new SkillType(skilltypeXML.getName(),skilltypeXML.getLabel());
				skillTypeDao.save(skillType);				
			}
			
			for (com.ochaumont.demo.skillknowledge.xml.Skill skillXML : know.getSkill()) {
				Skill skill = new Skill(skillXML.getName(), skillXML.getLabel(), skillXML.getDescription());
								
				List<SkillType> findSkillTypes = skillTypeDao.getBy("name",skillXML.getType());
				if(findSkillTypes == null || findSkillTypes.size() != 1) {
					throw new RuntimeException("the skill type "+skillXML.getType()+" is not find!");
				}				
				skill.setType(findSkillTypes.get(0));
				skillDao.save(skill);				
			}
			
			for (com.ochaumont.demo.skillknowledge.xml.Expertise expertiseXML : know.getExpertise()) {
				Expertise expertise = new Expertise(expertiseXML.getName(), expertiseXML.getLabel(), expertiseXML.getDescription());
				
				for (com.ochaumont.demo.skillknowledge.xml.Category catXML : expertiseXML.getCategory()) {
					Category cat = new Category(catXML.getName(),catXML.getLabel(),catXML.getDescription());
					
					for (com.ochaumont.demo.skillknowledge.xml.SkillRef skillRef : catXML.getSkill()) {
						List<Skill> findSkills = skillDao.getBy("name",skillRef.getRef());
						if(findSkills == null || findSkills.size() != 1) {
							throw new RuntimeException("the skill "+skillRef.getRef()+" is not find!");
						}
						cat.addSkill(findSkills.get(0));					
					}
					
					expertise.getCategories().add(cat);					
				}			
				
				expertiseDao.save(expertise);				
			}
			
			for (com.ochaumont.demo.skillknowledge.xml.Consultant consultantXML : know.getConsultant()) {
				ConsultantSkill consultantSkill = new ConsultantSkill(consultantXML.getName(), consultantXML.getFirstname());
				
				for (com.ochaumont.demo.skillknowledge.xml.SkillRef skillRef : consultantXML.getSkill()) {
					List<Skill> findSkills = skillDao.getBy("name",skillRef.getRef());
					if(findSkills == null || findSkills.size() != 1) {
						throw new RuntimeException("the skill "+skillRef.getRef()+" is not find!");
					}
					SkillExpertise skillExpertise = new SkillExpertise(findSkills.get(0),skillRef.getLevel());
					consultantSkill.getSkillExps().add(skillExpertise);
				}
				
				consultantSkillDao.save(consultantSkill);
			}
			
		}
	}
	
	public void initTransactionnel() {
		((InitDataSource)springContext.getBean("initDataSource")).init();
	}
	
	
	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

}
