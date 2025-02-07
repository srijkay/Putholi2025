package com.newrta.putholi.api.util;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
public class DatePrefixedSequenceIdGenerator extends SequenceStyleGenerator {

    public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
    public static final String VALUE_PREFIX_DEFAULT = "";
    private String valuePrefix;

    public static final String DATE_FORMAT_PARAMETER = "dateFormat";
    public static final String DATE_FORMAT_DEFAULT = "%1$ty%1$tm%1$td";
    private String dateFormat;

    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    public static final String NUMBER_FORMAT_DEFAULT = "%06d";
    private String numberFormat;

    /**
     *
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
	return valuePrefix + String.format(dateFormat, LocalDate.now())
		+ String.format(numberFormat, super.generate(session, object));
    }

    /**
     *
     */
    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
	super.configure(LongType.INSTANCE, params, serviceRegistry);
	valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params, VALUE_PREFIX_DEFAULT);
	dateFormat = ConfigurationHelper.getString(DATE_FORMAT_PARAMETER, params, DATE_FORMAT_DEFAULT);
	numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);
    }

}
