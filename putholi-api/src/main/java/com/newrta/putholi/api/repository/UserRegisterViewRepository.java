package com.newrta.putholi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.customsrepo.UserRegisterViewCustomRepo;
import com.newrta.putholi.api.domain.UserRegisterInfoDetails;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface UserRegisterViewRepository
		extends JpaRepository<UserRegisterInfoDetails, String>, UserRegisterViewCustomRepo {

}
