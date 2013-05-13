package com.ochaumont.demo.skillknowledge.test;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ochaumont.demo.skillknowledge.dao.CategoryDAO;
import com.ochaumont.demo.skillknowledge.dao.ConsultantSkillDAO;
import com.ochaumont.demo.skillknowledge.dao.ExpertiseDAO;
import com.ochaumont.demo.skillknowledge.dao.SkillDAO;
import com.ochaumont.demo.skillknowledge.domain.ConsultantSkill;
import com.ochaumont.demo.skillknowledge.domain.Expertise;
import com.ochaumont.demo.skillknowledge.domain.Skill;
import com.ochaumont.demo.skillknowledge.init.InitDataSource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-dao-test-initDatabase.xml"})
public class TestInitDatabase{

	@Autowired
	protected SkillDAO skillDAO;
	
	@Autowired
	protected CategoryDAO categoryDAO;
		
	@Autowired
	protected ExpertiseDAO expertiseDAO;	
	
	@Autowired
	protected ConsultantSkillDAO consultantSkillDAO;
	
	@Autowired
	protected InitDataSource initDataSource;
	
	@Test
	public void test2() {
				
						
		List<Skill> allSkills = skillDAO.getAll();		
		assertEquals(65,allSkills.size());
		
		List<Expertise> allExpertise = expertiseDAO.getAll();		
		assertEquals(2,allExpertise.size());
		
		List<ConsultantSkill> allConsultantsSkill = consultantSkillDAO.getAll();		
		assertEquals(1,allConsultantsSkill.size());
		
		
    }
	
	@Test @Transactional
	public void ConsultantSkills() {
				
		ConsultantSkill consultantSkill =  consultantSkillDAO.getBy("name", "chaumont").get(0);
	    consultantSkill.setFirstname("olvier2");
	    consultantSkillDAO.save(consultantSkill);
		
	}
   
}
