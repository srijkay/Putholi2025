package com.newrta.putholi.api.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data

public class DeoMasterCodeList implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -4866396349374962734L;

	/**
	 * 
	 */

	private List<DeoMasterCodeResultDTO> deoMasterCodeResultDTO;

	public DeoMasterCodeList(List<DeoMasterCodeResultDTO> deoMasterCodeResultDTO) {
		super();
		this.deoMasterCodeResultDTO = deoMasterCodeResultDTO;
	}
	
	
}
