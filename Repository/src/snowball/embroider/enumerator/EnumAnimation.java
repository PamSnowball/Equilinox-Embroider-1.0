package snowball.embroider.enumerator;

public enum EnumAnimation{ 
	COMMON(0),
	THROWING(1),
	DIGGING(2),
	DIVING(3),
	INSTANT(4);
	
	private int id;
	
	EnumAnimation(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}