package com.example.transactionslocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class TransactionsLocksApp {

	public static void main(String[] args) {
		SpringApplication.run(TransactionsLocksApp.class, args);
	}

}
