import java.util.List;

interface Account {

	List<TransactionStatement> getTransactions();

	int getWalletID();

	void setWalletID(int walletID);

	void print();

	void setName(String owner);

	void setTransactionAccountID(int accountNumber);

	void setBalance(double accountBalance);

	boolean deposit(double depositAmount);

	boolean withdraw(double withdrawalAmount);

	String getName();

	int getTransactionAccountID();

	double getBalance();

	boolean verifyBalance();

}