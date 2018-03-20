class Account {

	private int accountNumber;
	private double balance = 0.0;

	//Constructors

	public Account(int a, double b) {
		this.accountNumber=a;
		this.balance=b;
	}

	public Account(int a) {
		this.accountNumber=a;
	}

	//Getters

	public int getAccountNumber() {
		return this.accountNumber;
	}

	public double getBalance() {
		return this.balance;
	}

	//Setters

	public void setAccountNumber(int a) {
		this.accountNumber=a;
	}

	public void setBalance(double b) {
		this.balance=b;
	}

	//Others

	public void credit(double amount) {
		this.balance=this.balance+amount;
	}

	public void debit(double amount) {
		if (this.balance >= amount) {
			this.balance -= amount;
		}
		else {
			System.out.println("amount withdrawn exceeds the current balance!");
		}
	}

	//toString()

	public String toString() {
		return "A/C no:" + this.getAccountNumber() +", Balance=$" + this.getBalance();
	}



}