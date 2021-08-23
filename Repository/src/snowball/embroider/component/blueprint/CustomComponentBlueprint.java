package snowball.embroider.component.blueprint;

import componentArchitecture.Component;
import componentArchitecture.ComponentBlueprint;
import speciesInformation.SpeciesInfoLine;
import speciesInformation.SpeciesInfoType;

import java.util.List;
import java.util.Map;

public class CustomComponentBlueprint extends ComponentBlueprint implements IBlueprint {
	List<Object> list;
	
	private final CustomComponent comp;
	
	protected CustomComponentBlueprint(List<Object> list, CustomComponentType type, CustomComponent comp) {
		super(type);
		
		this.comp = comp;
		this.list = list;
	}
	
	@Override
	public final Component createInstance() {
		return comp;
	}
	
	@Override
	public final void delete() {}
	
	@Override
	public final void getInfo(Map<SpeciesInfoType, List<SpeciesInfoLine>> info) {
		this.loadInfo(info, list);
	}
	
	@Override
	public void loadInfo(Map<SpeciesInfoType, List<SpeciesInfoLine>> info, List<Object> list) {}
}
