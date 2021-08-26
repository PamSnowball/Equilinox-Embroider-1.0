package com.snowball.embroider.component.architecture.special;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.Entity;
import com.snowball.embroider.util.Vector;
import com.snowball.embroider.util.Utils;

import java.util.Collection;
import java.util.Collections;

public class Wood extends NativeComponent {

	/**
	 * Bark Chance Guide <p>
	 * Umbrella trees have a bark chance of 10% <br>
	 * Large trees and spindle trees have a bark chance of 25% <br>
	 * Dead trees, ash trees, spiral trees, forest trees and oak have a bark chance of 30% <br>
	 * Nut trees have a bark chance of 34% <br>
	 * Canopy trees, juniper trees, apple trees and acer trees have a bark chance of 40% <br>
	 * Flower Tree have a bark chance of 45% <br>
	 * Autumnal trees, eucalyptus, pink and orange trees have a bark chance of 50% <br>
	 * Mango Trees have a bark chance of 55% <br>
	 * Desert trees and banana trees have a bark chance of 60% <br>
	 * Swamp trees have a bark chance of 68% <br>
	 * Elm trees, red maple trees, palm trees, tall trees, witchwood and willows have a bark chance of 70% <br>
	 * Fir trees, pagoda trees, spruce trees and birch have a bark chance of 75% <br>
	 * Pine trees and vine trees have a bark chance of 80% <br>
	 * Ficus trees have a bark chance of 85% <br>
	 */
	float bark;

	/**
	 * Cut Time guide: <p>
	 * WitchWood have a cut time of 2.7; <br>
	 * Cedar trees, birch and eucalyptus have a cut time of 3; <br>
	 * Red maple trees and tall trees have a cut time of 3.5; <br>
	 * Orange trees have a cut time of 3.6; <br>
	 * Elm trees, banana trees, umbrella trees and willow trees have a cut Time of 4; <br>
	 * Sycamore trees have a cut time of 4.2; <br>
	 * Nut trees, pagoda trees, ash trees, pink trees and oak have a cut time of 4.5; <br>
	 * Ficus trees, apple and mango trees, spruce trees and firs have a cut time of 5; <br>
	 * Vine trees have a cut time of 5.2; <br>
	 * Flower trees and spiral trees have a cut time of 5.5; <br>
	 * Palm trees have a cut time of 5.8; <br>
	 * Canopy trees have a cut time of 6; <br>
	 * Swamp trees have a cut time of 6.3; <br>
	 * Juniper trees have a cut time of 6.5; <br>
	 * Desert trees and pines have a cut time of 7; <br>
	 * Acer trees have a cut time of 7.5; <br>
	 * Autumnal trees have a cut time of 8.5; <br>
	 * Large trees have a cut time of 9; <br>
	 * Dead trees have a cut time of 15; 
	 */
	
	float time;

	Vector colour;

	/**
	 * Constructs the WOOD component which is used by trees to set barks dropped, which are used by beavers.
	 * 
	 * @param cutTime time for the tree to be cut
	 * @param barkFactor bark dropping chance
	 * @param colour colour of bark
	 */
	public Wood(float cutTime, float barkFactor, Vector colour) {
		this.bark = barkFactor;
		this.colour = colour;
		this.time = cutTime;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton(Utils.value("cutTime", time, "barkFactor", bark, "colour", colour.toString()));
	}

	@Override
	public int getId() {
		return 29;
	}
}
