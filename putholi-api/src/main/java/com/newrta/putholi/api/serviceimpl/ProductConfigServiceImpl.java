package com.newrta.putholi.api.serviceimpl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.AuditDetails;
import com.newrta.putholi.api.domain.ProductConfig;
import com.newrta.putholi.api.domain.UserAttachment;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ProductConfigDTO;
import com.newrta.putholi.api.repository.ProductConfigRepository;
import com.newrta.putholi.api.repository.UserAttachmentRepository;
import com.newrta.putholi.api.service.ProductConfigService;
import com.newrta.putholi.api.service.UserAttachmentService;
import com.newrta.putholi.api.util.CommonQueueUtilService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Service
@Slf4j
@Data
public class ProductConfigServiceImpl implements ProductConfigService {
	/**
	 * 
	 */
	@Autowired(required = true)
	private ProductConfigRepository productConfigRepository;

	/**
	 * 
	 */
	@Autowired(required = true)
	CommonQueueUtilService commonQueueUtilService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private UserAttachmentRepository userProfileAttachmentRepository;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 * 
	 */
	@Autowired(required = true)
	UserAttachmentService userAttachmentService;

	/**
	 *
	 */
	@Override
	public List<ProductConfig> fetchCompanyInfo(String loggedUser) {
		log.info("ProductConfigServiceImpl-fetchCompanyInfo");

		UserAttachment userProfileAttachment = userAttachmentService.findByUploadFor("PC");
		List<ProductConfig> productConfig = productConfigRepository.findAll();
		if (userProfileAttachment != null) {
			for (ProductConfig productConfig2 : productConfig) {
				productConfig2.setCompanyLogo(Base64.getEncoder().encodeToString(userProfileAttachment.getFileData()));
			}
		}
		AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.FIND)
				.auditModule(CommonsConstants.PRODUCT_MGMT).auditDesc("ProductConfigServiceImpl-fetchSchoolInfo")
				.createdBy(loggedUser).build();
		commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

		return productConfig;
	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO modifyProductConfig(String locale, String loggedUser, ProductConfigDTO productConfigDTO,
			MultipartFile companyLogo) {
		log.info("ProductConfigServiceImpl-modifyProductConfig {}", productConfigDTO);

		ApiResultDTO apiResultDTO;

		if (productConfigDTO.getCompanyName() == null) {
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("FAILURE").apiStatusDesc("ID cannot be NULL").build();
		} else {
			if (productConfigRepository.existsById(productConfigDTO.getCompanyName())) {
				try {
					ProductConfig productConfig = modelMapper.map(productConfigDTO, ProductConfig.class);

					UserAttachment userAttachment = userProfileAttachmentRepository
							.findByUsernameAndUploadFor(productConfigDTO.getCompanyName(), "PC");

					if (userAttachment != null && companyLogo != null) {
						userProfileAttachmentRepository
								.save(new UserAttachment(userAttachment.getId(), productConfigDTO.getCompanyName(),
										"PC", companyLogo.getContentType(), companyLogo.getBytes()));
					} else if (companyLogo != null) {
						userProfileAttachmentRepository.save(new UserAttachment(productConfigDTO.getCompanyName(), "PC",
								companyLogo.getContentType(), companyLogo.getBytes()));
					}
					AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.UPDATE)
							.auditModule(CommonsConstants.PRODUCT_MGMT)
							.auditDesc("ProductConfigServiceImpl-modifyProductConfig")
							.auditValue(new ObjectMapper().writeValueAsString(productConfigDTO)).createdBy(loggedUser)
							.build();
					commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);
					productConfigRepository.save(productConfig);

					apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
							.apiStatusDesc("Updated Info Successfully!").build();

				} catch (IOException jpe) {
					log.error("ProductConfigServiceImpl-modifyProductConfig-JsonProcessingException {} {}",
							jpe.getCause(), jpe);
					apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
							.apiStatusDesc("Error While Processing, Contact System Administrator").build();
				}
			} else {
				apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
						.apiStatusDesc("ID Doesn't exists in the System").build();
			}
		}
		return apiResultDTO;

	}

	/**
	 *
	 */
	@Override
	public ApiResultDTO createProductConfig(String locale, String loggedUser, ProductConfigDTO productConfigDTO,
			MultipartFile companyLogo) {
		log.info("ProductConfigServiceImpl-createProductConfig {}", productConfigDTO);

		ApiResultDTO apiResultDTO;

		ProductConfig productConfig = modelMapper.map(productConfigDTO, ProductConfig.class);
		log.info("ProductConfigServiceImpl-createProductConfig {}", productConfig);
		try {

			if (companyLogo != null) {
				userAttachmentService.saveUserProfileAttachment(locale, new UserAttachment(
						productConfigDTO.getCompanyName(), "PC", companyLogo.getContentType(), companyLogo.getBytes()));
			}

			AuditDetails auditDetails = AuditDetails.builder().functionCode(CommonsConstants.INSERT)
					.auditModule(CommonsConstants.PRODUCT_MGMT)
					.auditDesc("ProductConfigServiceImpl-createProductConfig")
					.auditValue(new ObjectMapper().writeValueAsString(productConfigDTO)).createdBy(loggedUser).build();
			commonQueueUtilService.sendAuditDetailsToQueue(auditDetails);

			productConfigRepository.save(productConfig);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.SUCCESS)
					.apiStatusDesc("Saved Info Successfully!").build();

		} catch (IOException e) {
			log.error("ProductConfigServiceImpl-createProductConfig-JsonProcessingException {}", e.getCause());
			apiResultDTO = ApiResultDTO.builder().apiStatusCode(CommonsConstants.ERROR)
					.apiStatusDesc("Error While Processing, Contact System Administrator").build();
		}

		return apiResultDTO;
	}

}
