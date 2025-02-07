package com.newrta.putholi.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@NoArgsConstructor
@Data
@AllArgsConstructor(staticName = "build")
public class CheckoutResponseDTO {

    private String encRequest;
    private String accessCode;
}
