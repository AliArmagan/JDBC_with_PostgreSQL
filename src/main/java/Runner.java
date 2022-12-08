public class Runner {
    public static void main(String[] args) {

            //1. Step: Registration to the driver
            //2. Step: Create connection with database
            JdbcUtil.connectToDatabase("localhost", "postgres", "postgres", "1234");

            //3. Step: Create statement
            JdbcUtil.createStatement();

            //4. Step: Execute the query
            JdbcUtil.createTable("Students", "name VARCHAR(20)", "id INT", "address VARCHAR(50)", "tel BIGINT");

            JdbcUtil.insertDataToTable("Students", "name 'John'","id 123","address 'Ankara'");


            // JdbcUtils.dropTable("Students");

            //5. Step: Close the connection and statement
            JdbcUtil.closeConnectionAndStatement();


        }
    }
