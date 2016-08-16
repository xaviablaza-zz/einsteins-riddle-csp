package edu.chapman.ablaz101.einstein_csp;

import edu.chapman.ablaz101.enums.*;

import java.util.Random;

public class EinsteinConstraint {
	public Nationality n;
	private Color c;
	private Color c1;
	private Color c2;
	public Color notColor;
	private Pet p;
	public Beverage b;
	public int houseNo;
	private Cigar ci;
	private int type;
	private ConstraintPriority cp;
	private Random r;
	
	public EinsteinConstraint() {
		n = null;
		c = null;
		c1 = null;
		c2 = null;
		notColor = null;
		p = null;
		b = null;
		houseNo = -1;
		ci = null;
		type = -1;
		cp = null;
		r = new Random();
	}

	public EinsteinConstraint(Nationality n, Color c) {
		super();
		type = 0;
		this.n = n;
		this.c = c;
		cp = ConstraintPriority.LOW;
	}

	public EinsteinConstraint(Nationality n, Pet p) {
		super();
		type = 1;
		this.n = n;
		this.p = p;
		cp = ConstraintPriority.LOW;
	}
	
	public EinsteinConstraint(Nationality n, Beverage b) {
		super();
		type = 2;
		this.n = n;
		this.b = b;
		cp = ConstraintPriority.LOW;
	}

	public EinsteinConstraint(Color leftColor, Color rightColor) {
		super();
		type = 3;
		c1 = leftColor;
		c2 = rightColor;
		cp = ConstraintPriority.MEDIUM;
	}

	public EinsteinConstraint(Color c, Beverage b) {
		super();
		type = 4;
		this.c = c;
		this.b = b;
		cp = ConstraintPriority.LOW;
	}

	public EinsteinConstraint(Cigar ci, Pet p) {
		super();
		type = 5;
		this.ci = ci;
		this.p = p;
		cp = ConstraintPriority.LOW;
	}

	public EinsteinConstraint(Color c, Cigar ci) {
		super();
		type = 6;
		this.c = c;
		this.ci = ci;
		cp = ConstraintPriority.LOW;
	}

	public EinsteinConstraint(int houseNo, Beverage b) {
		super();
		type = 7;
		this.houseNo = houseNo;
		this.b = b;
		cp = ConstraintPriority.HIGH;
	}

	public EinsteinConstraint(int houseNo, Nationality n) {
		super();
		type = 8;
		this.houseNo = houseNo;
		this.n = n;
		cp = ConstraintPriority.HIGH;
	}

	public EinsteinConstraint(boolean nextTo, Cigar ci, Pet p) {
		super();
		type = 9;
		this.ci = ci;
		this.p = p;
		cp =  ConstraintPriority.MEDIUM;
	}

	public EinsteinConstraint(Cigar ci, Beverage b) {
		super();
		type = 10;
		this.ci = ci;
		this.b = b;
		cp = ConstraintPriority.LOW;
	}

	public EinsteinConstraint(Nationality n, Cigar ci) {
		super();
		type = 11;
		this.n = n;
		this.ci = ci;
		cp = ConstraintPriority.LOW;
	}

	public EinsteinConstraint(boolean nextTo, Nationality n, Color c) {
		super();
		type = 12;
		this.n = n;
		this.c = c;
		cp = ConstraintPriority.MEDIUM;
	}

	public EinsteinConstraint(boolean nextTo, Cigar ci, Beverage b) {
		super();
		type = 13;
		this.ci = ci;
		this.b = b;
		cp = ConstraintPriority.MEDIUM;
	}

	public EinsteinConstraint(int houseNo, Color notColor) {
		super();
		type = 14;
		this.houseNo = houseNo;
		this.notColor = notColor;
		cp = ConstraintPriority.HIGH;
	}
	
	private boolean nextTo(HouseVariable h1, HouseVariable h2) {
		if (Math.abs(h1.houseNo-h2.houseNo) == 1) return true;
		else return false;
	}

	public boolean isSatisfied(HouseAssignment assign, Nationality nationality) {
		switch (type) {
			case 0:
				// xnor
				if (nationality.equals(n) == assign.color.equals(c)) return true;
			case 1:
				if (nationality.equals(n) == assign.pet.equals(p)) return true;
			case 2:
				if (assign.nationality.equals(n) == assign.beverage.equals(b)) return true;
			case 8:
				if (EinsteinBackTrack.vars[houseNo].nationality.equals(n)) return true;
			case 11:
				if (assign.nationality.equals(n) && assign.cigar.equals(ci)) return true;
			case 12:
				for (int i=0; i<EinsteinBackTrack.vars.length-1; ++i) {
					if ((EinsteinBackTrack.vars[i].nationality.equals(n) && EinsteinBackTrack.vars[i+1].color.equals(c))
							|| (EinsteinBackTrack.vars[i].color.equals(c) && EinsteinBackTrack.vars[i+1].nationality.equals(n))) return true;
				}
		}
		return false;
	}

	public boolean isSatisfied(HouseAssignment assign, Beverage beverage) {
		switch (type) {
			case 2:
				if (assign.nationality.equals(n) == assign.beverage.equals(b)) return true;
			case 4:
				if (assign.color.equals(c) == assign.beverage.equals(b)) return true;
			case 7:
				if (EinsteinBackTrack.vars[houseNo].beverage.equals(b)) return true;
			case 10:
				if (assign.cigar.equals(ci) == assign.beverage.equals(b)) return true;
			case 13:
				for (int i=0; i<EinsteinBackTrack.vars.length-1; ++i) {
					if ((EinsteinBackTrack.vars[i].cigar.equals(ci) && EinsteinBackTrack.vars[i+1].beverage.equals(b))
							|| (EinsteinBackTrack.vars[i].beverage.equals(b) && EinsteinBackTrack.vars[i+1].cigar.equals(ci))) return true;
				}
		}
		return false;
	}

	public boolean isSatisfied(HouseAssignment assign, Color color) {
		switch (type) {
			case 0:
				if (assign.nationality.equals(n) == assign.color.equals(c)) return true;
			case 3:
				for (int i=0; i<EinsteinBackTrack.vars.length-1; ++i) {
					if (EinsteinBackTrack.vars[i].color.equals(c1)
							&& !EinsteinBackTrack.vars[i].color.equals(c2)
							&& EinsteinBackTrack.vars[i+1].color.equals(c2)
							&& !EinsteinBackTrack.vars[i+1].color.equals(c1)) return true;
				}
			case 4:
				if (assign.color.equals(c) == assign.beverage.equals(b)) return true;
			case 6:
				if (assign.color.equals(c) == assign.cigar.equals(ci)) return true;
			case 12:
				for (int i=0; i<EinsteinBackTrack.vars.length-1; ++i) {
					if ((EinsteinBackTrack.vars[i].nationality.equals(n) && EinsteinBackTrack.vars[i+1].color.equals(c))
							|| (EinsteinBackTrack.vars[i].color.equals(c) && EinsteinBackTrack.vars[i+1].nationality.equals(n))) return true;
				}
		}
		return false;
	}

	public boolean isSatisfied(HouseAssignment assign, Cigar cigar) {
		switch (type) {
			case 5:
				if (assign.cigar.equals(ci) == assign.pet.equals(p)) return true;
			case 9:
				for (int i=0; i<EinsteinBackTrack.vars.length-1; ++i) {
					if ((EinsteinBackTrack.vars[i].cigar.equals(ci) && EinsteinBackTrack.vars[i+1].pet.equals(p))
							|| (EinsteinBackTrack.vars[i].pet.equals(p) && EinsteinBackTrack.vars[i+1].cigar.equals(ci))) return true;
				}
			case 10:
				if (assign.cigar.equals(ci) == assign.beverage.equals(b)) return true;
			case 11:
				if (assign.nationality.equals(n) && assign.cigar.equals(ci)) return true;
			case 13:
				for (int i=0; i<EinsteinBackTrack.vars.length-1; ++i) {
					if ((EinsteinBackTrack.vars[i].cigar.equals(ci) && EinsteinBackTrack.vars[i+1].beverage.equals(b))
							|| (EinsteinBackTrack.vars[i].beverage.equals(b) && EinsteinBackTrack.vars[i+1].cigar.equals(ci))) return true;
				}
		}
		return false;
	}

	public boolean isSatisfied(HouseAssignment assign, Pet pet) {
		switch (type) {
			case 1:
				if (assign.nationality.equals(n) == assign.pet.equals(p)) return true;
			case 5:
				if (assign.cigar.equals(ci) == assign.pet.equals(p)) return true;
			case 9:
				for (int i=0; i<EinsteinBackTrack.vars.length-1; ++i) {
					if ((EinsteinBackTrack.vars[i].cigar.equals(ci) && EinsteinBackTrack.vars[i+1].pet.equals(p))
							|| (EinsteinBackTrack.vars[i].pet.equals(p) && EinsteinBackTrack.vars[i+1].cigar.equals(ci))) return true;
				}
		}
		return false;
	}
}
