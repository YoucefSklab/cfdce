package com.cfdce.Resolver;

import java.util.HashSet;
import java.util.Set;

import com.cfdce.FormationCoalitions.PartitionSetCreator;

public class Partitionner {

	public Partitionner() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		 Set<Integer> baseSet = new HashSet<Integer>();
           baseSet.add(1);
	       baseSet.add(2);
	       baseSet.add(3);
	       baseSet.add(4);
	     //  baseSet.add(5);
	      // baseSet.add(6);
		 PartitionSetCreator<Integer> partSetCreator = new PartitionSetCreator<Integer>(baseSet);
		 partSetCreator.ComputeCoalitions(baseSet);
	}

}
