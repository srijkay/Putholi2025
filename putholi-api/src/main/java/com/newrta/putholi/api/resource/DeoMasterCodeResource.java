package com.newrta.putholi.api.resource;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.newrta.putholi.api.constants.CommonsConstants;
import com.newrta.putholi.api.domain.DeoMasterCodeDetails;
import com.newrta.putholi.api.model.ApiResultDTO;
import com.newrta.putholi.api.model.DeoMasterCodeDetailsDTO;
import com.newrta.putholi.api.model.DeoMasterCodeList;
import com.newrta.putholi.api.model.DeoMasterCodeSearchDTO;
import com.newrta.putholi.api.service.DeoMasterCodeDetailsService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@RestController
@Slf4j
@Data
@RequestMapping(value = "/v1/api/deomastercode")
public class DeoMasterCodeResource {

	/**
	* 
	*/
	@Autowired(required = true)
	private DeoMasterCodeDetailsService deoMasterCodeDetailsService;

	/**
	 * 
	 */
	@Autowired(required = true)
	private ModelMapper modelMapper;

	/**
	 * 
	 * @param authorization
	 * @param locale
	 * @param deoMasterCodeDetailsDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ApiResultDTO> createDeoMasterCode(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestBody DeoMasterCodeDetailsDTO deoMasterCodeDetailsDTO) {
		log.info("DeoMasterCodeResource-createDeoMasterCode");
		return new ResponseEntity<>(deoMasterCodeDetailsService.createDeoMasterCode(locale,
				modelMapper.map(deoMasterCodeDetailsDTO, DeoMasterCodeDetails.class)), HttpStatus.OK);

	}

	/**
	 * 
	 * @param authorization
	 * @param locale
	 * @param deoMasterCodeDetailsDTO
	 * @return
	 */

	@CrossOrigin
	@PutMapping(value = "/v1/api/deomaster")
	public ResponseEntity<ApiResultDTO> modifyDeoMasterCode(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale,
			@RequestBody DeoMasterCodeDetailsDTO deoMasterCodeDetailsDTO) {
		log.info("DeoMasterCodeResource-modifyDeoMasterCode");
		return new ResponseEntity<>(deoMasterCodeDetailsService.modifyDeoMasterCode(locale,
				modelMapper.map(deoMasterCodeDetailsDTO, DeoMasterCodeDetails.class)), HttpStatus.OK);

	}

	/**
	 * 
	 * @param authorization
	 * @param locale
	 * @param deoMasterCodeSearchDTO
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/search")
	@ResponseStatus(code = HttpStatus.OK)
	public Page<DeoMasterCodeDetails> searchDeoMasterCodes(
			@RequestHeader String authorization,
			@RequestHeader String loggedUser,
			@RequestBody DeoMasterCodeSearchDTO deoMasterCodeSearchDTO) {
		log.info("MasterDeoCodeResource-searchDeoMasterCodes");
		return deoMasterCodeDetailsService.searchDeoMasterCodes(loggedUser, deoMasterCodeSearchDTO);
	}


	/**
	 * @param authorization
	 * @param locale
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<DeoMasterCodeDetails> fetchDeoMasterCodeById(
			@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale, @PathVariable("id") Long id) {
		log.info("MasterCodeResource-fetchMasterCodeById");
		return deoMasterCodeDetailsService.findDeoMasterCodeDetailsById(id);
	}

	/**
	 * @param authorization
	 * @param locale
	 * @param codeType
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/active")
	@ResponseStatus(code = HttpStatus.OK)
	public DeoMasterCodeList findActiveMasterCode(@RequestHeader(CommonsConstants.AUTHORIZATION) String authorization,
			@RequestHeader(CommonsConstants.ACCEPT_LANGUAGE) String locale) {
		log.info("CommonResource-findActiveMasterCode");
		return new DeoMasterCodeList(deoMasterCodeDetailsService.findMasterCode(CommonsConstants.YES));
	}

}
