import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Wallet implements WalletLibraryInterface {
	private static final AtomicInteger WalletCounter = new AtomicInteger();
	private int walletID;
	private String holderName;
	private String password;
	private int holderID;
	private String holderAddress;
	private Timestamp openDate;
	private double totalBalance;
	private int noOfTransactionAccounts = 0;

	Map<Integer, Account> transactionAccounts = new HashMap<>();

	List<TransactionStatement> transactions = new ArrayList<TransactionStatement>();

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#getTransactions()
	 */
	@Override
	public List<TransactionStatement> getTransactions() {
		return transactions;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#getTransactionAccounts()
	 */
	@Override
	public Map<Integer, Account> getTransactionAccounts() {
		return transactionAccounts;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#getWalletID()
	 */
	@Override
	public int getWalletID() {
		return walletID;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#getHolderName()
	 */
	@Override
	public String getHolderName() {
		return holderName;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#setHolderName(java.lang.String)
	 */
	@Override
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	
	/* (non-Javadoc)
	 * @see WalletLibraryInterface#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#getHolderID()
	 */
	@Override
	public int getHolderID() {
		return holderID;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#setHolderID(int)
	 */
	@Override
	public void setHolderID(int holderID) {
		this.holderID = holderID;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#getHolderAddress()
	 */
	@Override
	public String getHolderAddress() {
		return holderAddress;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#setHolderAddress(java.lang.String)
	 */
	@Override
	public void setHolderAddress(String holderAddress) {
		this.holderAddress = holderAddress;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#getOpenDate()
	 */
	@Override
	public Timestamp getOpenDate() {
		return openDate;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#getTotalBalance()
	 */
	@Override
	public double getTotalBalance() {
		return totalBalance;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#getNoOfTransactionAccounts()
	 */
	@Override
	public int getNoOfTransactionAccounts() {
		return noOfTransactionAccounts;
	}

	public static AtomicInteger getWalletcounter() {
		return WalletCounter;
	}

	public Wallet(String holderName, String address, int holderID, String password) {
		this.holderName = holderName;
		this.holderID = holderID;
		this.holderAddress = address;
		this.openDate = new Timestamp(System.currentTimeMillis());
		this.walletID = WalletCounter.incrementAndGet();
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#calculateTotalBalance()
	 */
	@Override
	public double calculateTotalBalance() {
		totalBalance = 0;
		for (Account account : transactionAccounts.values()) {
			totalBalance += account.getBalance();
		}
		return totalBalance;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#AddTransactionAccount(java.lang.String, double, java.lang.String)
	 */
	@Override
	public int AddTransactionAccount(String name, double balance, String password) {
		if (this.password == password) {
			Account account = new TransactionAccount(name, balance, this.walletID);
			transactionAccounts.put(account.getTransactionAccountID(), account);
			noOfTransactionAccounts++;
			totalBalance+=balance;
			return account.getTransactionAccountID();
		} else {
			System.out.println("Incorrect Password");
			return 0;
		}

	}
	/* (non-Javadoc)
	 * @see WalletLibraryInterface#AddTransactionAccount(Account, java.lang.String)
	 */
	@Override
	public void AddTransactionAccount(Account t,String password) {
		if (this.password == password) {
			if(t.getWalletID()==this.walletID) {
			transactionAccounts.put(t.getTransactionAccountID(), t);
			noOfTransactionAccounts++;
			}
			else {
				System.out.println("Wallet ID is not matching");
			}
		} else {
			System.out.println("Incorrect Password");
		}
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#withdraw(double, Account, java.lang.String)
	 */
	@Override
	public synchronized boolean withdraw(double amount, Account t, String password) {
		boolean result = false;
		if (this.password == password) {
			if(transactionAccounts.containsValue(t)) {
				result = t.withdraw(amount);
			transactions.add(new TransactionStatement(amount, "withdraw", t.getTransactionAccountID(), this.walletID));
			calculateTotalBalance();
			return result;
			} else {
				System.out.println("This account does not exist in this wallet");
				return result;
			}
		} else {
			System.out.println("Incorrect Password");
			return result;
		}
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#withdraw(double, int, java.lang.String)
	 */
	@Override
	public synchronized boolean withdraw(double amount, int accountID, String password) {
		boolean result = false;
		if (this.password == password) {
			result = transactionAccounts.get(accountID).withdraw(amount);
			transactions.add(new TransactionStatement(amount, "withdraw", accountID, this.walletID));
			calculateTotalBalance();
			return result;
		} else {
			System.out.println("Incorrect Password");
			return result;
		}
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#deposit(double, Account, java.lang.String)
	 */
	@Override
	public synchronized boolean deposit(double amount, Account t, String password) {
		boolean result = false;
		if (this.password == password) {
			if (transactionAccounts.containsValue(t)) {
				result = t.deposit(amount);
				transactions
						.add(new TransactionStatement(amount, "deposit", t.getTransactionAccountID(), this.walletID));
				calculateTotalBalance();
				return result;
			} else {
				System.out.println("This account does not exist in this wallet");
				return result;
			}
		} else {
			System.out.println("Incorrect Password");
			return result;
		}
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#deposit(double, int, java.lang.String)
	 */
	@Override
	public synchronized boolean deposit(double amount, int accountID, String password) {
		boolean result = false;
		if (this.password == password) {
			result = transactionAccounts.get(accountID).deposit(amount);
			transactions.add(new TransactionStatement(amount, "deposit", accountID, this.walletID));
			calculateTotalBalance();
			return result;
		} else {
			System.out.println("Incorrect Password");
			return result;
		}
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#TransferToAccount(int, int, double, java.lang.String)
	 */
	@Override
	public synchronized boolean TransferToAccount(int fromAccountID, int toAccountID, double amount, String password) {
		boolean result = false;
		if (this.password == password) {
			if (amount <= 0) {
				System.out.println("Transfered amount needs to be positive and greater than 0");
				return result;
			} else {
				if (transactionAccounts.containsKey(fromAccountID) && transactionAccounts.containsKey(toAccountID)) {
					boolean succesWithdraw = transactionAccounts.get(fromAccountID).withdraw(amount);
					if (succesWithdraw) {
						result = transactionAccounts.get(toAccountID).deposit(amount);
						transactions.add(new TransactionStatement(amount, "transfer", fromAccountID, toAccountID,
								this.walletID));
						calculateTotalBalance();
						return result;
					}
				}

			}
		} else {
			System.out.println("Incorrect Password");
			return result;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#print()
	 */
	@Override
	public void print() {
		System.out.println("Holder Name: " + holderName);
		System.out.println("No of Accounts: " + noOfTransactionAccounts);
		System.out.println("Address: " + holderAddress);
		System.out.println("Open Date: " + openDate);
		System.out.println("WalletID: " + walletID);
		System.out.println("Total Balance: " + totalBalance);
		for (Account account : transactionAccounts.values()) {
			System.out.println("Account Name: " + account.getName());
			System.out.println("AccountID: " + account.getTransactionAccountID());
			System.out.println("Account Balance: " + account.getBalance());
			System.out.println("---------------------");
		}

	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#getTransactions(int, int, java.lang.String)
	 */
	@Override
	public List<Transaction> getTransactions(int accountID, int numOfTransactions, String password) {
		List<Transaction> result = new ArrayList<Transaction>();
		if (this.password == password) {
			int sizeTransaction = transactionAccounts.get(accountID).getTransactions().size();
			if (numOfTransactions != 0) {
				if (numOfTransactions <= sizeTransaction) {
					for (int i = 1; i < numOfTransactions + 1; i++) {
						result.add(transactionAccounts.get(accountID).getTransactions().get(sizeTransaction - i));
					}
				} else {
					System.out.println("Not enough transactions");
					result.addAll(transactionAccounts.get(accountID).getTransactions());
					return result;
				}
				return result;
			} else {
				System.out.println("Error: 0 not a valid entry");
				return result;

			}
		} else {
			System.out.println("Incorrect Password");
			return result;
		}
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#transferToWallet(Wallet, int, int, double, java.lang.String)
	 */
	@Override
	public synchronized boolean transferToWallet(Wallet toWallet, int toAccountID, int fromAccountID, double amount,
			String password) {
		boolean result = false;
		if (this.password == password) {
			if (amount <= 0) {
				System.out.println("Transfered amount needs to be positive and greater than 0");
				return result;
			} else {
				if (transactionAccounts.containsKey(fromAccountID)
						&& toWallet.transactionAccounts.containsKey(toAccountID)) {
					boolean successWithdraw = transactionAccounts.get(fromAccountID).withdraw(amount);
					if (successWithdraw) {
						result = toWallet.transactionAccounts.get(toAccountID).deposit(amount);
						calculateTotalBalance();
						return result;
					}
				}

			}
		} else {
			System.out.println("Incorrect Password");
			return result;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#verifyBalance(java.lang.String)
	 */
	@Override
	public boolean verifyBalance(String password) {
		if (this.password == password) {
			boolean result = true;
			for (Account t : transactionAccounts.values()) {
				result = result && t.verifyBalance();
			}
			calculateTotalBalance();
			return result;
		} else {
			System.out.println("Incorrect Password");
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#compareTo(Wallet)
	 */
	@Override
	public boolean compareTo(Wallet w) {

		if (this.totalBalance > w.totalBalance) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#getAccount(int, java.lang.String)
	 */
	@Override
	public Account getAccount(int id, String password) {
		if (this.password == password) {
			if (transactionAccounts.containsKey(id)) {
				return transactionAccounts.get(id);
			} else {
				System.out.println("Error: account not found");
				return new TransactionAccount("Undefined", id, this.walletID);
			}
		} else {
			System.out.println("Incorrect Password");
			return new TransactionAccount("Undefined", id, this.walletID);
		}
	}

	/* (non-Javadoc)
	 * @see WalletLibraryInterface#transactionReversal(int, java.lang.String)
	 */
	@Override
	public boolean transactionReversal(int TransactionID, String password) {
		int index = -1;
		boolean idExist = false;
		if (this.password == password) {
			for (Transaction t : transactions) {
				index++;
				if (t.getTransactionID() == TransactionID) {
					idExist = true;
					break;
				}
			}
			if (idExist) {
				String type = transactions.get(index).getType();
				if (type=="deposit") {
					withdraw(transactions.get(index).getAmount(),transactions.get(index).getToAccountID(),password);
					transactions.get(index).setStatus("Reversed");
					return true;
				}
				if (type=="withdraw") {
					deposit(transactions.get(index).getAmount(),transactions.get(index).getToAccountID(),password);
					transactions.get(index).setStatus("Reversed");
					return true;
				}

			}
		}else {
			System.out.println("Incorrect Password");
			return false;
		}
		return false;
	}


}
