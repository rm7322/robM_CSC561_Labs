/**
 * Scope attachment will decorate (wrap and modify) the damage done by the weapon that it's wrapping.
 * 
 * Note; that weapon could be another attachment that wraps the initial weapon.  
 * 
 * @author Rob Miles, Chandana G
 */

package weapon;

import environment.Range;

public class Scope extends Attachment
{

	Scope(Weapon w)
	{
		super(w);
	}

	/**
	 * Determine and return the damage to decorate the weapon damage with
	 */
	public int damage()
	{

		int x = weapon.getMaxRange() - Range.distance;
		float y = 1 + ((float)x / weapon.getMaxRange());
		float z = weapon.damage() * y;
		return (int) z;
	}




}
