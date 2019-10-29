package ctaStation;

//Author: Pierre Shum
//Date: Dec 2,2016
//Purpose: Superclass for CtaStation class
public class GeoLocation {

	protected double latitude; //latitude location of station
	protected double longitude; // longitude location of station
	
	public GeoLocation(){
		latitude=0;
		longitude=0;
	}

	public GeoLocation(double latitude,double longitude){
		this.latitude=latitude;
		this.longitude=longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "GeoLocation [latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
	
}
