package edu.chapman.ablaz101.interfaces;

import edu.chapman.ablaz101.einstein_csp.HouseVariable;

public interface Constraint {
	
	boolean isSatisfied(HouseVariable v, HouseVariable h1, HouseVariable h2);

}
