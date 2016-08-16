package edu.chapman.ablaz101.einstein_csp;

import edu.chapman.ablaz101.enums.*;
import edu.chapman.ablaz101.interfaces.BackTrack;

import java.util.Random;

/**
 * Created by Xavi on 7/11/2016.
 */
public class EinsteinBackTrack implements BackTrack {
    static HouseVariable[] vars;
    static HouseVariable curr = null;
    private Random r;
    private final EinsteinsRiddle riddle;
    HouseAssignment assign;
    public final EinsteinConstraint[] highConstraints;
    public final EinsteinConstraint[] natConstraints;
    public final EinsteinConstraint[] bevConstraints;
    public final EinsteinConstraint[] colConstraints;
    public final EinsteinConstraint[] cigConstraints;
    public final EinsteinConstraint[] petConstraints;

    public EinsteinBackTrack(EinsteinsRiddle riddle) {
        vars = new HouseVariable[5];
        for (int i=0; i<5; ++i) {
            vars[i] = new HouseVariable(i);
        }
        r = new Random();
        this.riddle = riddle;
        highConstraints = new EinsteinConstraint[] {
                new EinsteinConstraint(2, Beverage.MILK),
                new EinsteinConstraint(0, Nationality.NORWEGIAN),
                new EinsteinConstraint(0, Color.WHITE),
                new EinsteinConstraint(4, Color.GREEN)
        };
        natConstraints = new EinsteinConstraint[] {
                new EinsteinConstraint(Nationality.GERMAN, Cigar.PRINCE),
                new EinsteinConstraint(Nationality.BRIT, Color.RED),
                new EinsteinConstraint(Nationality.SWEDE, Pet.DOGS),
                new EinsteinConstraint(Nationality.DANE, Beverage.TEA),
                new EinsteinConstraint(true, Nationality.NORWEGIAN, Color.BLUE),
                new EinsteinConstraint(0, Nationality.NORWEGIAN)
        };
        bevConstraints = new EinsteinConstraint[] {
                new EinsteinConstraint(2, Beverage.MILK),
                new EinsteinConstraint(Cigar.BLENDS, Beverage.WATER),
                new EinsteinConstraint(Cigar.BLUEMASTER, Beverage.BEER),
                new EinsteinConstraint(Color.GREEN, Beverage.COFFEE),
                new EinsteinConstraint(Nationality.DANE, Beverage.TEA)
        };
        colConstraints = new EinsteinConstraint[] {
                new EinsteinConstraint(Color.YELLOW, Cigar.DUNHILL),
                new EinsteinConstraint(Color.GREEN, Color.WHITE),
                new EinsteinConstraint(Color.GREEN, Beverage.COFFEE),
                new EinsteinConstraint(Nationality.BRIT, Color.RED),
                new EinsteinConstraint(0, Color.WHITE),
                new EinsteinConstraint(4, Color.GREEN),
                new EinsteinConstraint(true, Nationality.NORWEGIAN, Color.BLUE)
        };
        cigConstraints = new EinsteinConstraint[] {
                new EinsteinConstraint(Cigar.PALL_MALL, Pet.BIRDS),
                new EinsteinConstraint(Color.YELLOW, Cigar.DUNHILL),
                new EinsteinConstraint(Cigar.BLUEMASTER, Beverage.BEER),
                new EinsteinConstraint(Nationality.GERMAN, Cigar.PRINCE),
                new EinsteinConstraint(Cigar.BLENDS, Beverage.WATER),
                new EinsteinConstraint(true, Cigar.BLENDS, Pet.CATS),
                new EinsteinConstraint(true, Cigar.DUNHILL, Pet.HORSES),
        };
        petConstraints = new EinsteinConstraint[] {
                new EinsteinConstraint(true, Cigar.BLENDS, Pet.CATS),
                new EinsteinConstraint(true, Cigar.DUNHILL, Pet.HORSES),
                new EinsteinConstraint(Nationality.SWEDE, Pet.DOGS),
                new EinsteinConstraint(Cigar.PALL_MALL, Pet.BIRDS),
        };
    }

    public boolean CSP_BACKTRACKING() {
        // If backtrack is complete then return success
        if (completeCheck()) return true;

        // Select unassigned variable
        selectUnassignedVar();

        // Select an ordering for the domain of the variable
        selectDomain();

        // Check if variables are consistent
        boolean satisfied = true;
        // Check the constraints that only apply to the part of the domain that is being checked
        for (EinsteinConstraint c : natConstraints) {
            if (!c.isSatisfied(assign, assign.nationality))
                satisfied = false;
        }
        if (satisfied)
            curr.assign.nationality = assign.nationality;
        else
            curr.assign.nationality = null;

        for (EinsteinConstraint c : bevConstraints) {
            if (!c.isSatisfied(assign, assign.beverage))
                satisfied = false;
        }
        if (satisfied)
            curr.assign.beverage = assign.beverage;
        else
            curr.assign.beverage = null;

        for (EinsteinConstraint c : colConstraints) {
            if (!c.isSatisfied(assign, assign.color))
                satisfied = false;
        }
        if (satisfied)
            curr.assign.color = assign.color;
        else
            curr.assign.color = null;

        for (EinsteinConstraint c : cigConstraints) {
            if (!c.isSatisfied(assign, assign.cigar))
                satisfied = false;
        }
        if (satisfied)
            curr.assign.cigar = assign.cigar;
        else
            curr.assign.cigar = null;

        for (EinsteinConstraint c : petConstraints) {
            if (!c.isSatisfied(assign, assign.pet))
                satisfied = false;
        }
        if (satisfied)
            curr.assign.pet = assign.pet;
        else
            curr.assign.pet = null;

        boolean result = CSP_BACKTRACKING();
        if (result) return result;

        return false;
    }

    @Override
    public boolean completeCheck() {
        boolean complete = true;
        for (HouseVariable h : vars) {
            boolean c = h.isComplete();
            if (complete && !c) complete = false;
        }
        return complete;
    }

    @Override
    public void selectUnassignedVar() {
        // Select unassigned variable; most-constrained-variable heuristic
        // (i.e. select a variable with the fewest remaining values)
        int minVal = 25;
        for (HouseVariable h : vars) {
            if (h.valuesLeft() < minVal) {
                curr = h;
                minVal = h.valuesLeft();
            }
        }
    }

    @Override
    public void selectDomain() {
        // TODO: Select an ordering for the domain of u that minimizes conflicts
        // TODO: Prefer the value that leaves the largest subset of legal values for other unassigned variables
        // TODO: So, House 2, 3, or 4 because of the NEXT_TO constraint

        assign = new HouseAssignment();

        // Use random for the ordering for now
        Nationality[] nationalities = (Nationality[]) curr.nationality.toArray(); // complexity linear
        assign.nationality = nationalities[r.nextInt(nationalities.length)];
        Color[] colors = (Color[]) curr.color.toArray();
        assign.color = colors[r.nextInt(colors.length)];
        Cigar[] cigars = (Cigar[]) curr.cigar.toArray();
        assign.cigar = cigars[r.nextInt(cigars.length)];
        Beverage[] beverages = (Beverage[]) curr.beverage.toArray();
        assign.beverage = beverages[r.nextInt(beverages.length)];
        Pet[] pets = (Pet[]) curr.pet.toArray();
        assign.pet = pets[r.nextInt(pets.length)];

        // Use the succeed-first principle by choosing a value that is most likely to work
        for (EinsteinConstraint ec : highConstraints) {
            // Check positive constraints
            if (curr.houseNo == ec.houseNo) {
                // Assign
                if (ec.n != null) {
                    assign.nationality = ec.n;
                    curr.nationality.clear();
                    curr.nationality.add(ec.n);
                } else if (ec.b != null) {
                    assign.beverage = ec.b;
                    curr.beverage.clear();
                    curr.beverage.add(ec.b);
                }
                // Check negative constraints
            } else if (ec.notColor != null) {
                // Remove from set
                curr.color.remove(ec.notColor);
            }
        }

        // Apply the ordering onto the variable for the consistency check
        curr.assign = assign;

    }
}
