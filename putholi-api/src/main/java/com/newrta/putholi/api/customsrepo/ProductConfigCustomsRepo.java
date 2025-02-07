package com.newrta.putholi.api.customsrepo;

import org.springframework.data.domain.Page;

import com.newrta.putholi.api.domain.ProductConfig;
import com.newrta.putholi.api.model.ProductConfigSearchDTO;


/**
 * @author NEWRTA SOLUTIONS
 *
 */
public interface ProductConfigCustomsRepo {
    
    /**
     * @param productConfigSearchDTO
     * @return
     */
    Page<ProductConfig> searchProductConfig(ProductConfigSearchDTO productConfigSearchDTO);

}
