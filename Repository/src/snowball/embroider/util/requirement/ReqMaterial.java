package snowball.embroider.util.requirement;

import snowball.embroider.enumerator.EnumColours;
import snowball.embroider.util.component.IRequirement;

import java.util.Collection;
import java.util.Collections;

public class ReqMaterial implements IRequirement {
	EnumColours colour;
	
	public ReqMaterial(EnumColours colour) {
		this.colour = colour;
	}

	@Override
	public Collection<String> requirement() {
		return Collections.singleton("MATERIAL;col;" + colour);
	}
}
