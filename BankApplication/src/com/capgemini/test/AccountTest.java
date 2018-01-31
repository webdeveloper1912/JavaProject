package com.capgemini.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
public class AccountTest {

	AccountService accountService;
	
	@Mock
	AccountRepository accountRepository;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		accountService = new AccountServiceImpl(accountRepository);
	}

	/*
	 * create account
	 * 1.when the amount is less than 500 then system should throw exception
	 * 2.when the valid info is passed account should be created successfully
	 */
	
	@Test(expected=com.capgemini.exceptions.InsufficientInitialAmountException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientInitialAmountException
	{
		accountService.createAccount(101, 400);
	}
	
	
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialAmountException
	{
		Account account =new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 5000));
	}
	
	/*
	 * Deposit Amount
	 * 1.when the account number is invalid then system should throw exception
	 * 2.when the valid info is passed amount should be deposited successfully
	 */
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenTheAccountNumberInvalidSystemShouldThrowException() throws InvalidAccountNumberException
	{
		accountService.depositAmount(101, 200);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAmountShouldBeDepositedSuccessfully() throws InvalidAccountNumberException
	{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(200);
		
		when(accountRepository.searchAccount(101)).thenReturn(account);
		assertEquals(account.getAmount()+200, accountService.depositAmount(101, 200));
	}
	
	/* Withdraw Amount
	 * 1. When account number is invalid then system should throw exception
	 * 2. When account number having insufficient balance system should throw exception
	 * 3. Amount withdraw successfully 
	 */ 
	 
	 /*
	@Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
	public void whenTheInsufficientBalanceSystemShouldThrowException() throws InsufficientBalanceException, InvalidAccountNumberException 
	{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(200);
		
		when(accountRepository.searchAccount(101)).thenReturn(account);
		assertEquals(account.getAmount(), accountService.withdrawAmount(101, 100));
	}
	
	
	@Test 
	public void whenTheValidInfoIsPassedAmountShouldBeWithdrawSuccessfully() throws InvalidAccountNumberException, InsufficientBalanceException
	{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(200);
		
		assertEquals(account, accountService.withdrawAmount(101, 100));
	}
	*/
	

}
