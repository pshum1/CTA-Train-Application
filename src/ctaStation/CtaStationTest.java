package ctaStation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CtaStationTest {

	@Test
	public void removeStationtest() {
		ArrayList<CtaStation> station=new ArrayList<CtaStation>();
		CtaStation temp=new CtaStation();
		station.add(temp);
		station.add(temp);
		assertEquals(2,station.size());
		station.remove(0);
		assertEquals(1,station.size());
	}
	
	public void addStationtest(){
		ArrayList<CtaStation> station=new ArrayList<CtaStation>();
		CtaStation temp=new CtaStation();
		station.add(temp);
		assertEquals(1,station.size());
		station.add(temp);
		assertEquals(2,station.size());
	}

	public void insertStation(){
		ArrayList<CtaStation> station=new ArrayList<CtaStation>();
		CtaStation temp=new CtaStation();
		station.add(temp);
		station.add(temp);
		CtaStation insert=new CtaStation("blueline","elevated",true,0,0,0,0,0,0,0,0,1.0,2.0);
		station.add(1,insert);
		assertNotEquals("redline",station.get(1));
		assertEquals("blueline",station.get(1));
		
	}
}

	

