package com.rewards.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.dto.TransactionData;
import com.rewards.service.RewardsService;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

	@Autowired
	private RewardsService rewardsService;

	@PostMapping("/calculatePoints")
	public ResponseEntity<Map<String, Map<String, Integer>>> calculateRewards(
			@RequestBody List<TransactionData> transactionsData) {

		Map<String, Map<String, Integer>> response = rewardsService.calculate(transactionsData);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
