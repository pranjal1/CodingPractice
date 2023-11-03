package sumOfElementsThotIT;

public class CustomObject {
	private int value;
	private int idx;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public CustomObject(int value, int idx) {
		super();
		this.value = value;
		this.idx = idx;
	}
	
}
