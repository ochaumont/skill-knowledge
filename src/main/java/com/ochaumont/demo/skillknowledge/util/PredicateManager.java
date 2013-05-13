package com.ochaumont.demo.skillknowledge.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public abstract class PredicateManager {

	public static <T> Collection<T> filter(Collection<T> target, IPredicate<T> predicate,Comparator<T> comparator) {
		Collection<T> result = new TreeSet<T>(comparator);
	    for (T element : target) {
	        if (predicate.apply(element)) {
	            result.add(element);
	        }
	    }
	    return result;
	}
	
	
}