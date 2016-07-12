package edu.chapman.ablaz101.einstein_csp;

import edu.chapman.ablaz101.enums.*;
import edu.chapman.ablaz101.interfaces.BackTrack;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Created by Xavi on 7/11/2016.
 */
public class EinsteinBackTrack implements BackTrack {
    private Queue<HouseVariable> varQueue;
    private HouseVariable curr = null;
    private boolean emptyAssignment = true;
    private Random r;
    private final EinsteinsRiddle riddle;
    HouseAssignment assign;

    public EinsteinBackTrack(EinsteinsRiddle riddle) {
        varQueue = new PriorityQueue<HouseVariable>(5, new VarComparator());
        for (int i=0; i<5; ++i) {
            varQueue.add(new HouseVariable(i));
        }
        r = new Random();
        this.riddle = riddle;
    }

    public boolean CSP_BACKTRACKING() {
        if (completeCheck()) return true;
        selectUnassignedVar();
        selectDomain();
        consistencyCheck();
        return false;
    }

    @Override
    public boolean completeCheck() {
        boolean complete = true;
        for (HouseVariable h : varQueue) {
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
        for (HouseVariable h : varQueue) {
            if (h.valuesLeft() < minVal) {
                curr = h;
                minVal = h.valuesLeft();
                emptyAssignment = false;
            }
        }

    }

    @Override
    public void selectDomain() {
        // TODO: Select an ordering for the domain of u that minimizes conflicts
        // TODO: Prefer the value that leaves the largest subset of legal values for other unassigned variables
        // TODO: So, House 2, 3, or 4 because of the NEXT_TO constraint

        // Use the succeed-first principle by choosing a value that is most likely to work
        assign = new HouseAssignment();
        if (emptyAssignment) {
            // We assume to put Norweigan in House 1
            assign.nationality = Nationality.NORWEGIAN;
            varQueue.peek().assign = assign;
        } else {
            assign.nationality = Nationality.values()[r.nextInt(varQueue.peek().nationality.size())];
        }
        // Use random for the ordering for now
        assign.color = Color.values()[r.nextInt(r.nextInt(varQueue.peek().color.size()))];
        assign.cigar = Cigar.values()[r.nextInt(r.nextInt(varQueue.peek().cigar.size()))];
        assign.beverage = Beverage.values()[r.nextInt(r.nextInt(varQueue.peek().beverage.size()))];
        assign.pet = Pet.values()[r.nextInt(r.nextInt(varQueue.peek().pet.size()))];
    }

    @Override
    public void consistencyCheck() {

    }
}
