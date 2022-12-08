import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
        Statement st = con.createStatement();

        //1.Example: Update the number of employees to 9999 if the company name is IBM by using prepared statement

        //1.Step: Create Prepared Statement Query
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";

        //2.Step: Create Prepared Statement Object
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3.Step: Assign the values by using 'setInt(), setString()...' methods
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");

        //4.Step: Execute the Query
        Integer numberOfRecordsUpdated = pst1.executeUpdate();
        System.out.println("numberOfRecordsUpdated = " + numberOfRecordsUpdated);

        //5.Step: See the table
        String sql2 = "SELECT*FROM companies";
        ResultSet resultSet = st.executeQuery(sql2);
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " -- " + resultSet.getString(2) + " -- " + resultSet.getInt(3));
        }

        //2.Example: Update the number of employees to 5555 if the company name is GOOGLE by using prepared statement

        pst1.setInt(1,5555);
        pst1.setString(2,"GOOGLE");

        Integer numberOfRecordsUpdated2 = pst1.executeUpdate();
        System.out.println("numberOfRecordsUpdated = " + numberOfRecordsUpdated);

        String sql3 = "SELECT*FROM companies";
        ResultSet resultSet2 = st.executeQuery(sql3);
        while (resultSet2.next()){
            System.out.println(resultSet2.getInt(1) + " -- " + resultSet2.getString(2) + " -- " + resultSet2.getInt(3));
        }


        con.close();
        st.close();
        pst1.close();

    }

}
