package com.newrta.putholi.api.comparator;

import java.util.Comparator;
import java.util.Date;

import com.newrta.putholi.api.domain.UserLoginHistory;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public class UserLoginHistoryComparator implements Comparator<UserLoginHistory> {

    /**
     *
     */
    @Override
    public int compare(UserLoginHistory o1, UserLoginHistory o2) {
	/* passing dates to compare */
	return compareLoginTime(o1.getLoginTime(), o2.getLoginTime());
    }

    /**
     * @param loginTime
     * @param loginTime2
     * @return
     */
    private int compareLoginTime(Date loginTime, Date loginTime2) {
	/* method to compare two dates */
	return loginTime.compareTo(loginTime2);
    }

}
