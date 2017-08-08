import java.sql.*;                      

public class JdbcConnect {
        private Connection con = null;
        private PreparedStatement psmt = null;
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
            //우선은 실험하던 내 컴퓨터 위주로 진행하였으니 지금은 각자 형편에 맞게 위를 수정해주고 대회가 다가오면 정해진 서버에 맞게 수정 요함.
            
            try {
                con = DriverManager.getConnection(url, id, pw);
                System.out.println("Connection Complete");
            }catch(SQLException e) {
                System.err.println("Error : " + e);
            }
        }
        public boolean closeDB(){
                try {
                        if(rs   !=null)rs.close();
                        if(psmt !=null)psmt.close();
                        if(con  !=null)con.close();
                        return true;
                } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                }
        }
        public String Login(String id,String pwd)throws SQLException{
        	String query="SELECT ID, NAME, PNUM FROM user where ID= ? AND PWD=PASSWORD( ? )";
        	psmt = con.prepareStatement(query);
        	psmt.setString(1, id);
        	psmt.setString(2, pwd);
            rs=psmt.executeQuery();
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
        public String OfficePnum(String OfficeCode)throws SQLException{
        	String query="SELECT OfficePnum FROM office where OFFICECODE= ?";
        	psmt = con.prepareStatement(query);
        	psmt.setString(1, OfficeCode);
            rs=psmt.executeQuery();
            if(rs.next()==false){
                return "-";  
            }
            String PnumData="";
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColumn = rsmd.getColumnCount();
            String[] columName = new String[numberColumn]; 
            for(int i=0;i<numberColumn;i++)
            	columName[i]=rsmd.getColumnName(i+1);
            for(int i=0;i<numberColumn;i++)
            {
            	PnumData+=rs.getString(columName[i]);
            	PnumData+="/";
            }
            return PnumData;
        }
        public boolean IsUniqueID(String id)throws SQLException{
        	String query="SELECT COUNT(*) FROM user where ID= ?";
        	psmt = con.prepareStatement(query);
        	psmt.setString(1, id);
            rs=psmt.executeQuery();
            int rowcount=0;
            if(rs.next()) {
                rowcount = rs.getInt(1);
            } 
            if(rowcount==0)return true;
            else return false;
        }
        public boolean RegisterUser(String id,String name, String pwd, String Pnum) throws SQLException{
	        	String query="insert into user values(?, ?, PASSWORD(?), ?)";
	        	psmt = con.prepareStatement(query);
	        	psmt.setString(1, id);
	        	psmt.setString(2, name);
	        	psmt.setString(3, pwd);
	        	psmt.setString(4, Pnum);
                int check=psmt.executeUpdate();
                if(check>0)return true;  
                else return false;
        }
        public boolean DeleteUser(String id) throws SQLException{
        	String query="delete from user where ID = ?";
        	psmt = con.prepareStatement(query);
        	psmt.setString(1, id);
            int check=psmt.executeUpdate();
            if(check>0)return true;  
            else return false;
        }
        public boolean ChangePwd(String id,String Pwd) throws SQLException{
        	String query="update user set PWD=PASSWORD(?) where ID = ?";
        	psmt = con.prepareStatement(query);
        	psmt.setString(2, id);
        	psmt.setString(1, Pwd);
            int check=psmt.executeUpdate();
            if(check>0)return true;  
            else return false;
        }
        public boolean ChangePnum(String id,String Pnum)throws SQLException{
        	String query="update user set Pnum=? where ID = ?";
        	psmt = con.prepareStatement(query);
        	psmt.setString(2, id);
        	psmt.setString(1, Pnum);
            int check=psmt.executeUpdate();
        	System.out.println(check+"");
            if(check>0)return true;  
            else return false;
        }
        public boolean FriendPlus(String id,String Rnum) throws SQLException{
        	String query="insert into key values(?,?)";
        	psmt = con.prepareStatement(query);
        	psmt.setString(1, Rnum);
        	psmt.setString(2, id);
            int check=psmt.executeUpdate();
            if(check>0)return true;  
            else return false;
        }
        public String Search_ip(String OFFICECODE, String RNUM) throws SQLException{
        	String query="SELECT IP FROM doorlock where OFFICECODE= ? AND RNUM = ?";
        	psmt = con.prepareStatement(query);
        	psmt.setString(1, OFFICECODE);
        	psmt.setString(2, RNUM);
            rs=psmt.executeQuery();
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
        public String ReservationCheck(String ID) throws SQLException{
        	String query="select office.name, doorlock.rnum, reservation.startdate, reservation.enddate from ((reservation join doorlock on reservation.officecode = doorlock.officecode and reservation.rnum = doorlock.rnum) join usekey on reservation.IDX = usekey.reservationidx) join office on doorlock.officecode = office.officecode where usekey.userid= ?";
        	psmt = con.prepareStatement(query);
        	psmt.setString(1, ID);
            rs=psmt.executeQuery();
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
        public String RoomCheck(String Oname, String Rnum) throws SQLException{
        	String query="select office.name, reservation.Rnum, reservation.startdate, reservation.enddate from reservation join office on reservation.officecode = office.officecode where office.name= ? and reservation.rnum= ?";
        	psmt = con.prepareStatement(query);
        	psmt.setString(1, Oname);
        	psmt.setString(2, Rnum);
            rs=psmt.executeQuery();
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
        public String RoomList() throws SQLException{
        	String query="select office.name, doorlock.rnum from doorlock join office on doorlock.officecode = office.officecode";
        	psmt = con.prepareStatement(query);
            rs=psmt.executeQuery();
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
        	String query="select idx from reservation where rnum= ? and officecode = (select officecode from office where name= ? )";
        	psmt = con.prepareStatement(query);
        	psmt.setString(1, rnum);
        	psmt.setString(2, officename);
            rs=psmt.executeQuery();
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
        	String query="select officecode from office where name= ?";
        	psmt = con.prepareStatement(query);
        	psmt.setString(1, officename);
            rs=psmt.executeQuery();
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
        public boolean ReservationCancel(String Ridx) throws SQLException{
        	String query="delete from reservation where idx = ?";
        	psmt = con.prepareStatement(query);
        	psmt.setString(1, Ridx);
            int check=psmt.executeUpdate();
            if(check==0)return false;
            else return true;
        }
        public boolean RegisterReservation(String officecode,String rnum, String id, String start, String end) throws SQLException{
        	String query="insert into reservation (userid, startdate, enddate, officecode, rnum) values( ? , ? , ? , ? , ? )";
        	psmt = con.prepareStatement(query);
        	psmt.setString(1, id);
        	psmt.setString(2, start);
        	psmt.setString(3, end);
        	psmt.setString(4, officecode);
        	psmt.setString(4, rnum);
            int check=psmt.executeUpdate();
            if(check>0)return true;  
            else return false;
        }
}
