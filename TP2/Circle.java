public class Circle {

	private double radius;
	private String color;

	//Constructors

	public Circle() {
		radius=1.0;
		color="red";
	}

	public Circle(double r) {
		this.radius=r;
		color="red";
	}

	public Circle(double r, String c) {
		this.radius=r;
		this.color=c;
	}

	//Getters

	public double getRadius() {
		return this.radius;
	}

	public double getArea() {
		return this.radius*this.radius*Math.PI;
	}

	public double getCircumference() {
		return 2.0*this.radius*Math.PI;
	}

	public String getColor() {
		return this.color;
	}

	//Setters

	public void setRadius(double r) {
		this.radius=r;
	}

	public void setColor(String c) {
		this.color=c;
	}

	//toString()

	public String toString() {
		return "[radius=" + this.getRadius() + ", color=" + this.getColor() +"]";
	}


}