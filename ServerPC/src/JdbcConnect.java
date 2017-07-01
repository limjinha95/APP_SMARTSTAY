import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;                      

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
            String url = "jdbc:mysql://localhost:3306/smartstay";
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
        public String Login(String id,String pwd){
        	String loginData="";
        	try{
            rs=stmt.executeQuery("SELECT NAME, PNUM FROM user where ID='"+id+"' AND PWD=PASSWORD('"+pwd+"');");
            if(rs==null){
                return "-";  
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            if(rs.next())
            {
            	for(int i=0;i<numberColumn;i++)
            	{
            		columName[i]=rsmd.getColumnName(i+1);
            		loginData+=rs.getString(columName[i]);
            		loginData+="/";
            	}
            }
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        	return loginData;
        }
        public boolean isUniqueID(String id)throws SQLException{

            rs=stmt.executeQuery("SELECT * FROM user where ID='"+id+"';");
            if(rs==null)return true;
            else return false;
        }
        public boolean registUser(String id,String name, String pwd, String Pnum) throws SQLException{

                int check=stmt.executeUpdate("insert into user values('"+id+"', '"+name+"', PASSWORD('"+pwd+"'), '"+Pnum+";");
                if(check>0)return true;  
                else return false;
        }
        public boolean FriendPlus(String id,String Rnum) throws SQLException{

            int check=stmt.executeUpdate("insert into key values('"+Rnum+"','"+id+";");
            if(check>0)return true;  
            else return false;
        }
        public String search_ip(String OFFICECODE, String RNUM){
        	String ipdata="";
        	try{
            rs=stmt.executeQuery("SELECT IP FROM doorlock where OFFICECODE='"+OFFICECODE+"' AND RNUM = '"+RNUM+";");
            if(rs==null){
                return "-";  
            }
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            if(rs.next())
            {
            	columName[0]=rsmd.getColumnName(1);
        		ipdata+=rs.getString(columName[0]);
            }
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        	return ipdata;
        }
        public String Klist(String ID){
        	String KData="";
        	try{
            rs=stmt.executeQuery("select office.name, doorlock.rnum, reservation.startdate, reservation.enddate from ((reservation join doorlock on reservation.officecode = doorlock.officecode and reservation.rnum = doorlock.rnum) join usekey on reservation.IDX = usekey.reservationidx) join office on doorlock.officecode = office.officecode where usekey.userid='"+ID+"';");
            if(rs==null){
                return "-";  
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            
            while(rs.next())
            {
            	for(int i=0;i<numberColumn;i++)
            	{
            		columName[i]=rsmd.getColumnName(i+1);
            		KData+=rs.getString(columName[i]);
            		KData+="/";
            	}
            	KData+="|";
            }
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
            return KData;
        }
        public String RSC(String ID){
        	String RSCData="";
        	try{
            rs=stmt.executeQuery("select office.name, reservation.rnum, reservation.startdate, reservation.enddate from reservation join office on reservation.officecode = office.officecode where reservation.userid='"+ID+"';");
            if(rs==null){
                return "-";  
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            while(rs.next())
            {
            	for(int i=0;i<numberColumn;i++)
            	{
            		columName[i]=rsmd.getColumnName(i+1);
            		RSCData+=rs.getString(columName[i]);
            		RSCData+="/";
            	}
            	RSCData+="|";
            }
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
            return RSCData;
        }
        public String ROC(String Oname, String Rnum) throws SQLException{
        	String ROCData="";
        	try{
            rs=stmt.executeQuery("select office.name, reservation.Rnum, reservation.startdate, reservation.enddate from reservation join office on reservation.officecode = office.officecode where office.name='"+Oname+"' and reservation.rnum='"+Rnum+"';");
            if(rs==null){
                return "-";  
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            while(rs.next())
            {
            	for(int i=0;i<numberColumn;i++)
            	{
            		columName[i]=rsmd.getColumnName(i+1);
            		ROCData+=rs.getString(columName[i]);
            		ROCData+="/";
            	}
            	ROCData+="|";
            }
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
            return ROCData;
        }
        public String ARC(){
        	String ARCData="";
        	try{
            rs=stmt.executeQuery("select office.name, doorlock.rnum from doorlock join office on doorlock.officecode = office.officecode;");
            if(rs==null){
                return "-";  
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            while(rs.next())
            {
            	for(int i=0;i<numberColumn;i++)
            	{
            		columName[i]=rsmd.getColumnName(i+1);
            		ARCData+=rs.getString(columName[i]);
            		ARCData+="/";
            	}
            	ARCData+="|";
            }
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
            return ARCData;
        }
        public String CheckRidx(String officename, String rnum){
        	String CRdata="";
        	try{
        	rs=stmt.executeQuery("select idx from reservation where rnum='"+rnum+"' and officecode = (select officecode from office where name='"+officename+"');");
            if(rs==null){
                return "-";  
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            if(rs.next())
            {
            	columName[0]=rsmd.getColumnName(1);
        		CRdata+=rs.getString(columName[0]);
            }
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        	return CRdata;
        }
        public String CheckOfficeCode(String officename){
        	String COCdata="";
        	try
        	{
        	rs=stmt.executeQuery("select officecode from office where name='"+officename+"';");
            if(rs==null){
                return "-";  
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn];
            if(rs.next())
            {
            	columName[0]=rsmd.getColumnName(1);
        		COCdata+=rs.getString(columName[0]);
            }
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
            return COCdata;
        }
        public boolean RCC(String Ridx) throws SQLException{

            int check=stmt.executeUpdate("delete from reservation where idx = '"+Ridx+"';");
            if(check==0)return false;
            check=stmt.executeUpdate("delete from usekey where idx = '"+Ridx+"';");
            if(check==0)return false;
            else return true;
        }
        public boolean registReservation(String officecode,String rnum, String id, String start, String end) throws SQLException{

            int check=stmt.executeUpdate("insert into reservation (userid, startdate, enddate, officecode, rnum) values('"+id+"', '"+start+"', '"+end+"', '"+officecode+"', '"+rnum+";");
            if(check>0)return true;  
            else return false;
        }
}
