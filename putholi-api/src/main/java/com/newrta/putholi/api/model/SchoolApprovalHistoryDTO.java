package com.newrta.putholi.api.model;

import java.util.List;

import com.newrta.putholi.api.domain.ExpensesDetails;
import com.newrta.putholi.api.domain.InvoiceDetails;
import com.newrta.putholi.api.domain.QuotationInfo;
import com.newrta.putholi.api.domain.RequirementInfo;
import com.newrta.putholi.api.domain.SchoolApprovalHistoryDetails;
import com.newrta.putholi.api.domain.SchoolInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolApprovalHistoryDTO {

	private SchoolInfo schoolInfo;

	private QuotationInfo quotationInfo;

	private RequirementInfo requirementInfo;

	private InvoiceDetails invoiceDetails;

	private ExpensesDetails expensesDetails;

	private List<SchoolApprovalHistoryDetails> schoolApprovalHistoryDetails;
}
