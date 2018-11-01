import java.util.concurrent.atomic.AtomicInteger;

class TransactionStatement implements Transaction {
	private int transactionID;
	private String type;
	private double amount;
	private int toAccountID;
	private int fromAccountID;
	private int walletID;
	private String status;

	private static final AtomicInteger TransactionCounter = new AtomicInteger();
	
	public TransactionStatement(double amount, String type, int accountID,int walletID) {
		this.transactionID= TransactionCounter.getAndIncrement();
		this.amount= amount;
		this.type = type;
		this.toAccountID = accountID;
		this.walletID = walletID;
		this.status = "active";
		
	}
	public TransactionStatement(double amount, String type, int fromaccountID,int toAccountID,int walletID) {
		this.transactionID= TransactionCounter.getAndIncrement();
		this.amount= amount;
		this.type = type;
		this.toAccountID = toAccountID;
		this.fromAccountID = fromAccountID;
		this.walletID = walletID;
		this.status = "active";
		
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public int getWalletID() {
		return walletID;
	}
	public void setWalletID(int walletID) {
		this.walletID = walletID;
	}
	public int getToAccountID() {
		return toAccountID;
	}
	public void setToAccountID(int toAccountID) {
		this.toAccountID = toAccountID;
	}
	public int getFromAccountID() {
		return fromAccountID;
	}
	public void setFromAccountID(int fromAccountID) {
		this.fromAccountID = fromAccountID;
	}
	/* (non-Javadoc)
	 * @see Transaction#getTransactionID()
	 */
	@Override
	public int getTransactionID() {
		return transactionID;
	}
	/* (non-Javadoc)
	 * @see Transaction#setTransactionID(int)
	 */
	@Override
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	/* (non-Javadoc)
	 * @see Transaction#getType()
	 */
	@Override
	public String getType() {
		return type;
	}
	/* (non-Javadoc)
	 * @see Transaction#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;
	}
	/* (non-Javadoc)
	 * @see Transaction#getAmount()
	 */
	@Override
	public double getAmount() {
		return amount;
	}
	/* (non-Javadoc)
	 * @see Transaction#setAmount(double)
	 */
	@Override
	public void setAmount(double amount) {
		this.amount = amount;
	}
		

}
