import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class EinsteinsRiddle implements CSProblem {
	private List<HouseVariable> varList;
	private EinsteinConstraint[] constraints;
	private Random r;
	private final Color[] colors = Color.values();
	private final Nationality[] nationalities = Nationality.values();
	private final Cigar[] cigars = Cigar.values();
	private final Beverage[] beverages = Beverage.values();
	private final Pet[] pet = Pet.values();
	
	
	public EinsteinsRiddle() {
		varList = new ArrayList<HouseVariable>();
		for (int i=0; i<5; ++i) {
			varList.add(new HouseVariable());
		}
		r = new Random();
	}

	@Override
	public boolean solve() {
		// Backtracking
		// If varList is complete then return true
		boolean complete = true;
		for (HouseVariable h : varList) {
			boolean c = h.isComplete();
			if (complete && !c) complete = false; 
		}
		if (complete) return true;
		
		// Select unassigned variable; most-constrained-variable heuristic
		// (i.e. select a variable with the fewest remaining values)
		HouseVariable u = null;
		int minVal = 25;
		boolean emptyAssignment = true; // empty variables
		for (HouseVariable h : varList) {
			if (h.valuesLeft() < minVal) {
				u = h;
				minVal = h.valuesLeft();
				emptyAssignment = false;
			}
		}
		// Use the succeed-first principle by choosing a value that is most likely to work
		if (emptyAssignment) {
			// We assume to put Norweigan in House 1
			u = varList.get(0);
			u.nationality.clear();
			u.nationality.add(Nationality.NORWEGIAN);
		}
		// Unassigned variable selected at this point
		
		// Select an ordering for the domain of u that minimizes conflicts
		// Prefer the value that leaves the largest subset of legal values for other unassigned variables
		// (i.e. least-constraining-value heuristic or most constraints satisfied)
		Color c = colors[r.nextInt(5)];
		Nationality n = nationalities[r.nextInt(5)];
		Cigar ci = cigars[r.nextInt(5)];
		Beverage b = beverages[r.nextInt(5)];
		Pet p = pet[r.nextInt(5)];
		u.color.clear();
		u.nationality.clear();
		u.cigar.clear();
		u.beverage.clear();
		u.pet.clear();
		u.color.add(c);
		u.nationality.add(n);
		u.cigar.add(ci);
		u.beverage.add(b);
		u.pet.add(p);
		
		return false;
	}

	@Override
	public void printResult() {
		// TODO Auto-generated method stub
		
	}

}
