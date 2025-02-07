package com.newrta.putholi.api.serviceimpl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.newrta.putholi.api.configuration.LocaleConfig;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.InvoiceDetails;
import com.newrta.putholi.api.domain.QuotationAttachments;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.repository.QuotationAttachmentRepository;
import com.newrta.putholi.api.service.InvoiceDetailsService;
import com.newrta.putholi.api.service.QuotationAttachmentService;
import com.newrta.putholi.api.service.SchoolApprovalHistoryService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
public class QuotationAttachmentServiceImpl implements QuotationAttachmentService {

	/**
	 * 
	 */
	@Autowired(required = true)
	QuotationAttachmentRepository attachmentRepo;

	/**
	 * 
	 */
	@Autowired(required = true)
	InvoiceDetailsService invoiceDetailsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private SchoolApprovalHistoryService approvalHistoryService;

	/**
	 *
	 */
	@Override
	public ApiResultDTO saveQuotationAttachment(String locale, Long requirementId, long quotationId, long invoiceId,
			long schoolInfoId, Long expensesId, String uploadFor, MultipartFile quotation) {
		log.info("QuotationAttachmentServiceImpl-saveQuotationAttachment");

		ApiResultDTO apiResultDTO;

		try {
			attachmentRepo.save(new QuotationAttachments(null, requirementId, quotationId, invoiceId, schoolInfoId,
					expensesId, uploadFor, StringUtils.cleanPath(quotation.getOriginalFilename()),
					quotation.getContentType(), quotation.getBytes(), quotation.getBytes().length / 1024));

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc("Saved Info Successfully").build();

			InvoiceDetails invoice = invoiceDetailsService.findByInvoiceId(null, invoiceId);

			if (invoice != null && invoice.getInvoiceStatus().equals("ADMREC")) {

				approvalHistoryService.sentEmailForApprovals(null, null, CommonsConstants.ADMININSTRATOR, "ADD",
						"TRUSTV", CommonsConstants.ADMIN_COUNT, "Volunteer Receipt");

			}

		} catch (IOException exe) {
			log.error("applicantAttachments-IOException {}", exe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc(LocaleConfig.getResourceValue("error.processing", null, locale, null)).build();

		}

		return apiResultDTO;
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO updateAttachmentByAttachmentId(String locale, Long attachmentId, Long requirementId,
			long quotationId, long invoiceId, long schoolInfoId, Long expensesId, String uploadFor,
			MultipartFile file) {
		log.info("QuotationAttachmentServiceImpl-updateAttachmentByAttachmentId {}", requirementId);
		ApiResultDTO apiResultDTO;
		try {
			attachmentRepo.save(new QuotationAttachments(attachmentId, requirementId, quotationId, invoiceId,
					schoolInfoId, expensesId, uploadFor, StringUtils.cleanPath(file.getOriginalFilename()),
					file.getContentType(), file.getBytes(), file.getBytes().length / 1024));

			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc("Updated Info Successfully").build();

		} catch (IOException exe) {
			log.error("applicantAttachments-IOException {}", exe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc(LocaleConfig.getResourceValue("error.processing", null, locale, null)).build();
		}

		return apiResultDTO;

	}

	/**
	 *
	 */
	@Override
	public List<QuotationAttachments> getQuotationAttachment(Long requirementId, String uploadFor) {
		log.info("QuotationAttachmentServiceImpl-getQuotationAttachment");
		return attachmentRepo.findByRequirementIdAndUploadFor(requirementId, uploadFor);
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO removeQuotationAttachment(Long attachmentId) {
		log.info("QuotationAttachmentServiceImpl-removeQuotationAttachment");

		attachmentRepo.deleteById(attachmentId);

		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Delete Attachments Successfully").build();
	}

	/**
	 *
	 */
	@Override
	public QuotationAttachments findByAttachmentId(Long attachmentId) {
		log.info("QuotationAttachmentServiceImpl-findByAttachmentId");

		return attachmentRepo.findByAttachmentId(attachmentId);
	}

	@Override
	public QuotationAttachments getAttachmentByquotationId(long quotationId) {
		log.info("QuotationAttachmentServiceImpl-getAttachmentByquotationId");

		return attachmentRepo.findByQuotationId(quotationId);
	}

	@Override
	public List<QuotationAttachments> getAttachmentByInvoiceId(long invoiceId, String uploadfor) {
		log.info("QuotationAttachmentServiceImpl-getAttachmentByInvoiceId");

		return attachmentRepo.findByInvoiceIdAndUploadFor(invoiceId, uploadfor);
	}

	@Override
	public ApiResultDTO removeAttachmentByQuotateId(long quotationId) {
		log.info("QuotationAttachmentServiceImpl-removeAttachmentByQuotateId");

		attachmentRepo.deleteByQuotationId(quotationId);
		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
				.apiStatusDesc("Delete Information Successfully").build();
	}

	/**
	 *
	 */
	@Override
	public List<QuotationAttachments> findBySchoolInfoId(long schoolInfoId, String uploadFor) {
		log.info("QuotationAttachmentServiceImpl-findBySchoolInfoId");

		return attachmentRepo.findBySchoolInfoIdAndUploadFor(schoolInfoId, uploadFor);
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO removeAttachmentByInvoiceId(long invoiceId) {
		log.info("QuotationAttachmentServiceImpl-removeAttachmentByInvoiceId");

		attachmentRepo.deleteByInvoiceId(invoiceId);
		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS).apiStatusDesc("Delete Info Successfully")
				.build();
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO deleteByRequirementIdAndUploadFor(Long requirementId, String uploadFor) {
		log.info("QuotationAttachmentServiceImpl-deleteByRequirementIdAndUploadFor");

		attachmentRepo.deleteByRequirementIdAndUploadFor(requirementId, uploadFor);

		return ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS).apiStatusDesc("Delete Info Successfully")
				.build();
	}

	/**
	 *
	 */
	@Override
	public List<QuotationAttachments> getAttachments(String loggedUser, String uploadFor) {
		log.info("QuotationAttachmentServiceImpl-getAttachments");

		return attachmentRepo.findByUploadFor(uploadFor);
	}

	/**
	 *
	 */
	@Override
	public List<QuotationAttachments> findByLastSixMonthsUploadFor(String uploadFor) {
		log.info("QuotationAttachmentServiceImpl-findByLastSixMonthsUploadFor");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		Date sixMonthsAgo = cal.getTime();
		return attachmentRepo.findByLastSixMonthsUploadFor(uploadFor, sixMonthsAgo);
	}

	/**
	 *
	 */
	@Override
	public QuotationAttachments findByExpensesId(long expensesId) {
		log.info("QuotationAttachmentServiceImpl-findByExpensesId");

		return attachmentRepo.findByExpensesId(expensesId);
	}
}
