package com.newrta.putholi.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.newrta.putholi.api.domain.QuotationAttachments;
import com.newrta.putholi.api.model.ApiResultDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface QuotationAttachmentService {

	/**
	 * @param locale
	 * @param requirementId
	 * @param string
	 * @param quotation
	 * @return
	 */

	/**
	 *
	 */
	ApiResultDTO saveQuotationAttachment(String locale, Long requirementId, long quotationId, long invoiceId,
			long schoolInfoId, Long expensesId, String uploadFor, MultipartFile quotation);

	/**
	 * @param locale
	 * @param attachmentId
	 * @param requirementId
	 * @param uploadFor
	 * @param file
	 * @return
	 */
	ApiResultDTO updateAttachmentByAttachmentId(String locale, Long attachmentId, Long requirementId, long quotationId,
			long invoiceId, long schoolInfoId, Long expensesId, String uploadFor, MultipartFile file);

	/**
	 * @param requirementId
	 * @param uploadFor
	 * @return
	 */
	List<QuotationAttachments> getQuotationAttachment(Long requirementId, String uploadFor);

	/**
	 * @param quotationId
	 * @param uploadFor
	 * @return
	 */
	QuotationAttachments getAttachmentByquotationId(long quotationId);

	/**
	 * @param invoiceId
	 * @param uploadFor
	 * @return
	 */
	List<QuotationAttachments> getAttachmentByInvoiceId(long invoiceId, String uploadfor);

	/**
	 * @param attachmentId
	 * @return
	 */
	ApiResultDTO removeQuotationAttachment(Long attachmentId);

	/**
	 * @param quotationId
	 * @return
	 */
	ApiResultDTO removeAttachmentByQuotateId(long quotationId);

	/**
	 * 
	 * @param requirementId
	 * @param uploadFor
	 * @return
	 */
	ApiResultDTO deleteByRequirementIdAndUploadFor(Long requirementId, String uploadFor);

	/**
	 * @param attachmentId
	 * @return
	 */
	QuotationAttachments findByAttachmentId(Long attachmentId);

	/**
	 * @param schoolInfoId
	 * @return
	 */
	List<QuotationAttachments> findBySchoolInfoId(long schoolInfoId, String uploadFor);

	/**
	 * @param invoiceId
	 * @return
	 */
	ApiResultDTO removeAttachmentByInvoiceId(long invoiceId);

	/**
	 * @param loggedUser
	 * @param uploadFor
	 * @return
	 */
	List<QuotationAttachments> getAttachments(String loggedUser, String uploadFor);

	/**
	 * @param uploadFor
	 * @return
	 */
	List<QuotationAttachments> findByLastSixMonthsUploadFor(String uploadFor);

	/**
	 * @param expensesId
	 * @return
	 */
	QuotationAttachments findByExpensesId(long expensesId);

}
