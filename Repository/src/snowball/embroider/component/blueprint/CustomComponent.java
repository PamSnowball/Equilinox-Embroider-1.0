package snowball.embroider.component.blueprint;

import componentArchitecture.Action;
import componentArchitecture.Component;
import componentArchitecture.ComponentBundle;
import entityInfoGui.PopUpInfoGui;
import utils.BinaryReader;
import utils.BinaryWriter;

import java.io.IOException;
import java.util.List;

public abstract class CustomComponent extends Component {
	protected CustomComponent(CustomComponentBlueprint blueprint) {
		super(blueprint);
	}
	
	public abstract void load(ComponentBundle bundle, BinaryReader reader) throws Exception;
	public abstract void export(BinaryWriter writer) throws IOException;
	public abstract void getStatusInfo(List<PopUpInfoGui> info);
	public abstract void getActions(List<Action> action);
	public abstract void create(ComponentBundle bundle);
}
