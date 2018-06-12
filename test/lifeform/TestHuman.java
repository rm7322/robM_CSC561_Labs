/**
 * Testing for Human class
 * 
 * @Author:  Rob Miles
 */
package lifeform;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestHuman
{

	/*
	 * test default attack strength = 5
	 */
	@Test
	public void testDefaultAttackStrength()
	{
		Human entity = new Human(0, "Fred", 50);
		assertEquals(5, entity.getAttackStrength());
	}

	/*
	 * armor absorbs all damage using takeHit() when damage < armor
	 */
	@Test
	public void testArmorProtection() throws Exception
	{

		Human entity2 = new Human(11, "Dilbert", 50);
		Alien alien2 = new Alien("Commander Terrible", 100);
		// alien damage is 10, mount attack calls takeHit()
		alien2.mountAttack(entity2);
		assertEquals(50, entity2.getLifePoints());
	}

	/*
	 * armor absorbs partial damage using takeHit() when damage > armor
	 */
	@Test
	public void testArmorProtectionPartial() throws Exception
	{
		Human entity = new Human(7, "Dilbert", 50);
		Alien alien = new Alien("Commander Terrible", 100);
		// alien damage is 10, mount attack calls takeHit()
		alien.mountAttack(entity);
		assertEquals(47, entity.getLifePoints());

	}

	/*
	 * armor absorbs partial damage using takeHit() when damage > armor
	 */
	@Test
	public void testArmorProtectionSame() throws Exception
	{
		Human entity = new Human(10, "Dilbert", 50);
		Alien alien = new Alien("Commander Terrible", 100);
		// alien damage is 10, mount attack calls takeHit()
		alien.mountAttack(entity);
		assertEquals(50, entity.getLifePoints());
	}

	/*
	 * armor absorbs all damage using takeHit() when damage < armor
	 */
	@Test
	public void testArmorProtectionBoundary1() throws Exception
	{
		Human entity = new Human(7, "Dilbert", 10);
		Alien alien = new Alien("Commander Terrible", 100);
		alien.mountAttack(entity);
		alien.mountAttack(entity);
		alien.mountAttack(entity);
		alien.mountAttack(entity);
		alien.mountAttack(entity);
		alien.mountAttack(entity);
		assertEquals(0, entity.getLifePoints());
	}

	/*******************************************************************
	 * Start Section for Strategy Pattern Tests
	 * ******************************************************************
	 */
	/*
	 * test we can construct a Human with initial (armor, a name, and life points)
	 */
	@Test
	public void testInitalizationHuman()
	{
		Human entity;
		entity = new Human(0, "Fred", 50);
		assertEquals("Fred", entity.getName());
		assertEquals(50, entity.getLifePoints());
		assertEquals(0, entity.getArmorPoints());
	}

	/*
	 * getArmorPoints setArmorPoints values < 0 equal to 0
	 */
	@Test
	public void testSetArmor()
	{
		Human entity;
		entity = new Human(0, "Rudolph", 50);

		entity.setArmorPoints(5);
		assertEquals(5, entity.getArmorPoints());

		entity.setArmorPoints(-3);
		assertEquals(0, entity.getArmorPoints());
	}

}
