package com.newrta.putholi.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Data
@NoArgsConstructor
public class MenuDetailsSelectDTO {

    /**
     * 
     */

    private Long menuId;

    /**
     * 
     */
    private String menuCode;

    /**
     * 
     */
    private String menuName;

    /**
     * 
     */
    private boolean selected;

    /**
     * @param menuId
     * @param menuCode
     * @param menuName
     * @param selected
     */
    public MenuDetailsSelectDTO(Long menuId, String menuCode, String menuName, boolean selected) {
	super();
	this.menuId = menuId;
	this.menuCode = menuCode;
	this.menuName = menuName;
	this.selected = selected;
    }

}
