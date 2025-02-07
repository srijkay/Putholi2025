package com.newrta.putholi.api.repository;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.newrta.putholi.api.domain.UserLogin;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, String> {

	/**
	 * @param username
	 * @return
	 */
	public UserLogin findByUserNameIgnoreCase(String username);

	/**
	 * @param username
	 * @return
	 */
	public boolean existsByUserNameIgnoreCase(String username);

	/**
	 * @param userName
	 * @param encodePassword
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE UserLogin aul SET aul.password = :encodePassword, aul.changePasswordRequired = true WHERE aul.userName = :userName ")
	public void forgetPasswordChange(@PathParam("userName") String userName,
			@PathParam("encodePassword") String encodePassword);

	/**
	 * @param username
	 */
	@Modifying(clearAutomatically = true)
	public void deleteByUserNameIgnoreCase(String username);
}
