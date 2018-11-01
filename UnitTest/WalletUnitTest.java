import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;


public class WalletUnitTest {

    @Test
    public void CreatingWalletTest() {
        WalletLibraryInterface tester = new Wallet("daniel","montreal",1,"daniel"); // MyClass is tested

        // assert statements
        assertEquals("daniel",tester.getHolderName());
        assertEquals("montreal",tester.getHolderAddress());
        assertEquals(tester.getHolderID(), 1);
        assertEquals("daniel",tester.getPassword());
    }
    
    
	@Test
    public void WalletBalanceTest() {
		WalletLibraryInterface walletTest =new Wallet("daniel","montreal",1,"daniel");
    	int Account1 = walletTest.AddTransactionAccount("Checking", 500,"daniel");
    	int Account2 = walletTest.AddTransactionAccount("Saving", 500,"daniel");
    	
    	
    	// assert statements
        assertEquals(1000, walletTest.getTotalBalance(), 0);
    }
	
	@Test
    public void AddTransactionAccountTest() {
		Wallet walletTest =new Wallet("daniel","montreal",1,"daniel");
    	int Account1 = walletTest.AddTransactionAccount("Checking", 500,"daniel");
    	int Account2 = walletTest.AddTransactionAccount("Saving", 500,"daniel");
    	
    	
    	// assert statements
        assertEquals(walletTest.getNoOfTransactionAccounts(),2);
        assertEquals(true, walletTest.transactionAccounts.containsKey(Account1));
        assertEquals(true, walletTest.transactionAccounts.containsKey(Account2));
        assertEquals("Checking", walletTest.transactionAccounts.get(Account1).getName());
        assertEquals(500, walletTest.transactionAccounts.get(Account1).getBalance(),0);
        
    }
	@Test
    public void AddTransactionAccountInstanceTest() {
		Wallet walletTest =new Wallet("daniel","montreal",1,"daniel");
    	Account Account = new TransactionAccount("Saving", 500,walletTest.getWalletID());
    	walletTest.AddTransactionAccount(Account, "daniel");
    	
    	
    	// assert statements
        assertEquals(walletTest.getNoOfTransactionAccounts(),1);
        assertEquals(true, walletTest.transactionAccounts.containsValue(Account));
        assertEquals("Saving", walletTest.transactionAccounts.get(Account.getTransactionAccountID()).getName());
        assertEquals(500, walletTest.transactionAccounts.get(Account.getTransactionAccountID()).getBalance(),0);
        
    }
	
	@Test
    public void WithdrawTest() {
		Wallet walletTest =new Wallet("daniel","montreal",1,"daniel");
    	int Account1 = walletTest.AddTransactionAccount("Checking", 500,"daniel");
    	
    	boolean result = walletTest.withdraw(100, Account1, "daniel");
    	
    	
    	// assert statements
    	assertEquals(400, walletTest.transactionAccounts.get(Account1).getBalance(),0);
    	assertEquals(true,result);
        
    }
	@Test
    public void WithdrawNotSufficientTest() {
		Wallet walletTest =new Wallet("daniel","montreal",1,"daniel");
    	int Account1 = walletTest.AddTransactionAccount("Checking", 500,"daniel");
    	
    	boolean result =walletTest.withdraw(600, Account1, "daniel");
    	
    	
    	// assert statements
    	assertEquals(500, walletTest.transactionAccounts.get(Account1).getBalance(),0);
    	assertEquals(false,result);
        
    }
	

	

	
	@Test
    public void DepositTest() {
		Wallet walletTest =new Wallet("daniel","montreal",1,"daniel");
    	int Account1 = walletTest.AddTransactionAccount("Checking", 500,"daniel");
    	
    	boolean result = walletTest.deposit(100, Account1, "daniel");
    	
    	
    	// assert statements
    	assertEquals(600, walletTest.transactionAccounts.get(Account1).getBalance(),0);
    	assertEquals(true,result);
        
    }
	
	@Test
    public void DepositInstanceTest() {
		Wallet walletTest =new Wallet("daniel","montreal",1,"daniel");
    	Account Account = new TransactionAccount("Saving", 500,walletTest.getWalletID());
    	walletTest.AddTransactionAccount(Account, "daniel");
    	boolean result = walletTest.deposit(100, Account, "daniel");
    	
    	
    	// assert statements
    	assertEquals(600, walletTest.transactionAccounts.get(Account.getTransactionAccountID()).getBalance(),0);
    	assertEquals(true,result);
    }
	
	@Test
    public void WithdrawInstanceTest() {
		Wallet walletTest =new Wallet("daniel","montreal",1,"daniel");
		Account Account = new TransactionAccount("Saving", 500,walletTest.getWalletID());
    	walletTest.AddTransactionAccount(Account, "daniel");
    	boolean result = walletTest.withdraw(100, Account, "daniel");
    	
    	
    	// assert statements
    	assertEquals(400, walletTest.transactionAccounts.get(Account.getTransactionAccountID()).getBalance(),0);
    	assertEquals(true,result);
    }
	
	@Test
    public void TransferToAccountTest() {
		Wallet walletTest =new Wallet("daniel","montreal",1,"daniel");
    	int Account1 = walletTest.AddTransactionAccount("Checking", 500,"daniel");
    	int Account2 = walletTest.AddTransactionAccount("Saving", 500,"daniel");
    	
    	boolean result = walletTest.TransferToAccount(Account1, Account2, 100, "daniel");
    	
    	
    	// assert statements
        assertEquals(400, walletTest.transactionAccounts.get(Account1).getBalance(),0);
        assertEquals(600, walletTest.transactionAccounts.get(Account2).getBalance(),0);
        assertEquals(true,result);
    }
	
	
	@Test
    public void getTransactionsTest() {
		WalletLibraryInterface walletTest =new Wallet("daniel","montreal",1,"daniel");
    	int Account1 = walletTest.AddTransactionAccount("Checking", 500,"daniel");
    	walletTest.deposit(500, Account1, "daniel");
    	List<Transaction> result = walletTest.getTransactions(Account1, 1, "daniel");
    	
    	// assert statements
        assertEquals(500,result.get(0).getAmount() ,0);
        assertEquals("deposit", result.get(0).getType());
    }
	
	
	@Test
    public void verifyBalanceTest() {
		WalletLibraryInterface walletTest =new Wallet("daniel","montreal",1,"daniel");
    	int Account1 = walletTest.AddTransactionAccount("Checking", 500,"daniel");
    	int Account2 = walletTest.AddTransactionAccount("Saving", 500,"daniel");
    	walletTest.TransferToAccount(Account1, Account2, 100, "daniel");
    	walletTest.withdraw(100, Account1, "daniel");
    	walletTest.deposit(100, Account2, "daniel");
    	
    	boolean result = walletTest.verifyBalance("daniel");
    	
    	// assert statements
        assertEquals(1000, walletTest.getTotalBalance(),0);
        assertEquals(true,result);
    }
	
	@Test
    public void compareToTest() {
		Wallet walletTest1 =new Wallet("daniel","montreal",1,"daniel");
    	int Account1 = walletTest1.AddTransactionAccount("Checking", 600,"daniel");
    	Wallet walletTest2 =new Wallet("daniel","montreal",1,"daniel");
    	int Account2 = walletTest2.AddTransactionAccount("Saving", 500,"daniel");

    	
    	// assert statements
        assertEquals(true, walletTest1.compareTo(walletTest2));
        assertEquals(false,walletTest2.compareTo(walletTest1));
    }
	
	@Test
    public void getAccountTest() {
		WalletLibraryInterface walletTest =new Wallet("daniel","montreal",1,"daniel");
    	int Account = walletTest.AddTransactionAccount("Checking", 600,"daniel");
    	Account result = walletTest.getAccount(Account, "daniel");

    	
    	// assert statements
        assertEquals(600, result.getBalance(),0);
        assertEquals("Checking",result.getName());
        assertEquals(walletTest.getWalletID(),result.getWalletID());
    }
	
	
	@Test
    public void transactionReversalTest() {
		WalletLibraryInterface walletTest =new Wallet("daniel","montreal",1,"daniel");
    	int Account = walletTest.AddTransactionAccount("Checking", 600,"daniel");
    	walletTest.deposit(100, Account, "daniel");
    	boolean result =walletTest.transactionReversal(walletTest.getTransactions().get(0).getTransactionID(),"daniel");

    	
    	// assert statements
        assertEquals(600, walletTest.getTotalBalance(),0);
        assertEquals(true,result);
    }
	
	@Test
    public void transactionTransferReversalTest() {
		Wallet walletTest =new Wallet("daniel","montreal",1,"daniel");
    	int Account1 = walletTest.AddTransactionAccount("Checking", 500,"daniel");
    	int Account2 = walletTest.AddTransactionAccount("Saving", 500,"daniel");
    	
    	boolean result1 = walletTest.TransferToAccount(Account1, Account2, 100, "daniel");
    	boolean result2 =walletTest.transactionReversal(walletTest.getTransactions().get(0).getTransactionID(),"daniel");

    	
    	// assert statements
        assertEquals(1000, walletTest.getTotalBalance(),0);
        assertEquals(500, walletTest.getAccount(Account1, "daniel").getBalance(),0);
        assertEquals(500, walletTest.getAccount(Account2, "daniel").getBalance(),0);
        assertEquals(true,result1);
        assertEquals(true,result2);
    }
	
	@Test
    public void transactionTransferReversalFailTest() {
		Wallet walletTest =new Wallet("daniel","montreal",1,"daniel");
    	int Account1 = walletTest.AddTransactionAccount("Checking", 500,"daniel");
    	int Account2 = walletTest.AddTransactionAccount("Saving", 500,"daniel");
    	
    	boolean result1 = walletTest.TransferToAccount(Account1, Account2, 100, "daniel");
    	walletTest.withdraw(600, Account2, "daniel");
    	boolean result2 =walletTest.transactionReversal(walletTest.getTransactions().get(0).getTransactionID(),"daniel");

    	
    	// assert statements
        assertEquals(400, walletTest.getTotalBalance(),0);
        assertEquals(400, walletTest.getAccount(Account1, "daniel").getBalance(),0);
        assertEquals(0, walletTest.getAccount(Account2, "daniel").getBalance(),0);
        assertEquals(true,result1);
        assertEquals(false,result2);
    }
}