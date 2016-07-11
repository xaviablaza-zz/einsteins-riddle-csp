
public class EinsteinConstraint implements Constraint {
	private HouseVariable v;
	private HouseVariable h1;
	private HouseVariable h2;
	private Nationality n;
	private Color c;
	private Color c1;
	private Color c2;
	private Pet p;
	private Beverage b;
	private int houseNo;
	private Cigar ci;
	private int type;
	
	public EinsteinConstraint() {
		v = null;
		h1 = null;
		h2 = null;
		n = null;
		c = null;
		c1 = null;
		c2 = null;
		p = null;
		b = null;
		houseNo = -1;
		ci = null;
		type = -1;
	}

	public EinsteinConstraint(HouseVariable v, Nationality n, Color c) {
		super();
		type = 0;
		this.v = v;
		this.n = n;
		this.c = c;
	}

	public EinsteinConstraint(HouseVariable v, Nationality n, Pet p) {
		super();
		type = 1;
		this.v = v;
		this.n = n;
		this.p = p;
	}
	
	public EinsteinConstraint(HouseVariable v, Nationality n, Beverage b) {
		super();
		type = 2;
		this.v = v;
		this.n = n;
		this.b = b;
	}

	public EinsteinConstraint(HouseVariable leftHouse, Color leftColor, HouseVariable rightHouse, Color rightColor) {
		super();
		type = 3;
		h1 = leftHouse;
		c1 = leftColor;
		h2 = rightHouse;
		c2 = rightColor;
	}

	public EinsteinConstraint(HouseVariable v, Color c, Beverage b) {
		super();
		type = 4;
		this.v = v;
		this.c = c;
		this.b = b;
	}

	public EinsteinConstraint(HouseVariable v, Cigar ci, Pet p) {
		super();
		type = 5;
		this.v = v;
		this.ci = ci;
		this.p = p;
	}

	public EinsteinConstraint(HouseVariable v, Color c, Cigar ci) {
		super();
		type = 6;
		this.v = v;
		this.c = c;
		this.ci = ci;
	}

	public EinsteinConstraint(HouseVariable v, int houseNo, Beverage b) {
		super();
		type = 7;
		this.v = v;
		this.houseNo = houseNo;
		this.b = b;
	}

	public EinsteinConstraint(HouseVariable v, int houseNo, Nationality n) {
		super();
		type = 8;
		this.v = v;
		this.houseNo = houseNo;
		this.n = n;
	}

	public EinsteinConstraint(HouseVariable cigarHouse, HouseVariable petHouse, Cigar ci, Pet p) {
		super();
		type = 9;
		h1 = cigarHouse;
		h2 = petHouse;
		this.ci = ci;
		this.p = p;
	}

	public EinsteinConstraint(HouseVariable v, Cigar ci, Beverage b) {
		super();
		type = 10;
		this.v = v;
		this.ci = ci;
		this.b = b;
	}

	public EinsteinConstraint(HouseVariable v, Nationality n, Cigar ci) {
		super();
		type = 11;
		this.v = v;
		this.n = n;
		this.ci = ci;
	}

	public EinsteinConstraint(HouseVariable nationalityHouse, HouseVariable colorHouse, Nationality n, Color c) {
		super();
		type = 12;
		h1 = nationalityHouse;
		h2 = colorHouse;
		this.n = n;
		this.c = c;
	}

	public EinsteinConstraint(HouseVariable cigarHouse, HouseVariable beverageHouse, Cigar ci, Beverage b) {
		super();
		type = 13;
		h1 = cigarHouse;
		h2 = beverageHouse;
		this.ci = ci;
		this.b = b;
	}
	
	private boolean nextTo(HouseVariable h1, HouseVariable h2) {
		if (Math.abs(h1.houseNo-h2.houseNo) == 1) return true;
		else return false;
	}

	@Override
	public boolean isSatisfied() {
		switch (type) {
			case 0:
				if (v.nationality.contains(n) && v.color.contains(c)) return true;
			case 1:
				if (v.nationality.contains(n) && v.pet.contains(p)) return true;
			case 2:
				if (v.nationality.contains(n) && v.beverage.contains(b)) return true;
			case 3:
				if (h2.houseNo-h1.houseNo == 1 && h2.color.contains(c2) && h1.color.contains(c1)) return true;
			case 4:
				if (v.color.contains(c) && v.beverage.contains(b)) return true;
			case 5:
				if (v.cigar.contains(ci) && v.pet.contains(p)) return true;
			case 6:
				if (v.color.contains(c) && v.cigar.contains(ci)) return true;
			case 7:
				if (v.houseNo == houseNo && v.beverage.contains(b)) return true;
			case 8:
				if (v.houseNo == houseNo && v.nationality.contains(n)) return true;
			case 9:
				if (nextTo(h1, h2)) {
					if (h1.cigar.contains(ci) && h2.pet.contains(p)) return true;
					else if (h2.cigar.contains(ci) && h1.pet.contains(p)) return true;
				}
				break;
			case 10:
				if (v.cigar.contains(ci) && v.beverage.contains(b)) return true;
			case 11:
				if (v.nationality.contains(n) && v.cigar.contains(ci)) return true;
			case 12:
				if (nextTo(h1, h2)) {
					if (h1.nationality.contains(n) && h2.color.contains(c)) return true;
					else if (h2.nationality.contains(n) && h1.color.contains(c)) return true;
				}
				break;
			case 13:
				if (nextTo(h1, h2)) {
					if (h1.cigar.contains(ci) && h2.beverage.contains(b)) return true;
					else if (h2.cigar.contains(ci) && h1.beverage.contains(b)) return true;
				}
				break;
		}
		return false;
	}

}
