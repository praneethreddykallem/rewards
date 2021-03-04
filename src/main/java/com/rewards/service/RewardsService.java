package com.rewards.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rewards.dto.TransactionData;

@Service
public class RewardsService {

	private static final String TOTAL = "TOTAL";

	public Map<String, Map<String, Integer>> calculate(List<TransactionData> transactionsData) {
		Map<String, Map<String, Integer>> rewardsMap = new HashMap<>();

		transactionsData.forEach(transaction -> {
			if (rewardsMap.containsKey(transaction.getCustomerName())) {
				addRewardPoints(rewardsMap, transaction);
			} else {
				addCustomer(rewardsMap, transaction);
			}
		});
		return rewardsMap;
	}

	private void addRewardPoints(Map<String, Map<String, Integer>> rewardsMap, TransactionData transaction) {
		Map<String, Integer> customerMap = rewardsMap.get(transaction.getCustomerName());
		String transactionMonth = getTransactionMonth(transaction.getTransactionDate());

		if (customerMap.containsKey(transactionMonth)) {
			Integer rewards = customerMap.get(transactionMonth);
			customerMap.put(transactionMonth, rewards + calculatedPoints(transaction.getAmount()));
		} else {
			customerMap.put(transactionMonth, calculatedPoints(transaction.getAmount()));
		}

		customerMap.put(TOTAL, calculateTotalPoints(customerMap));
		rewardsMap.put(transaction.getCustomerName(), customerMap);
	}

	private void addCustomer(Map<String, Map<String, Integer>> rewardsMap, TransactionData transaction) {
		Map<String, Integer> customerMap = new HashMap<>();
		customerMap.put(getTransactionMonth(transaction.getTransactionDate()),
				calculatedPoints(transaction.getAmount()));
		customerMap.put(TOTAL, calculatedPoints(transaction.getAmount()));
		rewardsMap.put(transaction.getCustomerName(), customerMap);
	}

	private Integer calculatedPoints(Integer amount) {
		if (amount > 100) {
			return 50 + (amount - 100) * 2;
		} else if (amount > 50) {
			return amount - 50;
		}
		return 0;
	}

	private String getTransactionMonth(String transactionDate) {
		DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		try {
			Date date = format.parse(transactionDate);
			LocalDate localdDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return localdDate.getMonth().toString();
		} catch (ParseException e) {
		}
		return null;
	}

	private Integer calculateTotalPoints(Map<String, Integer> customerMap) {
		Integer totalPoints = 0;
		for (Map.Entry<String, Integer> entry : customerMap.entrySet())
			if (!entry.getKey().equals(TOTAL))
				totalPoints = totalPoints + entry.getValue();
		return totalPoints;
	}

}
