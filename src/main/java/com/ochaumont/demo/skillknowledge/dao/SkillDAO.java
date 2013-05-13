package com.ochaumont.demo.skillknowledge.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ochaumont.demo.skillknowledge.domain.Skill;


@Component(value="skillDAO")
public class SkillDAO extends CrudDaoImpl<Skill> {

	
	public SkillDAO() {
		setEntityClass(Skill.class);
    }
    
     
}