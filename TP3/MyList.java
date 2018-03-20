import javax.print.attribute.standard.PrinterLocation;

public class MyList {

	private Element head;

	//Constructeur
	public MyList() {
		this.head = null;
	}

	public Element getHead() {
		return head;
	}

	public void setHead(Element head) {
		this.head = head;
	}
	
	public boolean IsEmpty() {
		if (this.head==null) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		String s = new String();
		if (this.IsEmpty()==false) {
			Element courant = this.head;
			while (courant!=null) {
				s = s + "-> (" + courant.getKey() + ") ";
				courant=courant.getNext();
			}
		}
		return s;
	}

	public void addFirst(Element e) {
		e.setNext(this.head);
		this.head=e;
	}

	public Element removeFirst() {
		if (this.IsEmpty()==true) {
			return null;
		} else {
			Element elementDeleted = this.head;
			this.head = this.head.getNext();
			elementDeleted.setNext(null);
			return elementDeleted;
		}
	}

	public void addLast(Element e) {
		if (this.IsEmpty()) {
			this.head = e;
		} else {
			Element elementCourant = this.head;
			while (elementCourant.getNext()!=null) {
				elementCourant = elementCourant.getNext();
			}
			elementCourant.setNext(e);
		}
	}

	public Element removeLast() {
		if (this.IsEmpty()) {
			return null;
		} else {
			Element elementCourant = this.head;
			while (elementCourant.getNext().getNext()!=null) {
				elementCourant = elementCourant.getNext();
			}
			Element elementDeleted = elementCourant.getNext();
			elementDeleted.setNext(null);
			elementCourant.setNext(null);
			return elementDeleted;
		}
	}

	public Element findKey(int key) {
		if (this.IsEmpty()) {
			return null;
		} else {
			Element elementCourant = this.head;
			boolean res = false;
			while (elementCourant != null && res != false) {
				if (elementCourant.getKey() == key) {
					res = true;
				} else {
					elementCourant = elementCourant.getNext();
				}
			}
			return elementCourant;
		}
	}
	
}