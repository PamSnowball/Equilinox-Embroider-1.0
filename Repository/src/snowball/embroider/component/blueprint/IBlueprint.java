package snowball.embroider.component.blueprint;

import speciesInformation.SpeciesInfoLine;
import speciesInformation.SpeciesInfoType;

import java.util.List;
import java.util.Map;

public interface IBlueprint {
	void loadInfo(Map<SpeciesInfoType, List<SpeciesInfoLine>> info, List<Object> list);
}
