package cscie55.hw7.impl;

import cscie55.hw7.exception.TooManyResidentsException;
import cscie55.hw7.foodservice.FoodOrder;
import cscie55.hw7.foodservice.TakeOutShop;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Apartment {

	private final int id;

	private final int floorId;

	private final int bldgId = Building.ID;

	private final Address address;

	public static final int MAX_RESIDENTS = 5;

	private List<Resident> residents = new ArrayList<>();

	private Collection<FoodOrder> ordersWaiting = new ArrayDeque<FoodOrder>();

	private WebBrowser browser;

	/**
	 * Constructor
	 * @param id  the id of the apartment
	 * @param floorId the id of the Floor where this apartment is located.
	 * @see Floor
	 */
	public Apartment(int id, int floorId){
		this.id = id;
		this.floorId = floorId;
		this.address = new Address(Building.ID,floorId,id);
		this.browser = new WebBrowser(this, new TakeOutShop());
	}

	public void addResident(Resident resident) throws TooManyResidentsException {
		if(residents.size() < MAX_RESIDENTS){
			residents.add(resident);
		}
		else{
			throw new TooManyResidentsException();
		}
	}

	public Address getAddress(){
		return  this.address;
	}

	public int getId() {
		return id;
	}

	public int getFloorId() {
		return floorId;
	}

	public int getKey(){
		return this.hashCode();
	}

	public List<Resident> getResidents() {
		return residents;
	}

	public void setResidents(List<Resident> residents) {
		this.residents = residents;
	}

	public WebBrowser getBrowser() {
		return browser;
	}

	public void setBrowser(WebBrowser browser) {
		this.browser = browser;
	}

	protected void addOrderWaiting(FoodOrder order){
		this.ordersWaiting.add(order);
	}

	@Override public int hashCode(){
		int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + floorId;
		result = prime * result + bldgId;
		return result;
	}
}
