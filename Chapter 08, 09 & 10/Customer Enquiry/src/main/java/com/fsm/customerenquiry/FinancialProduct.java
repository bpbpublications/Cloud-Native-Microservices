package com.fsm.customerenquiry;

import java.util.Arrays;
import java.util.List;

public enum FinancialProduct {

	CAR_LOAN("CLOAN", "Loan for buying cars"), HOME_LOAN("HLOAN", "Loan for buying insurance"),
	HEALTH_INSURANCE("HINSU", "Health insurance"), MEDICAL_INSURANCE("MINSU", "Medical insurance");

	private String code;
	private String description;

	private FinancialProduct(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static FinancialProduct getFinancialProductFromName(String financialProductName) {
		FinancialProduct fiancialProduct = null;
		for (FinancialProduct fp : FinancialProduct.values()) {
			if (fp.name().equals(financialProductName))
				fiancialProduct = fp;
		}

		if (fiancialProduct == null)
			throw new RuntimeException("Unsupported Financial Product");

		return fiancialProduct;
	}

	public static List<FinancialProduct> getFinancialProducts() {
		return Arrays.asList(FinancialProduct.values());
	}
}
