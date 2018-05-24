package lifeform;
/*
 * Test cases for LifeForm class
 * Author: Rob Miles
 */

import static org.junit.Assert.*;
import org.junit.Test;

import lifeform.LifeForm;

/**
 * Tests the functionality provided by the LifeForm class
 *
 */
public class TestLifeForm
{
	/**
	 * When a LifeForm is created, it should know its name and how many life points
	 * it has.
	 */
	@Test
	public void testInitialization()
	{
		LifeForm entity;
		entity = new LifeForm("Bob", 40);
		assertEquals("Bob", entity.getName());
		assertEquals(40, entity.getCurrentLifePoints());
	}

	/*
	 * Testing for takeHit method, note LifeForm cannot go below 0 life points
	 */
	@Test
	public void testTakeHit()
	{
		
		LifeForm entity; 
		entity = new LifeForm("Bob", 40);
		entity.takeHit(5);
		assertEquals(35, entity.getCurrentLifePoints());

	}
}