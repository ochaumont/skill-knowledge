package com.ochaumont.demo.skillknowledge.dao;

import java.util.List;

public interface CrudDao<T> {

	  T save(T t);
	  T get(Long id);
	  List<T> getBy(String property, Object value);
	  List<T> getAll();
	  List<T> getAll(int start, int max);
	  void delete(T t);
	  
	  
	  /*Technical methods*/
	  void clear();
	  void flush();
}