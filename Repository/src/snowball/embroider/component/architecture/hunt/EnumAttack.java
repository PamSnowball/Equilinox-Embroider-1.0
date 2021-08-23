package snowball.embroider.component.architecture.hunt;

public enum EnumAttack {
	LUNGE(0),
	STING(1);

	private int id;
	
	EnumAttack(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
