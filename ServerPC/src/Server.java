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
    		//�ȵ���̵�� �ڹٰ��� ��ſ��� utf���ڵ��� ��� ��ħǥ ��Ȱ�� ���ִ� ĳ���� ���ϰ� ������ ���Խ��� ������. ���ڿ������� \0�� ����� ��Ȱ
    		data+="\r\n";
    		byte[] sendByteArray = data.getBytes("UTF-8");
    		//������ ���� ���ϵ� ������ utf-8�� ���ڵ��� �����͸� �����Ѵ�.
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
                //�޾Ƶ��� �����͵��� ��� ��ɾ�/������1-������2-...-������n�� �����̱� ������ /��-�� �и��� ���ڿ��� ����Ѵ�.
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
                		//������ ����Ʈ�� ���� Ŭ���̾�Ʈ���� ���������� ������ ���̱� ������ �츮�� ��� ������ ���ϴ� ���� ������ ���� ���ϴ� ������ ã�Ƴ��� ������ ��û�� ������.
                		
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
                //1��n����� ���� �̸鼭 ���ÿ� �� Ŭ���̾�Ʈ�� �ٸ� Ŭ���̾�Ʈ�� ���� �޽��� ������ �� �ʿ�(������)�� �ʿ��ϱ� ������ �� Ŭ���̾�Ʈ���� �ּҸ� ���� ����Ʈ�� ������ ������ Ŭ���̾�Ʈ��� ����� �����Ѵ�.
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
        	//���� ���� ������ ���� �κ�
        	System.out.println("����Ǿ����ϴ�.");
            ServerSocket mainServerSocket = null;
            mainServerSocket = new ServerSocket();
            mainServerSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 4040));
            //������ ���θ� ip�ּҿ� ��Ʈ�� ���μ��� ���Ͽ� ���ε� �Ͽ� ����غ� �Ѵ�.
            //ip�� �ڵ����� �޾ƿ������� ��Ʈ�� �ӽ÷� ����.
            ConnectThread connectThread = new ConnectThread(mainServerSocket);
            connectThread.start();
        } catch (Exception e) {}
    }
}

