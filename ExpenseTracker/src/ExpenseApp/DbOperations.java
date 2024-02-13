package ExpenseApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class DbOperations {
	private static String host = "jdbc:mysql://localhost:3306/expensedb";
	private static String userName = "root";
	private static String userPassword = "Whynotmee@2743";

	private static Connection toCreateConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		try {
			Connection con = DriverManager.getConnection(host, userName, userPassword);
			return con;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	public static void toCreateAExpense(ExpenseClass obj) {
		try {
			Connection con = toCreateConnection();
			String query = "Insert into expense(expenseName,expenseTotal,expenseDate,expenseType) values (?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, obj.getExpenseName());
			stmt.setFloat(2, obj.getExpenseTotal());
			stmt.setString(3, obj.getExpenseDate());
			stmt.setString(4, obj.getExpenseType());

			stmt.executeUpdate();
			con.close();
			System.out.println("\n\nExpense Creation is completed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<Integer, ExpenseClass> toDisplayExpenses() {
		HashMap<Integer, ExpenseClass> expenseData = new HashMap<>();
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from expense");

			ResultSet rowData = stmt.executeQuery();

			while (rowData.next()) {
				ExpenseClass obj = new ExpenseClass();
				obj.setExpenseID(rowData.getInt(1));
				obj.setExpenseName(rowData.getString(2));
				obj.setExpenseTotal(rowData.getFloat(3));
				obj.setExpenseDate(rowData.getString(4));
				obj.setExpenseType(rowData.getString(5));

				expenseData.put(obj.getExpenseID(), obj);
			}
			con.close();
			return expenseData;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean idExists(int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from expense where expenseId = ?");
			stmt.setInt(1, id);
			ResultSet rowData = stmt.executeQuery();
			return rowData.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void toRemoveExpenseData(int id) {

		try {
			if (idExists(id)) {
				Connection con = toCreateConnection();
				PreparedStatement stmt = con.prepareStatement("Delete from expense where expenseId = ?");
				stmt.setInt(1, id);
				stmt.executeUpdate();
				con.close();
				System.out.println("Expense data is Removed Successfully ");
			} else {
				System.out.println("Expense data does not exist with this id !!!!!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateAExpenseName(String name, int id) {

		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update expense set expenseName = ? where expenseId = ?");
			stmt.setString(1, name);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
			System.out.println("Expense data is Updated Successfully ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateAExpenseTotal(Float total, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update expense set expenseTotal = ? where expenseId = ?");
			stmt.setFloat(1, total);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
			System.out.println("Expense data is Updated Successfully ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateAExpenseDate(String date, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update expense set expenseDate = ? where expenseId = ?");
			stmt.setString(1, date);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
			System.out.println("Expense data is Updated Successfully ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateAExpenseType(String type, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update expense set expenseType = ? where expenseId = ?");
			stmt.setString(1, type);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
			System.out.println("Expense data is Updated Successfully ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ExpenseClass toGetAExpenseData(int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from expense where expenseId = ?");
			stmt.setInt(1, id);
			ResultSet rowData = stmt.executeQuery();
			rowData.next();
			ExpenseClass obj = new ExpenseClass();
			obj.setExpenseID(rowData.getInt(1));
			obj.setExpenseName(rowData.getString(2));
			obj.setExpenseTotal(rowData.getFloat(3));
			obj.setExpenseDate(rowData.getString(4));
			obj.setExpenseType(rowData.getString(5));
			con.close();
			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
