package accounts;

import java.math.BigDecimal;
import java.util.List;

public interface BeneficiaryManager {

	public Account findAccountById(Long accountId);
	public List<Beneficiary> findBeneficiariesByAccountId(Long accountId);
	public void saveBeneficiary(Long accountId, Long id, String name, String allocationPercentage);
	public void deleteBeneficiary(Long id);
	public boolean isValidAllocationPercentage(Long accountId, Long id, BigDecimal allocationPercentage);
}
