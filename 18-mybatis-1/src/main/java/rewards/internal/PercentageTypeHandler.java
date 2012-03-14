package rewards.internal;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import common.money.Percentage;

public class PercentageTypeHandler implements TypeHandler {

	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter,
			JdbcType jdbcType) throws SQLException {
		
		double d = ((Percentage)parameter).asDouble();
		ps.setDouble(i, d);
	}

	@Override
	public Object getResult(ResultSet rs, String columnName)
			throws SQLException {
		
		double d = rs.getDouble(columnName);
        return new Percentage(d);
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		
		double d = cs.getDouble(columnIndex);
        return new Percentage(d);
	}
	
    public Object valueOf(String s) {
    	return Percentage.valueOf(s);
    }
}
