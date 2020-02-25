package validation;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.chainsys.payrollapp.exceptions.DBExceptions;
import com.chainsys.payrollapp.util.Connections;
import com.chainsys.payrollapp.util.ErrorMessages;

public class UserDetailValidation {
	
	public boolean emailValidation(String email) throws DBExceptions
	{
		boolean result = false;
		String sql = "select email from employee where email = ?";
		try(Connection con = Connections.connect();
				PreparedStatement pst = con.prepareStatement(sql);)
		{
			pst.setString(1,email);
			result = pst.execute();
		}
		catch(Exception e)
		{
			throw new DBExceptions(ErrorMessages.Error);
		}
			return result;
	}
	public boolean panValidation(String pan) throws DBExceptions
	{
		boolean result = false;
		String sql = "select pan_number from employee where pan = ?";
		try(Connection con = Connections.connect();
				PreparedStatement pst = con.prepareStatement(sql);)
		{
			pst.setString(1,pan);
			result = pst.execute();
		}
		catch(Exception e)
		{
			throw new DBExceptions(ErrorMessages.Error);
		}
			return result;
	}

}
