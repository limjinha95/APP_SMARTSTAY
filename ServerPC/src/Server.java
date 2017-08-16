import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class UserInfo {
    Socket serverSocket;
    UserInfo(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
 
class UserThread extends Thread {    
    Socket serverSocket;
    List<UserInfo> li = new ArrayList<UserInfo>();
 
    @SuppressWarnings("rawtypes")
	UserThread(Socket serverSocket, List li) {
        this.serverSocket = serverSocket;
        this.li = li;
    }
    public void sendmsg(String data, Socket soc)
    {
    	try
    	{
    		data+="\r\n";
    		byte[] sendByteArray = data.getBytes("UTF-8");
    		OutputStream outputStream = soc.getOutputStream();
    		outputStream.write(sendByteArray);
    	}catch (Exception e) {
        }
    }
    @Override
    public void run() {
        try {
            while (true) {
                InputStream inputStream = serverSocket.getInputStream();
                byte[] byteArray = new byte[256];
                int size = inputStream.read(byteArray);
                if (size == -1) break;
                String sendMessage = new String(byteArray, 0, size, "UTF-8");
                System.out.println(sendMessage);
                JSONParser parser = new JSONParser();
                Object obj = parser.parse( sendMessage );
                JSONObject jsonObj = (JSONObject)obj;
                String head = (String) jsonObj.get("head");
                JdbcConnect jc = new JdbcConnect("smartstay");
                if(head.equals("Login"))
                {
                	String Id = (String) jsonObj.get("ID");
                	String Pwd = (String) jsonObj.get("PWD");
                	String Token = (String) jsonObj.get("Token");
                	String loginData = jc.Login(Id, Pwd, Token);
                	System.out.println(loginData);
                	sendmsg(loginData,serverSocket);
                }
                else if(head.equals("SelectUser"))
                {
                	String Id = (String) jsonObj.get("ID");
                	String loginData = jc.SelectUser(Id);
                	System.out.println(loginData);
                	sendmsg(loginData,serverSocket);
                }
                else if(head.equals("ID"))
                {
                	String Id = (String) jsonObj.get("ID");
                	String IdData;
                	JSONObject jo = new JSONObject();
                	if(jc.IsUniqueID(Id))
                		jo.put("Unique", "Y");
                	else
                		jo.put("Unique", "N");
                	IdData = jo.toString();
                	System.out.println(IdData);
                	sendmsg(IdData,serverSocket);
                }
                else if(head.equals("Register"))
                {
                	String Id = (String) jsonObj.get("ID");
                	String Pwd = (String) jsonObj.get("PWD");
                	String Name = (String) jsonObj.get("Name");
                	String Pnum = (String) jsonObj.get("Pnum");
                	String Token = (String) jsonObj.get("Token");
                	boolean check=false;
                	do
                		check=jc.RegisterUser(Id, Name, Pwd, Pnum, Token);
            		while(check==false);
                	System.out.println("ok");
                }
                else if(head.equals("Delete"))
                {
                	String Id = (String) jsonObj.get("ID");
                	boolean check=false;
                	do
                		check=jc.DeleteUser(Id);
            		while(check==false);
                }
                else if(head.equals("ChangePwd"))
                {
                	String Id = (String) jsonObj.get("ID");
                	String Pwd = (String) jsonObj.get("PWD");
                	boolean check=false;
                	do
                		check=jc.ChangePwd(Id,Pwd);
            		while(check==false);
                	System.out.println("ok");
                }
                else if(head.equals("SearchOfficePnum"))
                {
                	String OfficeCode = (String) jsonObj.get("OfficeCode");
                	String PnumData = jc.OfficePnum(OfficeCode);
                	sendmsg(PnumData,serverSocket);	
                }
                else if(head.equals("Search_Room_Ip"))
                {
                	String OfficeCode = (String) jsonObj.get("OfficeCode");
                	String RoomNumber = (String) jsonObj.get("RoomNumber");
                	String ip=jc.Search_ip(OfficeCode, RoomNumber);
                	for (int i = 0; i < li.size(); i++) {
            			if (li.get(i).serverSocket.getInetAddress().equals(ip)) {
            				String openData = "open";
            				sendmsg(openData,li.get(i).serverSocket);
            				break;
            			}
            		}
            		MessageService ms = new MessageService();
            		String tok = jc.GetToken(OfficeCode, RoomNumber);
            		String [] token = tok.split("|");
            		for(int i=0;i<token.length;i++)
            			ms.push(token[i]);
                }
                else if(head.equals("FriendPlus"))
                {
                	String RoomNumber = (String) jsonObj.get("RoomNumber");
                	String FriendId = (String) jsonObj.get("FriendId");
                	boolean check=false;
                	do
                		check=jc.FriendPlus(FriendId, RoomNumber);
            		while(check==false);
                }
                else if(head.equals("RoomCheck"))
                {
                	String OfficeCode = (String) jsonObj.get("OfficeCode");
                	String RoomNumber = (String) jsonObj.get("RoomNumber");
                	sendmsg(jc.RoomCheck(OfficeCode,RoomNumber),serverSocket);
                }
                else if(head.equals("RoomList"))
                {
                	sendmsg(jc.RoomList(),serverSocket);
                }
                else if(head.equals("ReservationCancle"))
                {
                	String OfficeCode = (String) jsonObj.get("OfficeCode");
                	String RoomNumber = (String) jsonObj.get("RoomNumber");
                	String Ridx = jc.CheckRidx(OfficeCode, RoomNumber);
                	boolean check=false;
                	do
                		check=jc.ReservationCancel(Ridx);
            		while(check==false);
                }
                else if(head.equals("Reservation"))
                {
                	String OfficeName = (String) jsonObj.get("OfficeName");
                	String RoomNumber = (String) jsonObj.get("RoomNumber");
                	String ID = (String) jsonObj.get("ID");
                	String StartDate = (String) jsonObj.get("StartDate");
                	String EndDate = (String) jsonObj.get("EndDate");
                	String officeCode = jc.CheckOfficeCode(OfficeName);
                	boolean check=false;
                	do
                		check=jc.RegisterReservation(officeCode,RoomNumber,ID,StartDate,EndDate);
            		while(check==false);
                }
                else if(head.equals("ReservationCheck"))
                {
                	String ID = (String) jsonObj.get("ID");
                	sendmsg(jc.ReservationCheck(ID),serverSocket);
                }
                else if(head.equals("MyCoupon"))
                {
                	String ID = (String) jsonObj.get("ID");
                	sendmsg(jc.MyCoupon(ID),serverSocket);
                }
                else if(head.equals("MyKey"))
                {
                	String ID = (String) jsonObj.get("ID");
                	sendmsg(jc.MyKey(ID),serverSocket);
                }
                jc.closeDB();
            }
        } catch (Exception e) {
            for (int i = 0; i < li.size();   ) {
                if(serverSocket == li.get(i).serverSocket) {
                    li.remove(i);
                } else {
                    i++;
                }
            }
        }
    }
}
 
class ConnectThread extends Thread {
    ServerSocket mainServerSocket = null;
    List<UserInfo> li = new ArrayList<UserInfo>();
    ConnectThread(ServerSocket mainServerSocket) {
        this.mainServerSocket = mainServerSocket;
    }
 
    @Override
    public void run() {
        try {
            while (true) {
                Socket serverSocket = mainServerSocket.accept();
                System.out.println(serverSocket.getRemoteSocketAddress().toString());
                li.add(new UserInfo(serverSocket));
                UserThread userThread = new UserThread(serverSocket, li);
                userThread.start();
            }
        } catch (Exception e) {}
    }
}
 
public class Server {
	public static void main(String[] args) {        
        try {
            ServerSocket mainServerSocket = null;
            mainServerSocket = new ServerSocket();
            mainServerSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 9010));
            ConnectThread connectThread = new ConnectThread(mainServerSocket);
            connectThread.start();
        } catch (Exception e) {}
    }
}

