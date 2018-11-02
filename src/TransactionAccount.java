import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class TransactionAccount implements Account {

	private String name;
	private static final AtomicInteger TransactionAccountCounter = new AtomicInteger();
	private int transactionAccountID;
	private double balance;
	private double initialBalance;
	private int walletID;

	List<TransactionStatement> transactions = new ArrayList<TransactionStatement>();



	/* (non-Javadoc)
	 * @see Account#getTransactions()
	 */
	@Override
	public List<TransactionStatement> getTransactions() {
		return transactions;
	}

	public TransactionAccount(String name, double balance, int walletID) {
		this.name = name;
		this.walletID = walletID;
		this.transactionAccountID = TransactionAccountCounter.incrementAndGet();
		if (balance < 0) {
			balance = 0;
			initialBalance = 0;
		} else {
			this.balance = balance;
			initialBalance = balance;
		}
	}
	
	/* (non-Javadoc)
	 * @see Account#getWalletID()
	 */
	@Override
	public int getWalletID() {
		return walletID;
	}

	/* (non-Javadoc)
	 * @see Account#setWalletID(int)
	 */
	@Override
	public void setWalletID(int walletID) {
		this.walletID = walletID;
	}


	/* (non-Javadoc)
	 * @see Account#print()
	 */
	@Override
	public void print() {

		System.out.println("Account Name = " + name);
		System.out.println("Account Number = " + transactionAccountID);
		System.out.println("Account Balance = " + balance);
	}

	/* (non-Javadoc)
	 * @see Account#setName(java.lang.String)
	 */
	@Override
	public void setName(String owner) {
		name = owner;
	}

	/* (non-Javadoc)
	 * @see Account#setTransactionAccountID(int)
	 */
	@Override
	public void setTransactionAccountID(int accountNumber) {
		transactionAccountID = accountNumber;
	}

	/* (non-Javadoc)
	 * @see Account#setBalance(double)
	 */
	@Override
	public void setBalance(double accountBalance) {
		if (accountBalance < 0) {
			balance = 0;
		} else {
			balance = accountBalance;
		}
	}

	/* (non-Javadoc)
	 * @see Account#deposit(double)
	 */
	@Override
	public synchronized boolean deposit(double depositAmount) {
		if (depositAmount < 0) {
			System.out.println("Error: Deposit amount must be a positive number.");
			return false;
		} else {
			balance = balance + depositAmount;
			transactions.add(new TransactionStatement(depositAmount, "deposit",this.transactionAccountID,this.walletID));
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see Account#withdraw(double)
	 */
	@Override
	public synchronized boolean withdraw(double withdrawalAmount) {
		if (withdrawalAmount < 0) {
			System.out.println("Error: Withdrawl amount must be a positive number.");
			return false;
		} else if (withdrawalAmount > balance) {
			System.out.println("No Sufficient fund to complete the transaction");
            throw new InsufficientFundException(
                    "Current balance " + balance+" is less than requested amount " + withdrawalAmount);
		}

		else {
			balance = balance - withdrawalAmount;
			transactions.add(new TransactionStatement(withdrawalAmount, "withdraw",this.transactionAccountID, this.walletID));
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see Account#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see Account#getTransactionAccountID()
	 */
	@Override
	public int getTransactionAccountID() {
		return transactionAccountID;
	}

	/* (non-Javadoc)
	 * @see Account#getBalance()
	 */
	@Override
	public double getBalance() {
		return balance;
	}

	/* (non-Javadoc)
	 * @see Account#verifyBalance()
	 */
	@Override
	public boolean verifyBalance() {
		double verifiedBalance = initialBalance;
		for (Transaction t : transactions) {
			if (t.getType() == "withdraw") {
				verifiedBalance -= t.getAmount();
			} else {
				verifiedBalance += t.getAmount();
			}
		}
		if (verifiedBalance == balance) {
			return true;
		} else {
			balance  = verifiedBalance;
			return false;
		}

	}

}
