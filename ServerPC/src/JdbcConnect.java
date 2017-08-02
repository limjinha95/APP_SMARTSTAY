import java.sql.*;                      

public class JdbcConnect {

        private Connection con = null;
        private Statement stmt = null;
        private ResultSet rs = null;
 
        public JdbcConnect(String dbName) throws Exception{
            try {
                //sql 드라이버를 로딩하는 문구입니다.
                Class.forName("org.gjt.mm.mysql.Driver");
                System.out.println("Driver Install Complete");
            }catch(ClassNotFoundException cnfe) {
                System.err.println("Error : " + cnfe);
            }
            
            Connection con = null;
            String url = "jdbc:mysql://localhost:3306/hack";
            String id = "root";
            String pw = "ckdgns1016!";
            
            try {
                con = DriverManager.getConnection(url, id, pw);
                stmt = con.createStatement();
                System.out.println("Connection Complete");
            }catch(SQLException e) {
                System.err.println("Error : " + e);
            }
        }
        public boolean closeDB(){
                try {
                        if(rs   !=null)rs.close();
                        if(stmt !=null)stmt.close();
                        if(con  !=null)con.close();
                        return true;
                } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                }
        }
        public String Login(String id,String pwd)throws SQLException{

            rs=stmt.executeQuery("SELECT ID, NAME, PNUM FROM user where ID='"+id+"' AND PWD=PASSWORD('"+pwd+"');");
            if(rs.next()==false){
                return "-";  
            }
            String loginData="";
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn]; 
            for(int i=0;i<numberColumn;i++)
            	columName[i]=rsmd.getColumnName(i+1);
            for(int i=0;i<numberColumn;i++)
            {
            	loginData+=rs.getString(columName[i]);
            	loginData+="/";
            }
            return loginData;
        }

        public boolean isUniqueID(String id)throws SQLException{
            rs=stmt.executeQuery("SELECT COUNT(*) FROM user where ID='"+id+"';");
            int rowcount=0;
            if(rs.next()) {
                rowcount = rs.getInt(1);
            } 
            if(rowcount==0)return true;
            else return false;
        }
        public boolean registUser(String id,String name, String pwd, String Pnum) throws SQLException{
                int check=stmt.executeUpdate("insert into user values('"+id+"', '"+name+"', PASSWORD('"+pwd+"'), '"+Pnum+"');");
                if(check>0)return true;  
                else return false;
        }
        public boolean deleteUser(String id) throws SQLException{
            int check=stmt.executeUpdate("delete from user where ID = '"+id+"';");
            if(check>0)return true;  
            else return false;
        }
        public boolean ChangePwd(String id,String Pwd) throws SQLException{
            int check=stmt.executeUpdate("update user set PWD=PASSWORD('"+Pwd+"') where ID = '"+id+"';");
            if(check>0)return true;  
            else return false;
        }
        public boolean ChangePnum(String id,String Pnum)throws SQLException{
        	int check=stmt.executeUpdate("update user set Pnum='"+Pnum+"' where ID = '"+id+"';");
        	System.out.println(check+"");
            if(check>0)return true;  
            else return false;
        }
        public boolean FriendPlus(String id,String Rnum) throws SQLException{

            int check=stmt.executeUpdate("insert into key values('"+Rnum+"','"+id+";");
            if(check>0)return true;  
            else return false;
        }
        public String search_ip(String OFFICECODE, String RNUM) throws SQLException{

            rs=stmt.executeQuery("SELECT IP FROM doorlock where OFFICECODE='"+OFFICECODE+"' AND RNUM = '"+RNUM+";");
            if(rs.next()==false){
                return "-";  
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            for(int i=0;i<numberColumn;i++)
            	columName[i]=rsmd.getColumnName(i+1);
            return rs.getString(columName[0]);
        }
        public String RSC(String ID) throws SQLException{

            rs=stmt.executeQuery("select office.name, doorlock.rnum, reservation.startdate, reservation.enddate from ((reservation join doorlock on reservation.officecode = doorlock.officecode and reservation.rnum = doorlock.rnum) join usekey on reservation.IDX = usekey.reservationidx) join office on doorlock.officecode = office.officecode where usekey.userid='"+ID+"';");
            if(rs.next()==false){
                return "-";  
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            for(int i=0;i<numberColumn;i++)
            	columName[i]=rsmd.getColumnName(i+1);
            String RSCData="";
            do
            {
            	for(int i=0;i<numberColumn;i++)
            	{
            		RSCData+=rs.getString(columName[i]);
            		RSCData+="/";
            	}
            	RSCData+="|";
            }while(rs.next());
            return RSCData;
        }
        public String ROC(String Oname, String Rnum) throws SQLException{

            rs=stmt.executeQuery("select office.name, reservation.Rnum, reservation.startdate, reservation.enddate from reservation join office on reservation.officecode = office.officecode where office.name='"+Oname+"' and reservation.rnum='"+Rnum+"';");
            if(rs.next()==false){
                return "-";  
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            for(int i=0;i<numberColumn;i++)
            	columName[i]=rsmd.getColumnName(i+1);
            String ROCData="";
            do
            {
            	for(int i=0;i<numberColumn;i++)
            	{
            		ROCData+=rs.getString(columName[i]);
            		ROCData+="/";
            	}
            	ROCData+="|";
            }while(rs.next());
            return ROCData;
        }
        public String ARC() throws SQLException{

            rs=stmt.executeQuery("select office.name, doorlock.rnum from doorlock join office on doorlock.officecode = office.officecode;");
            if(rs.next()==false){
                return "-";  
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            for(int i=0;i<numberColumn;i++)
            	columName[i]=rsmd.getColumnName(i+1);
            String ARCData="";
            do
            {
            	for(int i=0;i<numberColumn;i++)
            	{
            		ARCData+=rs.getString(columName[i]);
            		ARCData+="/";
            	}
            	ARCData+="|";
            }while(rs.next());
            return ARCData;
        }
        public String CheckRidx(String officename, String rnum) throws SQLException{

        	rs=stmt.executeQuery("select idx from reservation where rnum='"+rnum+"' and officecode = (select officecode from office where name='"+officename+"');");
            if(rs.next()==false){
                return "-";  
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            for(int i=0;i<numberColumn;i++)
            	columName[i]=rsmd.getColumnName(i+1);
            return rs.getString(columName[0]);
        }
        public String CheckOfficeCode(String officename) throws SQLException{

        	rs=stmt.executeQuery("select officecode from office where name='"+officename+"';");
            if(rs.next()==false){
                return "-";  
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            for(int i=0;i<numberColumn;i++)
            	columName[i]=rsmd.getColumnName(i+1);
            return rs.getString(columName[0]);
        }
        public boolean RCC(String Ridx) throws SQLException{

            int check=stmt.executeUpdate("delete from reservation where idx = '"+Ridx+"';");
            if(check==0)return false;
            check=stmt.executeUpdate("delete from usekey where idx = '"+Ridx+"';");
            if(check==0)return false;
            else return true;
        }
        public boolean registReservation(String officecode,String rnum, String id, String start, String end) throws SQLException{

            int check=stmt.executeUpdate("insert into reservation (userid, startdate, enddate, officecode, rnum) values('"+id+"', '"+start+"', '"+end+"', '"+officecode+"', '"+rnum+"');");
            if(check>0)return true;  
            else return false;
        }
}
