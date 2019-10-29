package ctaStation;

import java.util.ArrayList;

//Author: Pierre Shum
//Date: Dec 2,2016
//Purpose: class to hold data for ctastations

public class CtaStation extends GeoLocation {
	
	private String name;
	private String location;
	private boolean wheelchair;
	private int redline;
	private int greenline;
	private int blueline;
	private int brownline;
	private int purpleline;
	private int pinkline;
	private int orangeline;
	private int yellowline;
	
	public CtaStation(){
		super();
		name=" ";
		location=" ";
		wheelchair=false;
		redline=0;
		greenline=0;
		blueline=0;
		brownline=0;
		purpleline=0;
		pinkline=0;
		orangeline=0;
		yellowline=0;
	}
	
	public CtaStation(String name, String location, boolean wheelchair, int redline, int greenline, int blueline,
			int brownline, int purpleline, int pinkline, int orangeline, int yellowline, double latitude, double longitude){
		super(latitude,longitude);
		this.name=name;
		this.location=location;
		this.wheelchair=wheelchair;
		this.redline=redline;
		this.greenline=greenline;
		this.blueline=blueline;
		this.brownline=brownline;
		this.purpleline=purpleline;
		this.pinkline=pinkline;
		this.orangeline=orangeline;
		this.yellowline=yellowline;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isWheelchair() {
		return wheelchair;
	}

	public void setWheelchair(boolean wheelchair) {
		this.wheelchair = wheelchair;
	}

	public int getRedline() {
		return redline;
	}

	public void setRedline(int redline) {
		this.redline = redline;
	}

	public int getGreenline() {
		return greenline;
	}

	public void setGreenline(int greenline) {
		this.greenline = greenline;
	}

	public int getBlueline() {
		return blueline;
	}

	public void setBlueline(int blueline) {
		this.blueline = blueline;
	}

	public int getBrownline() {
		return brownline;
	}

	public void setBrownline(int brownline) {
		this.brownline = brownline;
	}

	public int getPurpleline() {
		return purpleline;
	}

	public void setPurpleline(int purpleline) {
		this.purpleline = purpleline;
	}

	public int getPinkline() {
		return pinkline;
	}

	public void setPinkline(int pinkline) {
		this.pinkline = pinkline;
	}

	public int getOrangeline() {
		return orangeline;
	}

	public void setOrangeline(int orangeline) {
		this.orangeline = orangeline;
	}

	public int getYellowline() {
		return yellowline;
	}

	public void setYellowline(int yellowline) {
		this.yellowline = yellowline;
	}

	//calculates distance using latitude and longitude locations
	public double calcDistance(double latitude, double longitude){

		double distanceLatitude=this.latitude-latitude;
		double distanceLongitude=this.longitude-longitude;
		
		double distance=Math.sqrt((distanceLatitude*distanceLatitude)+(distanceLongitude*distanceLongitude));
		
		return distance;
	}
	
	//compares name of station to find specific station
	public int lookUpStation(ArrayList<CtaStation> station, String name, int i){
		
		int found=0;
		if(name.equalsIgnoreCase(station.get(i).getName())){
			found++;
			System.out.println("\nStation ");
			System.out.println("Name: "+station.get(i).getName()+"\nLatitude: "+station.get(i).getLatitude()+
					"\nLongitude: "+station.get(i).getLongitude()+"\nLocation: "+station.get(i).getLocation()+
					"\nWheelchair Access: "+station.get(i).isWheelchair());
			System.out.println("Line: ");
			if(station.get(i).getRedline()>0){
				System.out.println("Red Line");
			}
			if(station.get(i).getGreenline()>0){
				System.out.println("Green Line");
			}
			if(station.get(i).getBlueline()>0){
				System.out.println("Blue Line");
			}
			if(station.get(i).getBrownline()>0){
				System.out.println("Brown Line");
			}
			if(station.get(i).getPurpleline()>0){
				System.out.println("Purple Line");
			}
			if(station.get(i).getPinkline()>0){
				System.out.println("Pink Line");
			}
			if(station.get(i).getOrangeline()>0){
				System.out.println("Orange Line");
			}
			if(station.get(i).getYellowline()>0){
				System.out.println("Yellow Line");
			}
			System.out.println("\n");
		}	
		return found;
	}
	
	
	@Override
	public String toString() {
		return name + "," + latitude + "," + longitude +","+ location+ "," + wheelchair + "," + redline+ 
				"," + greenline + "," + blueline + "," + brownline + "," + purpleline+ "," + pinkline + "," + orangeline
				+ "," + yellowline;
	}

	//finds the nearest station to a location
	public void nearestStation(ArrayList<CtaStation> route, double latitude, double longitude){
		
		double distanceLatitude=this.latitude-latitude;
		double distanceLongitude=this.longitude-longitude;
		
		double distance=Math.sqrt((distanceLatitude*distanceLatitude)+(distanceLongitude*distanceLongitude));
		
		double distances=0;
		double shorterDistance=100000000;
		
		for(int i=0;i<route.size();i++){
			distances=route.get(i).calcDistance(latitude, longitude);
			if(distances<shorterDistance){
				shorterDistance=distance;
			}
		}
		for(int i=0;i<route.size();i++){
			if(shorterDistance==route.get(i).calcDistance(latitude, longitude)){
				System.out.println("The closest station to your location is "+
			route.get(i).getName()+" at "+shorterDistance+" degrees");
			}
		}
	}
	
	//removes item from arraylist
	public void removeStation(ArrayList<CtaStation> route, int index){
		route.remove(index);
	}
	//adds item to end of arraylist
	public void addStation(ArrayList<CtaStation> route, CtaStation station){
		route.add(station);
	}
	//adds item to a specific position in arraylist
	public void insertStation(ArrayList<CtaStation> route, CtaStation station, int index){
		route.add((index+1), station);
	}
}
