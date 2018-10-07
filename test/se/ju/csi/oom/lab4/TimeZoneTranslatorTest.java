package se.ju.csi.oom.lab4;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TimeZoneTranslatorTest {

	@Before
	public void setUp() throws Exception {	}

	@Test
	public void testShiftTimeZone() {
		
		DateTime dateTimeTestObject = new DateTime(1993, 11, 29, 13, 00, 00);
				
		dateTimeTestObject = TimeZoneTranslator.shiftTimeZone(dateTimeTestObject, 0, 1);
		
		assertEquals("They are not the same, ", "1993-11-29 14:00:00", dateTimeTestObject.toString());	
	}
	
	
	@Test
	public void testShiftTimeZone2() {
		
		DateTime testObject = new DateTime(2016, 01, 01, 06, 00, 00);
		
		testObject = TimeZoneTranslator.shiftTimeZone(testObject, 1, -8);
		
		assertEquals("They are not the same, ", "2015-12-31 21:00:00", testObject.toString());
		
	}

	@Test
	public void testShiftEventTimeZone() {
		
		DateTime dateTimeObject = new DateTime(1993, 11, 29, 12, 00, 00);
		DateTime dateTimeObject2 = new DateTime(1993, 11, 29, 13, 00, 00);
		Person joakim = new Person("Joakim");
		Person anton = new Person("Anton");
		Place party = new Place("Partyplejset", 10.1, 20.2, 30.3);
		
		Event eventTestObject = new Event("Party", 
				dateTimeObject, 
				dateTimeObject2, 
				new HashSet<>(Arrays.asList(joakim, anton)), 
				party
				);
		
		eventTestObject = TimeZoneTranslator.shiftEventTimeZone(eventTestObject, TimeZone.CENTRAL_EUROPEAN_TIME, TimeZone.EASTERN_EUROPEAN_TIME);
		
		Event expectedEventInformation = new Event("Party", 
				new DateTime(1993, 11, 29, 13, 00, 00),
				new DateTime(1993, 11, 29, 14, 00, 00),
				new HashSet<>(Arrays.asList(joakim, anton)),
				party				
				);
		
		assertEquals("They are not the same", expectedEventInformation.toString(), eventTestObject.toString());	
	}
}
