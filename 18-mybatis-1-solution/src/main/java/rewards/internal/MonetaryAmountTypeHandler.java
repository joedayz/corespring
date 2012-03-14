package rewards.internal;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import common.money.MonetaryAmount;

public class MonetaryAmountTypeHandler implements TypeHandler {

	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter,
			JdbcType jdbcType) throws SQLException {
		
		double amount = ((MonetaryAmount)parameter).asDouble();
		ps.setDouble(i, amount);
	}

	@Override
	public Object getResult(ResultSet rs, String columnName)
			throws SQLException {
		
        double amount = rs.getDouble(columnName);
        return new MonetaryAmount(amount);
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		double amount = cs.getDouble(columnIndex);
        return new MonetaryAmount(amount);
	}
	
    public Object valueOf(String amount) {
    	return MonetaryAmount.valueOf(amount);
    }

}
