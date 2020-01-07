package cscie55.hw7.impl;


public class Address {

	/**
	 * buildingId in the Address object
	 */
	private int buildingId;


	/**
	 * the id of the Floor where the Apartment is situated
	 */

	private int floorId;

	/**
	 * Id of this Apartment
	 */
	private int apartmentId;


	public Address(int buildingId, int floorId, int apartmentId){
		this.buildingId = buildingId;
		this.floorId = floorId;
		this.apartmentId = apartmentId;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public int getFloorId() {
		return floorId;
	}

	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}

	public int getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(int apartmentId) {
		this.apartmentId = apartmentId;
	}

	@Override
	public String toString(){
		return this.buildingId+"_"+this.floorId+"_"+this.apartmentId;
	}

	@Override public int hashCode(){
		int prime = 31;
		int result = 1;
		result = prime * result + apartmentId;
		result = prime * result + floorId;
		result = prime * result + buildingId;
		return result;
	}
}
