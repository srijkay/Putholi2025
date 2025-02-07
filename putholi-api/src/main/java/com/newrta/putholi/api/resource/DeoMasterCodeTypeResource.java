package com.newrta.putholi.api.resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.DeoMasterCodeTypeDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.DeoMasterCodeTypeDTO;
import com.newrta.putholi.api.service.DeoMasterCodeTypeService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Slf4j
@Data
@RequestMapping(value = "/v1/api/deomastercodetype")
public class DeoMasterCodeTypeResource {

	/**
	* 
	*/
	@Autowired(required = true)
	private DeoMasterCodeTypeService deoMasterCodeTypeService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 * 
	 * @param authorization
	 * @param locale
	 * @param deoMasterCodeTypeDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> createDeoMasterCodeType(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestBody DeoMasterCodeTypeDTO deoMasterCodeTypeDTO) {
		log.info("DeoMasterCodeTypeResource-createDeoMasterCodeType");
		return new ResponseEntity<>(deoMasterCodeTypeService.createDeoMasterCodeType(locale,
				modelMapper.map(deoMasterCodeTypeDTO, DeoMasterCodeTypeDetails.class)), HttpStatus.OK);

	}

	/**
	 * @param authorization
	 * @param locale
	 * @param masterCodeTypeDetailsDTO
	 * @return
	 */
	@CrossOrigin
	@PutMapping
	public ResponseEntity<ApiResultDTO> modifyDeoMasterCodeType(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestBody DeoMasterCodeTypeDTO deoMasterCodeTypeDTO) {
		log.info("DeoMasterCodeTypeResource-modifyDeoMasterCodeType");
		return new ResponseEntity<>(deoMasterCodeTypeService.modifyDeoMasterCodeType(locale,
				modelMapper.map(deoMasterCodeTypeDTO, DeoMasterCodeTypeDetails.class)), HttpStatus.OK);

	}

}
