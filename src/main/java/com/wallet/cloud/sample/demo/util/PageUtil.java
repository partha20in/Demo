package com.wallet.cloud.sample.demo.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.wallet.cloud.sample.demo.exception.InvalidParametersException;
import com.wallet.cloud.sample.demo.service.AccountServiceImpl;

public class PageUtil {

	private static final Logger logger = LoggerFactory.getLogger(PageUtil.class);

	@SuppressWarnings("deprecation")
	protected static Pageable createPageRequest(int number, int size, String sort) {
		logger.debug("In createPageRequest method");

		
		Pageable pageables = null;
		try {
			if (sort != null) {
				if (sort.equalsIgnoreCase("ASC")) {
					
					pageables = PageRequest.of(number, size, Sort.by("id").ascending());
					
				} else {
					pageables = PageRequest.of(number, size, Sort.by("id").descending());
				}
			} else {
				throw new InvalidParametersException("Provide paging details");
			}
		} catch (InvalidParametersException e) {
			logger.error(e.getMessage());
		}
		
		return pageables;
	}

}
