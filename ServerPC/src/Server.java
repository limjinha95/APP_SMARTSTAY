import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.doosoo.MessagePush;
 
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
    		//안드로이드와 자바간의 통신에서 utf인코딩의 경우 마침표 역활을 해주는 캐리지 리턴과 개행을 포함시켜 보낸다. 문자열에서의 \0과 비슷한 역활
    		data+="\r\n";
    		byte[] sendByteArray = data.getBytes("UTF-8");
    		//데이터 간의 통일된 형식인 utf-8로 인코딩한 데이터를 전송한다.
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
                String[] inputdata;
                inputdata = sendMessage.split("/");
                String[] data = inputdata[1].split("-");
                //받아들일 데이터들은 모두 명령어/데이터1-데이터2-...-데이터n의 구조이기 때문에 /과-로 분리한 문자열을 사용한다.
                JdbcConnect jc = new JdbcConnect("smartstay");
                if(inputdata[0].equals("Login"))
                {
                	String loginData = jc.Login(data[0], data[1]);
                	System.out.println(loginData);
                	sendmsg(loginData,serverSocket);
                }
                else if(inputdata[0].equals("ID"))
                {
                	String IdData;
                	if(jc.IsUniqueID(data[0]))
                		IdData="Y";
                	else
                		IdData="N";
                	sendmsg(IdData,serverSocket);
                }
                else if(inputdata[0].equals("Regist"))
                {
                	String RegistData;
                	System.out.println("d");
            		if(jc.RegisterUser(data[0], data[1], data[2], data[3])) RegistData = "success";
            		else RegistData = "failed";
                	sendmsg(RegistData,serverSocket);
                	System.out.println(RegistData);
                }
                else if(inputdata[0].equals("Delete"))
                {
                	String DeleteData;
                	System.out.println("d");
            		if(jc.DeleteUser(data[0])) 
            			DeleteData = "success";
            		else 
            			DeleteData = "failed";
                	sendmsg(DeleteData,serverSocket);
                	System.out.println(DeleteData);
                }
                else if(inputdata[0].equals("ChangePwd"))
                {
                	String PwdData;
                	System.out.println("d");
            		if(jc.ChangePwd(data[0],data[1])) 
            			PwdData = "success";
            		else 
            			PwdData = "failed";
            		System.out.println(PwdData);
                	sendmsg(PwdData,serverSocket);
                	
                }
                else if(inputdata[0].equals("ChangePnum"))
                {
                	String PnumData;
                	System.out.println("d");
            		if(jc.ChangePnum(data[0],data[1])) 
            			PnumData = "success";
            		else 
            			PnumData = "failed";
            		System.out.println(PnumData);
                	sendmsg(PnumData,serverSocket);	
                }
                else if(inputdata[0].equals("SearchOfficePnum"))
                {
                	String PnumData = jc.OfficePnum(data[0]);
                	System.out.println(PnumData);
                	sendmsg(PnumData,serverSocket);	
                }
                else if(inputdata[0].equals("Search_Room_Ip"))
                {
                	String ip=jc.Search_ip(data[0], data[1]);
                	String doorData;
                	if(ip.equals("-"))
                		doorData="failed";
                	else
                	{
                		for (int i = 0; i < li.size(); i++) {
                			if (li.get(i).serverSocket.getInetAddress().equals(ip)) {
                				String openData = "open";
                				sendmsg(openData,li.get(i).serverSocket);
                				break;
                			}
                		}
                		//서버의 리스트에 각각 클라이언트들의 소켓정보가 저정될 것이기 때문에 우리는 디비에 저정된 원하는 방의 정보를 통해 원하는 소켓을 찾아내어 문열기 요청을 보낸다.
                		
                		MessageService ms = new MessageService();
                		String token = "clsKk9CWB0M:APA91bEwqzPZocFCX1QYQSjhaVNiZjunNlew2aKFdoeMQ6fXaW1X4yuIq5NGpS4EW8PjgaJynOXtgaRYJM4T8GmCCPNTfGnZA7VFCkipzZrLlYt_pEWFk4uVL6k4VzdkyaPQbZHsUTUB";
                		ms.push(token);
                		
                		doorData="success";
                	}
                	sendmsg(doorData,serverSocket);
                }
                else if(inputdata[0].equals("FriendPlus"))
                {
                	int fcheck=0;
                	String faillist="";
                	for(int i=1; i<data.length;i++)
                	{
                		if(jc.FriendPlus(data[i], data[0])==false)
                		{
                			faillist+=data[i];
                			faillist+="/";
                			fcheck++;
                		}
                	}
                	if(fcheck>0)
                    	sendmsg(faillist,serverSocket);
                	else
                    	sendmsg("success",serverSocket);
                }
                else if(inputdata[0].equals("RoomCheck"))
                {
                	sendmsg(jc.RoomCheck(data[0], data[1]),serverSocket);
                }
                else if(inputdata[0].equals("RoomList"))
                {
                	sendmsg(jc.RoomList(),serverSocket);
                }
                else if(inputdata[0].equals("ReservationCancle"))
                {
                	String Ridx = jc.CheckRidx(data[0], data[1]);
                	if(!Ridx.equals("-"))
                		sendmsg("failed",serverSocket);
                	else
                	{
                		if(jc.ReservationCancel(Ridx)) sendmsg("success",serverSocket);
                		else sendmsg("failed",serverSocket);
                	}
                }
                else if(inputdata[0].equals("Reservation"))
                {
                	String officeCode = jc.CheckOfficeCode(data[0]);
                	if(officeCode.equals("-")) sendmsg("failed",serverSocket);
                	else
                	{
                		if(jc.RegisterReservation(officeCode,data[1],data[2], data[3],data[4])) sendmsg("success",serverSocket);
                		else sendmsg("failed",serverSocket);
                	}
                }
                else if(inputdata[0].equals("ReservationCheck"))
                {
                	sendmsg(jc.ReservationCheck(data[0]),serverSocket);
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
}//
 
 
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
                //1대n통신의 서버 이면서 동시에 한 클라이언트가 다른 클라이언트에 직접 메시지 전송을 할 필요(문열기)가 필요하기 때문에 각 클라이언트들의 주소를 가질 리스트를 가지고 각각의 클라이언트들과 통신을 수행한다.
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
        	//최초 서버 가동시 시작 부분
        	System.out.println("실행되었습니다.");
            ServerSocket mainServerSocket = null;
            mainServerSocket = new ServerSocket();
            mainServerSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 4040));
            //서버의 내부망 ip주소와 포트를 메인서버 소켓에 바인딩 하여 통신준비를 한다.
            //ip는 자동으로 받아오겠지만 포트는 임시로 지정.
            ConnectThread connectThread = new ConnectThread(mainServerSocket);
            connectThread.start();
        } catch (Exception e) {}
    }
}

