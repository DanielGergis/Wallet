import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface WalletLibraryInterface {

	List<TransactionStatement> getTransactions();

	Map<Integer, Account> getTransactionAccounts();

	int getWalletID();

	String getHolderName();

	void setHolderName(String holderName);

	String getPassword();

	void setPassword(String password);

	int getHolderID();

	void setHolderID(int holderID);

	String getHolderAddress();

	void setHolderAddress(String holderAddress);

	Timestamp getOpenDate();

	double getTotalBalance();

	int getNoOfTransactionAccounts();

	double calculateTotalBalance();

	int AddTransactionAccount(String name, double balance, String password);

	void AddTransactionAccount(Account t, String password);

	boolean withdraw(double amount, Account t, String password);

	boolean withdraw(double amount, int accountID, String password);

	boolean deposit(double amount, Account t, String password);

	boolean deposit(double amount, int accountID, String password);

	boolean TransferToAccount(int fromAccountID, int toAccountID, double amount, String password);

	void print();

	List<Transaction> getTransactions(int accountID, int numOfTransactions, String password);

	boolean transferToWallet(Wallet toWallet, int toAccountID, int fromAccountID, double amount, String password);

	boolean verifyBalance(String password);

	boolean compareTo(Wallet w);

	Account getAccount(int id, String password);

	boolean transactionReversal(int TransactionID, String password);

}