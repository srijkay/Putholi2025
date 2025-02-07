package com.newrta.putholi.api.domain;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "VERIFICATION_TOKEN")
public class VerificationToken {

    /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "VERIFICATION_TOKEN_ID_SEQ")
    @SequenceGenerator(name = "VERIFICATION_TOKEN_ID_SEQ", sequenceName = "VERIFICATION_TOKEN_ID_SEQ", allocationSize = 1)
    @Column(name = "ID", length = 25)
    private Long id;

    /**
     * 
     */
    @Column(name = "TOKEN", length = 100)
    private String token;

    /**
     * 
     */
    @OneToOne(targetEntity = UserLogin.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "username")
    private UserLogin userLogin;

    /**
     * 
     */
    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

    /**
     * @param expiryTimeInMinutes
     * @return
     */
    private Date calculateExpiryDate(int expiryTimeInMinutes) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(new Timestamp(cal.getTime().getTime()));
	cal.add(Calendar.MINUTE, expiryTimeInMinutes);
	return new Date(cal.getTime().getTime());
    }

    /**
     * @param applicantUserLogin
     * @param token
     * @param expiry
     */
    public VerificationToken(UserLogin userLogin, String token, int expiry) {
	this.userLogin = userLogin;
	this.expiryDate = calculateExpiryDate(expiry);
	this.token = token;
    }

}
