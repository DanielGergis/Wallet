
interface Transaction {

	String getStatus();

	void setStatus(String status);

	int getWalletID();

	void setWalletID(int walletID);

	int getToAccountID();

	void setToAccountID(int toAccountID);

	int getFromAccountID();

	void setFromAccountID(int fromAccountID);

	int getTransactionID();

	void setTransactionID(int transactionID);

	String getType();

	void setType(String type);

	double getAmount();

	void setAmount(double amount);

}