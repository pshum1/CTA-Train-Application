package ctaStation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Author: Pierre Shum
//Date: Dec 2,2016
//Purpose: 
public class CtaApplication {

	//main method
	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<CtaStation> station=new ArrayList<CtaStation>();
		
		readsFile(station);

		boolean loop=false;
		do{
			loop=system(station);
		}while(!loop);
		saveFile(station);
	}
	
	//chooses between functions
	public static boolean system(ArrayList<CtaStation> station){
		
		Scanner s = new Scanner(System.in);
		int option=menu(s);
		switch(option){
		
		case 1: //display information for all stations
			displayAll(station);
			break;
		case 2: //display nearest station to a location
			displayNearest(station,s);
			break;
		case 3: //display information for a station with a specific name
			searchStation(station, s);
			break;
		case 4: //display route from one station to another
			route(station,s);
			break;
		case 5: //add a new station
			add(station,s);
			break;
		case 6:	//delete an existing station
			delete(station, s);
			break;
		case 7: //exit program
			System.out.println("Thank you for using the CTA Route App!");
			return true;
		default: //invalid case
			System.out.println("Invalid option! Please choose again \n");
			break;
			
		}
		return false;
	}
	
	//adds station to arraylist
	public static void add(ArrayList<CtaStation> route, Scanner s){
		
		int redline=-1;
		int greenline=-1;
		int blueline=-1;
		int brownline=-1;
		int purpleline=-1;
		int pinkline=-1;
		int orangeline=-1;
		int yellowline=-1;
		
		System.out.println("Add a new station. Enter new Station Details: \n");
		System.out.print("Name: ");
		String name=s.next();
		System.out.print("Latitude: ");
		double latitude=s.nextDouble();
		System.out.print("Longitude: ");
		double longitude=s.nextDouble();
		System.out.print("Location(example: elevated/surface/subway/embankment): ");
		String location=s.next();
		System.out.print("Wheelchair access (true or false): ");
		boolean wheelchair=s.nextBoolean();
		System.out.print("Route (Red/Green/Blue/Brown/Purple/Pink/Orange/Yellow): ");
		String line=s.next();
		
		int found=0;
		do{
			System.out.println("Enter names of station to insert new station between:");
			System.out.print("Name of station before: ");
			String before=s.next();
		
			for(int i=0; i<route.size(); i++){
				if(before.equalsIgnoreCase(route.get(i).getName())){
					found++;
					int index=i;
					if(line.equalsIgnoreCase("red")){
						redline=i+1;
					}
					if(line.equalsIgnoreCase("green")){
						greenline=i+1;
					}
					if(line.equalsIgnoreCase("blue")){
						blueline=i+1;
					}
					if(line.equalsIgnoreCase("brown")){
						brownline=i+1;
					}
					if(line.equalsIgnoreCase("purple")){
						purpleline=i+1;
					}
					if(line.equalsIgnoreCase("pink")){
						pinkline=i+1;
					}
					if(line.equalsIgnoreCase("orange")){
						orangeline=i+1;
					}
					if(line.equalsIgnoreCase("yellow")){
						yellowline=i+1;
					}
					CtaStation temp=new CtaStation(name, location, wheelchair, redline, greenline, blueline, brownline,
							purpleline, pinkline, orangeline, yellowline, latitude, longitude);
					route.get(i).insertStation(route, temp, index);
				}
			}
			if(found==0){
				System.out.println("Station not found!\n");
			}
		}while(found==0);
		System.out.println("Station added!");
	}
	
	//deletes station from arraylist
	public static void delete(ArrayList<CtaStation> station, Scanner s){
		
		System.out.println("Enter the name of the station to remove: ");
		String name=s.next();
		int found=0;
		
		for(int i=0; i<station.size(); i++){
			if(name.equalsIgnoreCase(station.get(i).getName())){
				found++;
				int index=i;
				station.get(i).removeStation(station, index);
			}
		}
		if(found==0){
			System.out.println("\nStation does not exist!");
		}else{
			System.out.println("\nStation removed!\n");
		}
	}
	
	//displays route from one station to another
	public static void route(ArrayList<CtaStation> station, Scanner s){
		
		boolean found=false;
		int startindex=0, endindex=0;
		do{
			System.out.print("Enter the name of the starting station: ");
			String start=s.next();
			System.out.print("Enter the name of the destination station: ");
			String end=s.next();
			
			for(int i=0; i<station.size();i++){
			
				if(start.equalsIgnoreCase(station.get(i).getName())){
					found=true;
					startindex=i;
				}
			}
			for(int i=0; i<station.size();i++){
				
				if(end.equalsIgnoreCase(station.get(i).getName())){
					found=true;
					endindex=i;
				}
			}
			if(!found){
				System.out.println("Names do not match! Try again\n");
			}
		}while(!found);
		
		System.out.println("Route:");
		if(startindex<endindex){
			for(int i=startindex;i<endindex;i++){	
				System.out.println(station.get(i).getName());
			}
		}else if(endindex<startindex){
			for(int i=startindex;i>endindex;i--){	
				System.out.println(station.get(i).getName());
			}
		}
	}
	
	//displays information for all stations
	public static void displayAll(ArrayList<CtaStation> station){
		boolean found=false;
		
		System.out.println("Stations: ");
		for(int i=0;i<station.size();i++){
			System.out.println("Name: "+station.get(i).getName()+"\nLatitude: "+station.get(i).getLatitude()+
					"\nLongitude: "+station.get(i).getLongitude()+"\nLocation: "+station.get(i).getLocation()+
					"\nWheelchair Access: "+station.get(i).isWheelchair());
			found=true;
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
		if(found==false){
			System.out.println("No Stations found.\n");
		}
	}

	//allows user to search for specific station
	public static void searchStation(ArrayList<CtaStation> station, Scanner s){
		String name=" ";
		int found=0;
		
		System.out.print("Enter the name of the station for its details: ");
		name=s.next();
		
		for(int i=0; i<station.size(); i++){
			found=station.get(i).lookUpStation(station, name,i);
		}
		if(found==0){
			System.out.println("Station not found");
		}
		
	}
	
	//displays nearest station to a location
	public static void displayNearest(ArrayList<CtaStation> station, Scanner s){

			double latitude=0;
			double longitude=0;
			
			System.out.println("Enter your location.");
			System.out.print("Enter a latitude value: ");
			latitude=s.nextDouble();
			System.out.print("Enter a longitude value: ");
			longitude=s.nextDouble();
			System.out.print("\n");
			station.get(0).nearestStation(station, latitude, longitude);
			System.out.println("\n");
		
	}
	
	//displays menu options for user
	public static int menu(Scanner s){
		
		System.out.println("CTA Route Menu. Enter an option");
		System.out.println("1. Display all stations");
		System.out.println("2. Display nearest station to a location");
		System.out.println("3. Search for a specific station");
		System.out.println("4. Generate route from station");
		System.out.println("5. Add a new station");
		System.out.println("6. Delete an existing station");
		System.out.println("7. Exit program");
		System.out.print("Option: ");
		int option=s.nextInt();
		System.out.println("\n");
		return option;
	}
	
	//reads file into an arraylist
	public static void readsFile(ArrayList<CtaStation> station) throws FileNotFoundException{
		Scanner file=new Scanner(new File("CTAStops.csv"));
		
		file.nextLine();
		file.nextLine();
		while(file.hasNextLine()){
			String line = file.nextLine();
			String[] element = line.split(",");
			
			double latitude=Double.parseDouble(element[1]);
			double longitude= Double.parseDouble(element[2]);
			boolean wheelchair= Boolean.parseBoolean(element[4]);
			int greenline= Integer.parseInt(element[5]);
			int redline= Integer.parseInt(element[6]);
			int blueline= Integer.parseInt(element[7]);
			int brownline= Integer.parseInt(element[8]);
			int purpleline= Integer.parseInt(element[9]);
			int pinkline= Integer.parseInt(element[10]);
			int orangeline= Integer.parseInt(element[11]);
			int yellowline= Integer.parseInt(element[12]);
			
			CtaStation temp=new CtaStation(element[0], element[3], wheelchair, redline, greenline, blueline, brownline, purpleline,
					pinkline, orangeline, yellowline, latitude,longitude);
			station.add(temp);
		}
	}
	
	//saves arraylist into file
	public static void saveFile(ArrayList<CtaStation> station){
		
		BufferedWriter bw= null;
		FileWriter fw= null;
		
		try{
			
			fw = new FileWriter("CTAStops.csv");
			bw = new BufferedWriter(fw);
			
			bw.write("Name,Latitude,Longitude,Location,Wheelchair,Red:33,Green:26,Blue:33,Brown:27,Purple:27,Pink:22,Orange:16,Yellow:3\n");
			bw.flush();
			bw.write("Null,Null,Null,Null,Null,Null,Null,Null,Merchandise Mart,Merchandise Mart,Clinton,Roosevelt,Null\n");
			bw.flush();
			for(int i=0;i<station.size();i++){
				bw.write(station.get(i).toString()+"\n");
				bw.flush();
			}
		} catch (IOException e){
			
			e.printStackTrace();
		}
		
		finally{
			try{
				if (bw!=null){
					bw.close();
				}
				if (fw!=null){
					fw.close();
				}
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
}
