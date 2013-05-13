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
import com.ochaumont.demo.skillknowledge.domain.Category;
import com.ochaumont.demo.skillknowledge.domain.ConsultantSkill;
import com.ochaumont.demo.skillknowledge.domain.Expertise;
import com.ochaumont.demo.skillknowledge.domain.Skill;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-dao-test.xml"})
public class TestDAO{

	@Autowired
	protected SkillDAO skillDAO;
	
	@Autowired
	protected CategoryDAO categoryDAO;
		
	@Autowired
	protected ExpertiseDAO expertiseDAO;
	
	@Autowired
	private ConsultantSkillDAO consultantSkillDAO;
		
	@Test @Transactional
	public void ConsultantSkills() {
		ConsultantSkill consultantSkill = new ConsultantSkill("test","test");
		consultantSkillDAO.save(consultantSkill);
		
		consultantSkill =  consultantSkillDAO.getBy("name", "test").get(0);
	    consultantSkill.setFirstname("olvier2");
	    consultantSkillDAO.save(consultantSkill);
		
	}
	
	private Expertise createExpertise() {
		Expertise expertiseLogicielle = new Expertise("Expertise Logicielle","Expertise Logicielle");
		
		Category catConnaisance = new Category("Connaissance","Compétence théroique associée à l'expertise");
		   Skill skillIOC = new Skill("IOC","Injection de dépendance");
		   Skill skillAOP = new Skill("AOP","Programation orienté aspect");
		   Skill skillMVC = new Skill("MVC","Modéle vue controller");
		   Skill skillDTO = new Skill("DTO","Data transfert Object");
		   catConnaisance.addSkill(skillIOC);
		   catConnaisance.addSkill(skillAOP);
		   catConnaisance.addSkill(skillMVC);
		   catConnaisance.addSkill(skillDTO);		  
		   expertiseLogicielle.addCategory(catConnaisance);
		
		Category catLanguage = new Category("Language","Langauage technique associée à l'expertise");
		   Skill skillJava = new Skill("Java","Language HTML");
		   Skill skillHTML = new Skill("HTML","Language Java");
		   catLanguage.addSkill(skillJava);
		   catLanguage.addSkill(skillHTML);
		   expertiseLogicielle.addCategory(catLanguage);
				
		Category catPattern = new Category("Framewok","Framework technique associée à l'expertise");
		   Skill skillSpring = new Skill("Spring IOC","frameowork Spring");
		   Skill skillJEE = new Skill("JSP/Servlet","specification JEE");
		   catPattern.addSkill(skillSpring);
		   catPattern.addSkill(skillJEE);
		   expertiseLogicielle.addCategory(catPattern);
		
		 return expertiseLogicielle;
		
	}
	
	
    @Test @Transactional
    public void testCreateExpertise() {
    	Expertise exp = createExpertise();
    	expertiseDAO.save(exp);
    	    	
    	List<Skill> allSkills = skillDAO.getAll();
    	assertEquals(8, allSkills.size());
    	
    	List<Category> allCategories = categoryDAO.getAll();
    	assertEquals(3, allCategories.size());
    	
    	List<Expertise> allExpertises = expertiseDAO.getAll();
    	assertEquals(1, allExpertises.size());
    	
    	expertiseDAO.delete(exp);
    	
    	allSkills = skillDAO.getAll();
    	assertEquals(8, allSkills.size());
    	
    	allCategories = categoryDAO.getAll();
    	assertEquals(0, allCategories.size());
    	
    	allExpertises = expertiseDAO.getAll();
    	assertEquals(0, allExpertises.size());
    	    	
    }
   
}
