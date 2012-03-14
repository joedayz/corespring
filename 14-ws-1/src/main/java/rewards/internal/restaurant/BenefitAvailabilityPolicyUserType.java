package rewards.internal.restaurant;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

public class BenefitAvailabilityPolicyUserType implements UserType {

	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return getBenefitAvailabiltyPolicy((String) cached);
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		BenefitAvailabilityPolicy policy = (BenefitAvailabilityPolicy) value;
		return getPolicyCode(policy);
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		return x.equals(y);
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public boolean isMutable() {
		return false;
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		String policyCode = rs.getString(names[0]);
		return getBenefitAvailabiltyPolicy(policyCode);
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.VARCHAR);
		} else {
			BenefitAvailabilityPolicy policy = (BenefitAvailabilityPolicy) value;
			st.setString(index, getPolicyCode(policy));
		}
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@SuppressWarnings("rawtypes")
	public Class returnedClass() {
		return BenefitAvailabilityPolicy.class;
	}

	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

	private BenefitAvailabilityPolicy getBenefitAvailabiltyPolicy(String policyCode) {
		if ("A".equals(policyCode)) {
			return AlwaysAvailable.INSTANCE;
		} else if ("N".equals(policyCode)) {
			return NeverAvailable.INSTANCE;
		} else {
			throw new IllegalArgumentException("Not a supported policy code " + policyCode);
		}
	}

	private String getPolicyCode(BenefitAvailabilityPolicy policy) {
		if (AlwaysAvailable.INSTANCE.equals(policy)) {
			return "A";
		} else if (NeverAvailable.INSTANCE.equals(policy)) {
			return "N";
		} else {
			throw new IllegalArgumentException("Not a supported policy " + policy.toString());
		}
	}
}
