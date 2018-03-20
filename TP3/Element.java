import javax.print.attribute.standard.PrinterLocation;

public class Element {

	private int key;
	private Element next;
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public Element getNext() {
		return next;
	}
	public void setNext(Element next) {
		this.next = next;
	}
	
	public String toString() {
		String s = "";
		s = s + "(" + this.key + ")";
		return s;
	}

	public Element(int key){
		this.key = key;
	}
	
}