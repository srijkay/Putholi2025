package com.newrta.putholi.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Entity
@Data
@Table(name = "TSYS_USER_RECOVERY_ANSWERS")
public class UserRecoveryAnswer {

    /**
     * 
     */
    @Id
    @Column(name = "username", length = 25, unique = true)
    private String username;

    /**
     * 
     */
    @Column(name = "code", length = 10, nullable = false)
    private String code;

    /**
     * 
     */
    @Column(name = "answer", length = 120, nullable = false)
    private String answer;

}
