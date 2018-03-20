public class Main {

	public static void main(String[] args) {
		MyList myList = new MyList();
		System.out.println(myList.toString());
		myList.addLast(new Element(3));
		System.out.println(myList.toString());
		myList.addFirst(new Element(1));
		System.out.println(myList.toString());
		myList.addLast(new Element(4));
		System.out.println(myList.toString());
		Element removed = myList.removeFirst();
		System.out.println(myList.toString());
		myList.addLast(removed);
		System.out.println(myList.toString());
	}
}