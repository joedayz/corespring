package rewards.internal.restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;

import common.repository.ImmutableValueUserType;

/**
 * A Hibernate user type that maps columns in a Restaurant result set to a {@link BenefitAvailabilityPolicy}.
 */
public class BenefitAvailabilityPolicyUserType extends ImmutableValueUserType {

	@SuppressWarnings("rawtypes")
	public Class returnedClass() {
		return BenefitAvailabilityPolicy.class;
	}

	public int[] sqlTypes() {
		return new int[] { Types.CHAR };
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		String policyCode = rs.getString(names[0]);
		if ("A".equals(policyCode)) {
			return AlwaysAvailable.INSTANCE;
		} else if ("N".equals(policyCode)) {
			return NeverAvailable.INSTANCE;
		} else {
			throw new IllegalArgumentException("Not a supported policy code " + policyCode);
		}
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		throw new UnsupportedOperationException("Should never be called - the rewards app does not change policy");
	}
}