package com.ochaumont.demo.skillknowledge.dao;

import org.springframework.stereotype.Component;

import com.ochaumont.demo.skillknowledge.domain.Category;


@Component(value="categoryDAO")
public class CategoryDAO extends CrudDaoImpl<Category> {

	
	public CategoryDAO() {
		setEntityClass(Category.class);
    }
    
     
}