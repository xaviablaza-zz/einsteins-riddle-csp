package edu.chapman.ablaz101.einstein_csp;

import java.util.Comparator;

/**
 * Created by Xavi on 7/11/2016.
 */
public class VarComparator implements Comparator<HouseVariable> {
    public VarComparator(){}

    @Override
    public int compare(HouseVariable o1, HouseVariable o2) {
        if (o1.valuesLeft() < o2.valuesLeft()) return -1;
        if (o1.valuesLeft() > o2.valuesLeft()) return 1;
        return 0;
    }
}
