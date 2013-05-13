package com.ochaumont.demo.skillknowledge.test;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ochaumont.demo.skillknowledge.dao.SkillDAO;
import com.ochaumont.demo.skillknowledge.domain.Skill;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-dao-test.xml"})
public class TestSkillDAO{

	@Autowired
	protected SkillDAO skillDAO;
		
	
    @Test @Transactional
    public void testAccountCreateGetUpdateDelete() {
    	Skill skillCreate = new Skill();    	
    	assertNull(skillCreate.getId());
    	
    	skillCreate.setName("Java");
    	skillCreate.setDescription("langage de programation");
    	Skill skillRead = skillDAO.save(skillCreate);
    	assertNotNull(skillRead.getId());
    	assertEquals(skillCreate,skillRead);
    	    	
    	skillRead = skillDAO.get(skillCreate.getId());
    	assertEquals(skillCreate,skillRead);
    	
    	skillDAO.delete(skillRead);
    	skillRead = skillDAO.get(skillCreate.getId());
    	    	
      	assertNull(skillRead);
      	
    }
   
}
