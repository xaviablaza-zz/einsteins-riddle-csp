package edu.chapman.ablaz101.einstein_csp;

import edu.chapman.ablaz101.interfaces.CSProblem;

public class EinsteinsRiddle implements CSProblem {

	public EinsteinsRiddle(){}

	@Override
	public boolean solve() {
        EinsteinBackTrack ebt = new EinsteinBackTrack(this);
        ebt.CSP_BACKTRACKING();
		return false;
	}

	@Override
	public void printResult() {
		// TODO Auto-generated method stub
		
	}

}
