import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
        Statement st = con.createStatement();


    //1.Example: Find the company and number_of_employees whose number_of_employees is the second highest from the companies table
    //1st Way=>
    String sql1 = "Select company,number_of_employees from companies order by number_of_employees DESC offset 1 Row Fetch Next 1 Row Only";
    ResultSet resultSet1 = st.executeQuery(sql1);
    while (resultSet1.next()){
        System.out.println(resultSet1.getString("company" )+ " --> " + resultSet1.getInt("number_of_employees"));
        System.out.println(resultSet1.getString(1 )+ " --> " + resultSet1.getInt(2));
    }

    //2nd Way=> By using subQuery
    String sql2 ="SELECT company, number_of_employees\n" +
            "FROM companies\n" +
            "WHERE number_of_employees = (SELECT MAX(number_of_employees)\n" +
            "                                FROM companies\n" +
            "                                WHERE number_of_employees < (SELECT MAX(number_of_employees)\n" +
            "                                                             FROM companies))";
    ResultSet resultSet2 = st.executeQuery(sql2);
    while(resultSet2.next()){
        System.out.println(resultSet2.getString(1)+"--> "+resultSet2.getInt(2));
    }
        //2.Example: Find the company names and number of employees whose number of employees is less than the average number of employees
        String sql3 = "SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees<(SELECT AVG(number_of_employees)\n" +
                "                            FROM companies)";
        ResultSet resultSet3 = st.executeQuery(sql3);

        while (resultSet3.next()){

            System.out.println(resultSet3.getString(1)+"--"+resultSet3.getInt(2));

        }

        con.close();
        st.close();




    }
}
