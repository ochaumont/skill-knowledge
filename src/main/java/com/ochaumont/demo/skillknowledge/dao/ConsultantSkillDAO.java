package com.ochaumont.demo.skillknowledge.dao;

import org.springframework.stereotype.Component;

import com.ochaumont.demo.skillknowledge.domain.ConsultantSkill;


@Component(value="consultantSkillDAO")
public class ConsultantSkillDAO extends CrudDaoImpl<ConsultantSkill> {

	
	public ConsultantSkillDAO() {
		setEntityClass(ConsultantSkill.class);
    }
    
     
}