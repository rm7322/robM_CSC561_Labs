/**
 * @author Rob Miles
 */

package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.RException;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Pistol;
import weapon.Weapon;

public class TestDeadState
{

	@Test
	public void testinitialize()
	{
		LifeForm bill = new Human(22, "bill", 22);
		ActionState as = new DeadState(bill, null);
		assertEquals(as.myLF, bill);
	}

	@Test  //respawns, drops weapon  and goes to a new spot
	public void testRespawnWithGun() throws EnvironmentException, RException
	{
		Environment.resetWorld();
		Environment.createWorld(20, 20);
		Environment theWorld = Environment.getWorld();
		Weapon w = new Pistol();
		LifeForm bill = new Human(0, "bill", 22);
		LifeForm sandra = new Human(0, "sandra", 22);
		theWorld.addLifeForm(2, 2, bill);
		theWorld.addLifeForm(3, 3, sandra);
		// theWorld.addWeapon(2, 2, w);
		// TODO i think this needs to be done so that the weapon is removed from the
		// cell
		bill.pickUpWeapon(w);

		AI myAi = new AI(bill);
		ActionState as = myAi.deadState;
		sandra.mountAttack(bill);
		assertEquals(17, as.myLF.getLifePoints());

		as.respawn();
		assertNull(bill.getWeapon());
		// test that the life is regenerated
		assertEquals(22, as.myLF.getLifePoints());
		// test that LF shows up in a new location
		// TODO we want a better test than this
		assertTrue((2 != theWorld.getLifeFormLocation(bill)[0]) && (theWorld.getLifeFormLocation(bill)[1] != 2));
		// test that weapon is dropped
		// I know it's supposed to pick a random spot, but we made it check to see if it
		// can drop it in place, then pick a random spot if cell is already filled with 2
		assertEquals(w, theWorld.getWeapon(2, 2));

		// test that new state is set
	}

	@Test //respawns and goes to a new spot
	public void testRespawnWithoutGun() throws EnvironmentException, RException
	{
		Environment.resetWorld();
		Environment.createWorld(20, 20);
		Environment theWorld = Environment.getWorld();
		LifeForm bill = new Human(0, "bill", 22);
		LifeForm sandra = new Human(0, "sandra", 22);
		theWorld.addLifeForm(2, 2, bill);
		theWorld.addLifeForm(3, 3, sandra);

		AI myAi = new AI(bill);
		ActionState as = myAi.deadState;
		
		// it's auto generated in AI

		sandra.mountAttack(bill);
		assertEquals(17, as.myLF.getLifePoints());

		as.respawn();
		// test that the life is regenerated
		assertEquals(22, as.myLF.getLifePoints());
		// test that LF shows up in a new location
		// TODO we want a better test than this
		assertTrue((2 != theWorld.getLifeFormLocation(bill)[0]) && (theWorld.getLifeFormLocation(bill)[1] != 2));

		// test that new state is set
		assertTrue(as.ai.getState() instanceof NoWeaponState);
	}
	
	
	@Test  //checks that ig 
	public void testEvaluate() throws RException
	{
		Environment.resetWorld();
		Environment.createWorld(5, 5);
		Environment theWorld = Environment.getWorld();

		LifeForm bill = new Human(22, "bill", 22);
		AI myAi = new AI(bill);
		ActionState as = myAi.deadState;
		theWorld.addLifeForm(3, 3, bill);
		Weapon w = new Pistol();
		bill.pickUpWeapon(w);
		// call respawn via evaluate
		as.evaluate();
		assertNull(bill.getWeapon());
		assertEquals(myAi.noWeaponState, myAi.getState());

	}
}
