package com.ochaumont.demo.skillknowledge.dao;

import org.springframework.stereotype.Component;

import com.ochaumont.demo.skillknowledge.domain.Expertise;


@Component(value="expertiseDAO")
public class ExpertiseDAO extends CrudDaoImpl<Expertise> {

	
	public ExpertiseDAO() {
		setEntityClass(Expertise.class);
    }
    
     
}