package ui;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import exceptions.RException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

public class TestGameDisplay
{
	@Before // clear and setup the world before each test
	public void testSetupWorld() throws RException
	{
		Environment.resetWorld();
		Environment.createWorld(10, 20);
	}

	@Test
	public void testCreateHumansandAliens() throws InterruptedException, RException
	{
		Environment theWorld = Environment.getWorld();

		LifeForm entity = new Human(2, "mock entity", 0);
		theWorld.addLifeForm(3, 2, entity);
		entity.rotate("East");
		theWorld.setActivePlayer(entity);

		LifeForm human = new Human(0, "eddie cheddar", 20);
		theWorld.addLifeForm(0, 0, human);
		human.rotate("West");

		LifeForm alien = new Alien("zaphod beeblebrox", 20);
		theWorld.addLifeForm(1, 1, alien);

		GameDisplay gui = new GameDisplay();
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null,
				"Create Cell Image Icon Correct For\nHuman facing West(0,0)\nand Alien facing North(1,1)\nand MockEntity facing East(3,2)\nDoes it look right?"));
	}
	@Test
	public void testCreateArmedAlien() throws InterruptedException, RException
	{
		Environment theWorld = Environment.getWorld();

		LifeForm alien = new Alien("zaphod beeblebrox", 20);
		theWorld.addLifeForm(1, 1, alien);

		Weapon pistol = new Pistol();
		alien.pickUpWeapon(pistol);

		GameDisplay gui = new GameDisplay();
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null,
				"Create Cell Image Icon Correct For\nArmed Alien facing North(1,1) \nDoes it look right?"));
	}
	@Test
	public void testCreateLegend() throws InterruptedException, RException
	{
		GameDisplay gui = new GameDisplay();
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null,
				"Do you see a legend on the right\nIt should display Aliens, Humans, Weapons (3), Armed LifeForm.\nDoes it look right?"));
	}



	@Test
	public void testCreateWeapons() throws InterruptedException, RException
	{
		Environment theWorld = Environment.getWorld();

		Weapon pistol = new Pistol();
		theWorld.addWeapon(7, 14, pistol);
		Weapon chain = new ChainGun();
		theWorld.addWeapon(7, 15, chain);
		Weapon cannon = new PlasmaCannon();
		theWorld.addWeapon(7, 16, cannon);
		Weapon pistol2 = new Pistol();
		theWorld.addWeapon(0, 14, pistol2);
		Weapon chain2 = new ChainGun();
		theWorld.addWeapon(0, 14, chain2);
		Weapon cannon2 = new PlasmaCannon();
		theWorld.addWeapon(2, 16, cannon2);
		Weapon pistol3 = new Pistol();
		theWorld.addWeapon(2, 16, pistol3);
		Weapon chain3 = new ChainGun();
		theWorld.addWeapon(1, 15, chain3);
		Weapon cannon3 = new PlasmaCannon();
		theWorld.addWeapon(1, 15, cannon3);

		GameDisplay gui = new GameDisplay();
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null,
				"Do you see all the possible combinations of weapons in various cells\nDoes it look right?"));
	}

	@Test
	public void testCreateAll() throws InterruptedException, RException
	{
		Environment theWorld = Environment.getWorld();

		LifeForm entity = new Human(2, "mock entity", 0);
		theWorld.addLifeForm(3, 2, entity);
		entity.rotate("East");
		theWorld.setActivePlayer(entity);

		LifeForm human = new Human(0, "eddie cheddar", 20);
		theWorld.addLifeForm(0, 0, human);
		human.rotate("West");

		LifeForm alien = new Alien("zaphod beeblebrox", 20);
		theWorld.addLifeForm(1, 1, alien);

		Weapon pistol = new Pistol();
		alien.pickUpWeapon(pistol);
		theWorld.addWeapon(7, 14, pistol);
		Weapon chain = new ChainGun();
		theWorld.addWeapon(7, 15, chain);
		Weapon cannon = new PlasmaCannon();
		theWorld.addWeapon(7, 16, cannon);

		Weapon pistol2 = new Pistol();
		alien.pickUpWeapon(pistol);
		theWorld.addWeapon(0, 14, pistol2);
		Weapon chain2 = new ChainGun();
		theWorld.addWeapon(0, 14, chain2);
		Weapon cannon2 = new PlasmaCannon();
		theWorld.addWeapon(2, 16, cannon2);
		Weapon pistol3 = new Pistol();
		alien.pickUpWeapon(pistol);
		theWorld.addWeapon(2, 16, pistol3);
		Weapon chain3 = new ChainGun();
		theWorld.addWeapon(1, 15, chain3);
		Weapon cannon3 = new PlasmaCannon();
		theWorld.addWeapon(1, 15, cannon3);

		GameDisplay gui = new GameDisplay();
		// Thread.sleep(10000);
		// assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null,
		// "Create Cell Image Icon Correct For\nHuman, East(0,0) and Alien, North,
		// Armed(1,1)\nDoes it look right?"));

		// assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, "Map
		// legend Displays Correctly\nAlien(Green Triangle)\nDoes it look right?"));
	}

}
