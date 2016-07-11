import java.util.HashSet;
import java.util.Set;

public class HouseVariable {
	
	public Set<Color> color;
	public Set<Nationality> nationality;
	public Set<Beverage> beverage;
	public Set<Cigar> cigar;
	public Set<Pet> pet;
	public int houseNo;
	public boolean complete = false;
	
	public HouseVariable() {
		color = new HashSet<Color>();
		nationality = new HashSet<Nationality>();
		beverage = new HashSet<Beverage>();
		cigar = new HashSet<Cigar>();
		pet = new HashSet<Pet>();
		for (Color c : Color.values()) {
			color.add(c);
		}
		for (Nationality n : Nationality.values()) {
			nationality.add(n);
		}
		for (Beverage b : Beverage.values()) {
			beverage.add(b);
		}
		for (Cigar c : Cigar.values()) {
			cigar.add(c);
		}
		for (Pet p : Pet.values()) {
			pet.add(p);
		}
	}
	
	public HouseVariable(int houseNo) {
		super();
		this.houseNo = houseNo;
	}
	
	public boolean isComplete() {
		if (color.size() == 1
				&& nationality.size() == 1
				&& beverage.size() == 1
				&& cigar.size() == 1
				&& pet.size() == 1) {
			complete = true;
		} else complete = false;
		return complete;
	}
	
	public int valuesLeft() {
		return color.size() + nationality.size() + beverage.size() + cigar.size() + pet.size();
	}

}
