/**
 * A Cell that can hold a LifeForm. 
 * 
 * @Author: Rob Miles (partly)
 *
 */

package environment;

import exceptions.RException;
import lifeform.LifeForm;
import weapon.Weapon;

public class Cell
{

	protected LifeForm identity;
	protected Weapon weapon1;
	protected Weapon weapon2;

	/**
	 * @return the LifeForm in this Cell.
	 */
	public LifeForm getLifeForm()
	{
		return identity;
	}

	/**
	 * Tries to add the LifeForm to the Cell. Will not add if a LifeForm is already
	 * present.
	 * 
	 * @param entity
	 *            - life form instance to add to cell
	 * 
	 * @return true - if the LifeForm was added to the Cell, false otherwise.
	 */
	public boolean addLifeForm(LifeForm entity)
	{
		if (identity == null)
		{
			identity = entity;
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * Removes the LifeForm in the Cell. Returns the LifeForm removed, null if none
	 * present.
	 * 
	 * @return identity of life form removed else null
	 */
	public LifeForm removeLifeForm()
	{
		if (identity != null)
		{
			LifeForm id = identity;
			identity = null;
			return id;
		}
		else
		{
			return null;
		}

	}

	public void addWeapon(Weapon w) throws RException
	{
		if (weapon1 == null)
		{
			weapon1 = w;
		}
		else if (weapon2 == null)
		{
			weapon2 = w;
		}
		else if ((weapon1 != null) && (weapon2 != null))
		{
			throw new RException("this cell is full of weapons already");
		}

	}

	public void removeWeapon(Weapon w) throws RException
	{
		if (weapon1 == w)
		{
			weapon1 = null;
		}
		else if (weapon2 == w)
		{
			weapon2 = null;
		}
		else
		{
			throw new RException("Weapon can't be found; therefore can't be removed");
		}

	}
}
