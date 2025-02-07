package com.newrta.putholi.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.ProductConfig;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.ProductConfigDTO;
import com.newrta.putholi.api.service.ProductConfigService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Slf4j
@Data
@RequestMapping(value = "/v1/api/productconfig")
public class ProductConfigResource {

	/**
	 * 
	 */
	@Autowired(required = true)
	private ProductConfigService productConfigService;

	/**
	 * @param locale
	 * @param authorization
	 * @param loggedUser
	 * @param productConfigDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> createProductConfig(
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization, @RequestHeader String loggedUser,
			@RequestPart("ProductConfigDTO") String productConfigDTO,
			@RequestPart(value = "companyLogo", required = true) MultipartFile companyLogo) {
		log.info("ProductConfigResource-createProductConfig");

		ApiResultDTO apiResultDTO;
		HttpStatus httpStatus;
		try {
			apiResultDTO = productConfigService.createProductConfig(locale, loggedUser,
					new ObjectMapper().readValue(productConfigDTO, ProductConfigDTO.class), companyLogo);
			httpStatus = HttpStatus.OK;

		} catch (JsonProcessingException jpe) {
			log.error("registeruser-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("ERROR")
					.apiStatusDesc("Error While Processing, Contact Admin!").build();
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
		}
		return new ResponseEntity<>(apiResultDTO, httpStatus);
	}

	/**
	 * @param locale
	 * @param authorization
	 * @param loggedUser
	 * @param productConfigDTO
	 * @return
	 */
	@CrossOrigin
	@PutMapping
	public ResponseEntity<ApiResultDTO> updateProductConfig(
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization, @RequestHeader String loggedUser,
			@RequestPart("ProductConfigDTO") String productConfigDTO,
			@RequestPart(value = "companyLogo", required = false) MultipartFile companyLogo) {
		log.info("ProductConfigResource-updateProductConfig");
		ApiResultDTO apiResultDTO;
		HttpStatus httpStatus;
		try {
			apiResultDTO = productConfigService.modifyProductConfig(locale, loggedUser,
					new ObjectMapper().readValue(productConfigDTO, ProductConfigDTO.class), companyLogo);
			httpStatus = HttpStatus.OK;

		} catch (JsonProcessingException jpe) {
			log.error("registeruser-JsonProcessingException {} {}", jpe.getCause(), jpe);
			apiResultDTO = ApiResultDTO.builder().apiStatusCode("ERROR")
					.apiStatusDesc("Error While Processing, Contact Admin!").build();
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
		}
		return new ResponseEntity<>(apiResultDTO, httpStatus);
	}

	/**
	 * @param locale
	 * @param authorization
	 * @param loggedUser
	 * @param configid
	 * @return
	 */
	@CrossOrigin
	@GetMapping
	public List<ProductConfig> fetchProductConfig(@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization, @RequestHeader String loggedUser) {
		log.info("ProductConfigResource-fetchProductConfig");

		return productConfigService.fetchCompanyInfo(loggedUser);
	}


}
