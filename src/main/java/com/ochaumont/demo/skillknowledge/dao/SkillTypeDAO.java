package com.ochaumont.demo.skillknowledge.dao;

import org.springframework.stereotype.Component;

import com.ochaumont.demo.skillknowledge.domain.SkillType;


@Component(value="skillTypeDAO")
public class SkillTypeDAO extends CrudDaoImpl<SkillType> {

	
	public SkillTypeDAO() {
		setEntityClass(SkillType.class);
    }
    
     
}