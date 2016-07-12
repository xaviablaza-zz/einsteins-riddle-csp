package edu.chapman.ablaz101.einstein_csp;

import edu.chapman.ablaz101.enums.*;
import edu.chapman.ablaz101.interfaces.CSProblem;

public class EinsteinsRiddle implements CSProblem {
    public final EinsteinConstraint[] constraints;
	
	public EinsteinsRiddle() {
        constraints = new EinsteinConstraint[] {
                new EinsteinConstraint(Nationality.BRIT, Color.RED),
                new EinsteinConstraint(Nationality.SWEDE, Pet.DOGS),
                new EinsteinConstraint(Nationality.DANE, Beverage.TEA),
                new EinsteinConstraint(Color.GREEN, Color.WHITE),
                new EinsteinConstraint(Color.GREEN, Beverage.COFFEE),
                new EinsteinConstraint(Cigar.PALL_MALL, Pet.BIRDS),
                new EinsteinConstraint(Color.YELLOW, Cigar.DUNHILL),
                new EinsteinConstraint(2, Beverage.MILK),
                new EinsteinConstraint(0, Nationality.NORWEGIAN),
                new EinsteinConstraint(Cigar.BLENDS, Pet.CATS),
                new EinsteinConstraint(Cigar.DUNHILL, Pet.HORSES),
                new EinsteinConstraint(Cigar.BLUEMASTER, Beverage.BEER),
                new EinsteinConstraint(Nationality.GERMAN, Cigar.PRINCE),
                new EinsteinConstraint(Nationality.NORWEGIAN, Color.BLUE),
                new EinsteinConstraint(Cigar.BLENDS, Beverage.WATER)
        };
    }

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
