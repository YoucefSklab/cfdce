package com.cfdce.Resolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test {
	
	public static <T> Set<Set<T>> powerSet(Set<T> myset) {
        Set<Set<T>> pset = new HashSet<Set<T>>();
        if (myset.isEmpty()) {
            pset.add(new HashSet<T>());
            return pset;
        }
        List<T> list = new ArrayList<T>(myset);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            pset.add(newSet);
            pset.add(set); 
        }

        return pset;
    }

}
