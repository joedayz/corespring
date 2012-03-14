package rewards.internal.restaurant;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * iBATIS TypeHandlerCallback for mapping {@link BenefitAvailabilyPolicy} instances to and from the database as simple policy codes
 */
public class BenefitAvailabilityPolicyTypeHandler implements TypeHandler {

	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter,
			JdbcType jdbcType) throws SQLException {
		
        BenefitAvailabilityPolicy policy = (BenefitAvailabilityPolicy)parameter;
        if(policy instanceof AlwaysAvailable)
        	ps.setString(i, "A");
        else if(policy instanceof NeverAvailable)
        	ps.setString(i, "N");
		else
			throw new IllegalArgumentException("Not a supported benefit availability policy: " + policy.getClass().getName());
	}
	
	@Override
	public Object getResult(ResultSet rs, String columnName)
			throws SQLException {
		
        String policyCode = rs.getString(columnName);
        return valueOf(policyCode);
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		 
		String policyCode = cs.getString(columnIndex);
		return valueOf(policyCode);
	}

    public Object valueOf(String policyCode) {
        if("A".equals(policyCode))
        	return new AlwaysAvailable();
        else if("N".equals(policyCode))
        	return new NeverAvailable();
		else
			throw new IllegalArgumentException("Not a supported policy code " + policyCode);
    }

}
