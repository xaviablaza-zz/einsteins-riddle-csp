package edu.chapman.ablaz101.interfaces;

import edu.chapman.ablaz101.einstein_csp.HouseAssignment;
import edu.chapman.ablaz101.einstein_csp.HouseVariable;

import java.util.Queue;

public interface Constraint {
	
	boolean isSatisfied(HouseAssignment assign);

}
