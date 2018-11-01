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
		this.fromAccountID = fromaccountID;
		this.walletID = walletID;
		this.status = "active";
		
	}
	
	/* (non-Javadoc)
	 * @see Transaction#getStatus()
	 */
	@Override
	public String getStatus() {
		return status;
	}
	/* (non-Javadoc)
	 * @see Transaction#setStatus(java.lang.String)
	 */
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	/* (non-Javadoc)
	 * @see Transaction#getWalletID()
	 */
	@Override
	public int getWalletID() {
		return walletID;
	}
	/* (non-Javadoc)
	 * @see Transaction#setWalletID(int)
	 */
	@Override
	public void setWalletID(int walletID) {
		this.walletID = walletID;
	}
	/* (non-Javadoc)
	 * @see Transaction#getToAccountID()
	 */
	@Override
	public int getToAccountID() {
		return toAccountID;
	}
	/* (non-Javadoc)
	 * @see Transaction#setToAccountID(int)
	 */
	@Override
	public void setToAccountID(int toAccountID) {
		this.toAccountID = toAccountID;
	}
	/* (non-Javadoc)
	 * @see Transaction#getFromAccountID()
	 */
	@Override
	public int getFromAccountID() {
		return fromAccountID;
	}
	/* (non-Javadoc)
	 * @see Transaction#setFromAccountID(int)
	 */
	@Override
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
