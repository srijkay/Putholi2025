package com.newrta.putholi.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.newrta.putholi.api.domain.ProductConfig;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ProductConfigDTO;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface ProductConfigService {


	/**
	 * @param configCode
	 * @return
	 */
	List<ProductConfig> fetchCompanyInfo(String loggedUser);

	/**
	 * @param locale
	 * @param loggedUser
	 * @param productConfigDTO
	 * @param companyLogo
	 * @return
	 */
	ApiResultDTO modifyProductConfig(String locale, String loggedUser, ProductConfigDTO productConfigDTO,
			MultipartFile companyLogo);

	/**
	 * @param locale
	 * @param loggedUser
	 * @param productConfigDTO
	 * @param companyLogo
	 * @return
	 */
	ApiResultDTO createProductConfig(String locale, String loggedUser, ProductConfigDTO productConfigDTO,
			MultipartFile companyLogo);

}
