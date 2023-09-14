package Aux_Package;

import java.sql.*;

import Student_Package.Student;

/**
 * T�tulo: Clase AuxClass
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 28 abr 2022
 */
public class AuxClass {
//CONSTANTES--------------------------------------------------------------------
//------------------------------------------------------------------------------
//CONEXIONES
//------------------------------------------------------------------------------
	/**
	 * Constante USERNAME
	 */
	public final static String USERNAME = Student.USERNAME;
	/**
	 * Constante PASSWORD
	 */
	public final static String PASSWORD = Student.PASSWORD;
	/**
	 * Constante CONNECTION_STRING
	 */
	public final static String CONNECTION_STRING = "jdbc:oracle:thin:@156.35.94.98:1521:desa19";
//------------------------------------------------------------------------------
//TIPOS
//------------------------------------------------------------------------------
	/**
	 * Constante INT_TYPE
	 */
	public final static int INT_TYPE = 0;
	/**
	 * Constante VARCHAR_TYPE
	 */
	public final static int VARCHAR_TYPE = 1;
	/**
	 * Constante DATE_TYPE
	 */
	public final static int DATE_TYPE = 2;
	/**
	 * Constante DOUBLE_TYPE
	 */
	public final static int DOUBLE_TYPE = 3; 
	/**
	 * Constante BOOLEAN_TYPE
	 */
	public final static int BOOLEAN_TYPE = 4; 
//ATRIBUTOS---------------------------------------------------------------------
	/**
	 * Atributo con
	 */
	private static Connection con;
	/**
	 * Atributo st
	 */
	private static Statement st;
	/**
	 * Atributo pst
	 */
	private static PreparedStatement pst;
	/**
	 * Atributo cs
	 */
	private static CallableStatement cs;
	
//M�TODOS PRINCIPALES-----------------------------------------------------------
//------------------------------------------------------------------------------
//QUERYS
//------------------------------------------------------------------------------
	/**
	 * M�todo que resuelve la query sin par�metros
	 * 
	 * @param query
	 * @return rs
	 * @throws SQLException
	 */
	public static ResultSet solveQuery(final String query) {
		return solveQuery(query, null);
	}

	/**
	 * M�todo que resuelve la query con un �nico par�metro
	 * 
	 * @param query
	 * @param parameter
	 * @return rs
	 * @throws SQLException
	 */
	public static ResultSet solveQuery(final String query, final Object parameter1) {
		return solveQuery(query, new Object[] {parameter1});
	}
	
	/**
	 * M�todo que resuelve la query con dos par�metros
	 * 
	 * @param query
	 * @param parameter1
	 * @param parameter2
	 * @return rs
	 * @throws SQLException
	 */
	public static ResultSet solveQuery(final String query, final Object parameter1, 
														   final Object parameter2) {
		return solveQuery(query, new Object[] {parameter1, parameter2});
	}
	
	/**
	 * M�todo que resuelve la query con tres par�metros
	 * 
	 * @param query
	 * @param parameter1
	 * @param parameter2
	 * @param parameter3
	 * @return rs
	 * @throws SQLException
	 */
	public static ResultSet solveQuery(final String query, final Object parameter1, 
														   final Object parameter2, 
														   final Object parameter3) {
		return solveQuery(query, new Object[] {parameter1, parameter2, parameter3});
	}
	
	/**
	 * M�todo que resuelve la query con cuatro par�metros
	 * 
	 * @param query
	 * @param parameter1
	 * @param parameter2
	 * @param parameter3
	 * @return rs
	 * @throws SQLException
	 */
	public static ResultSet solveQuery(final String query, final Object parameter1, 
														   final Object parameter2, 
														   final Object parameter3,
														   final Object parameter4) {
		return solveQuery(query, new Object[] {parameter1, parameter2, parameter3, parameter4});
	}
	
	/**
	 * M�todo que resuelve la query con un array de par�metros
	 * 
	 * @param query
	 * @param parameters
	 * @return rs
	 * @throws SQLException
	 */
	public static ResultSet solveQuery(final String query, final Object[] parameters) {		
		try {
			setConnection();					
			ResultSet rs;
			if (parameters==null) {
				setStatement();
				rs = st.executeQuery(query); 
			} else {
				setPreparedStatement(query);
				for (int i=0; i<parameters.length; i++) {
					pst.setObject(i+1, parameters[i]);
				}
				rs = pst.executeQuery();
			}
			return rs;
		} catch (SQLException exception) {
			System.out.println("SQLException recogida: ");
			while (exception!=null) {
				System.out.println("Mensaje: "+exception.getMessage());
				System.out.println("SQLState: "+exception.getSQLState());
				System.out.println("ErrorCode: "+exception.getErrorCode());
				exception=exception.getNextException();
				System.out.println(" ");
			}
			System.exit(0);
		}	
		return null;		
	}	
//------------------------------------------------------------------------------
//MODIFICACI�N DE TABLAS
//------------------------------------------------------------------------------
	/**
	 * M�todo que modifica la tabla con un �nico par�metro
	 * @param cad
	 * @param parameter1
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement changeTable(final String cad, final Object parameter1) {
		return changeTable(cad, new Object[] {parameter1});
	}
	
	/**
	 * M�todo que modifica la tabla con dos par�metros
	 * @param cad
	 * @param parameter1
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement changeTable(final String cad, final Object parameter1, 
																  final Object parameter2) {
		return changeTable(cad, new Object[] {parameter1, parameter2});
	}
	
	/**
	 * M�todo que modifica la tabla con tres par�metros
	 * @param cad
	 * @param parameter1
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement changeTable(final String cad, final Object parameter1, 
																  final Object parameter2,
																  final Object parameter3) {
		return changeTable(cad, new Object[] {parameter1, parameter2, parameter3});
	}
	
	/**
	 * M�todo que modifica la tabla con cuatro par�metros
	 * @param cad
	 * @param parameter1
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement changeTable(final String cad, final Object parameter1, 
																  final Object parameter2,
																  final Object parameter3,
																  final Object parameter4) {
		return changeTable(cad, new Object[] {parameter1, parameter2, parameter3, parameter4});
	}
	
	/**
	 * M�todo que modifica la tabla con un array de par�metros
	 * @param cad
	 * @param parameters
	 * @return pst
	 * @throws SQLException
	 */
	public static PreparedStatement changeTable(final String change, final Object[] parameters)  {
		try {
			setConnection();
			
			setPreparedStatement(change);
			for (int i=0; i<parameters.length; i++) {
				pst.setObject(i+1, parameters[i]);
			} 
		} catch (SQLException exception) {
			System.out.println("SQLException recogida: ");
			while (exception!=null) {
				System.out.println("Mensaje: "+exception.getMessage());
				System.out.println("SQLState: "+exception.getSQLState());
				System.out.println("ErrorCode: "+exception.getErrorCode());
				exception=exception.getNextException();
				System.out.println(" ");
			}
			System.exit(0);
		}
		return pst;
	}
//------------------------------------------------------------------------------
//LLAMADAS A PROCEDIMIENTOS/FUNCIONES
//------------------------------------------------------------------------------
	/**
	 * M�todo que llama a una funci�n o un procedimiento 
	 * @param call
	 * @return cs
	 * @throws SQLException
	 */
	public static CallableStatement callFunctionOrProcedure(final String call) {
		return callFunctionOrProcedure(call, null, null);
	}
	
	/**
	 * M�todo que llama a una funci�n o un procedimiento pasandole un �nico par�metro 
	 * de entrada y el n�mero de par�metros de salida
	 * @param call
	 * @param inParameter1
	 * @param numberOfOutParameters
	 * @return cs
	 * @throws SQLException
	 */
	public static CallableStatement callFunctionOrProcedure(final String call, final Object inParameter1, 
																			   final int type1) {
		return callFunctionOrProcedure(call, new Object[] {inParameter1}, new int[] {type1});
	}
	
	/**
	 * M�todo que llama a una funci�n o un procedimiento pasandole dos par�metros 
	 * de entrada y el n�mero de par�metros de salida
	 * @param call
	 * @param inParameter1
	 * @param numberOfOutParameters
	 * @return cs
	 * @throws SQLException
	 */
	public static CallableStatement callFunctionOrProcedure(final String call, final Object inParameter1,
																			   final Object inParameter2,
																			   final int[] types) {
		return callFunctionOrProcedure(call, new Object[] {inParameter1, inParameter2}, types);
	}
	
	/**
	 * M�todo que llama a una funci�n o un procedimiento pasandole tres par�metros 
	 * de entrada y el n�mero de par�metros de salida
	 * @param call
	 * @param inParameter1
	 * @param numberOfOutParameters
	 * @return cs
	 * @throws SQLException
	 */
	public static CallableStatement callFunctionOrProcedure(final String call, final Object inParameter1,
																			   final Object inParameter2,
																			   final Object inParameter3,
																			   final int[] types) {
		return callFunctionOrProcedure(call, new Object[] {inParameter1, inParameter2, inParameter3}, types);
	}
	
	/**
	 * M�todo que llama a una funci�n o un procedimiento pasandole tres par�metros 
	 * de entrada y el n�mero de par�metros de salida
	 * @param call
	 * @param inParameter1
	 * @param numberOfOutParameters
	 * @return cs
	 * @throws SQLException
	 */
	public static CallableStatement callFunctionOrProcedure(final String call, final Object inParameter1,
																			   final Object inParameter2,
																			   final Object inParameter3,
																			   final Object inParameter4,
																			   final int[] types) {
		return callFunctionOrProcedure(call, new Object[] {inParameter1, inParameter2, inParameter3, inParameter4}, types);
	}
	
	/**
	 * M�todo que llama a una funci�n o un procedimiento pasandole los par�metros 
	 * de entrada y el n�mero de par�metros de salida
	 * @param call
	 * @param inParameters
	 * @param numberOfOutParameters
	 * @return cs
	 * @throws SQLException
	 */
	public static CallableStatement callFunctionOrProcedure(final String call, final Object[] inParameters, final int[] types){		
		try {
			setConnection();
			
			setCallableStatement(call);
			if (call.charAt(1)=='?') {
				cs.registerOutParameter(1,  checkRegisterOutParameterType(types[0]));
				int inPLength=0;
				if (inParameters!=null) {
					inPLength=inParameters.length;
					for (int i=0; i<inPLength; i++) {
						cs.setObject(i+2, inParameters[i]);
					}			
				}	
				if (types.length>1) {
					for (int i=1; i<types.length; i++) {
						inPLength++;
						cs.registerOutParameter(inPLength,  checkRegisterOutParameterType(types[i]));						
					}
				}
			} else {
				int inPLength=0;
				if (inParameters!=null) {
					inPLength=inParameters.length;
					for (int i=0; i<inPLength; i++) {
						cs.setObject(i+1, inParameters[i]);
					}			
				}	
				if (types!=null && types.length>0) {
					for (int i=0; i<types.length; i++) {
						inPLength++;
						cs.registerOutParameter(inPLength,  checkRegisterOutParameterType(types[i]));						
					}
				}
			}
		} catch (SQLException exception) {
			System.out.println("SQLException recogida: ");
			while (exception!=null) {
				System.out.println("Mensaje: "+exception.getMessage());
				System.out.println("SQLState: "+exception.getSQLState());
				System.out.println("ErrorCode: "+exception.getErrorCode());
				exception=exception.getNextException();
				System.out.println(" ");				
			}
			System.exit(0);
		}
		return cs; 
	}
	
	/**
	 * M�todo que comprueba el tipo del par�metro de salida
	 * @param type
	 * @return type
	 */
	private static int checkRegisterOutParameterType(int type) {
		switch (type) {
			case INT_TYPE:
				return Types.INTEGER;
			case VARCHAR_TYPE:
				return Types.VARCHAR;
			case DATE_TYPE:
				return Types.DATE;
			case DOUBLE_TYPE:
				return Types.DOUBLE;
			case BOOLEAN_TYPE:
				return Types.BOOLEAN;
			default:
				return Types.OTHER;
		}
	}
	
//M�TODOS SECUNDARIOS-----------------------------------------------------------
	/**
	 * M�todo que setea la Connection
	 * @throws SQLException
	 */
	private static void setConnection() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		con = DriverManager.getConnection(CONNECTION_STRING,USERNAME,PASSWORD);
	}
	
	/**
	 * M�todo que setea el Statement
	 * @throws SQLException
	 */
	private static void setStatement() throws SQLException {
		st = getConnection().createStatement();
	}
	
	/**
	 * M�todo que sete el PreparedStatement con la query que se le pasa
	 * @param query
	 * @throws SQLException
	 */
	private static void setPreparedStatement(String query) throws SQLException{
		pst = getConnection().prepareStatement(query);
	}
	
	/**
	 * M�todo que sete el CallableStatement con la llamada a la funci�n o 
	 * procedimiento que se le pasa
	 * @param call
	 * @throws SQLException
	 */
	private static void setCallableStatement(String call) throws SQLException{
		cs = getConnection().prepareCall(call);
	}
	
	/**
	 * M�todo que devuelve la Connection
	 * @return con
	 */
	public static Connection getConnection() {
		return con;
	}
	
	/**
	 * M�todo que devuelve el Statement
	 * @return st
	 */
	public static Statement getStatement() {
		return st;
	}
	
	/**
	 * M�todo que devuelve el PreparedStatement
	 * @return pst
	 */
	public static PreparedStatement getPreparedStatement() {
		return pst;
	}
	
	/**
	 * M�todo que devuelve el CallableStatement
	 * @return cs
	 */
	public static CallableStatement getCallableStatement() {
		return cs;
	}
}
