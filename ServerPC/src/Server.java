import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
class UserInfo {
    Socket serverSocket;
 
    UserInfo(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
 
class UserThread extends Thread {    
    Socket serverSocket;
    List<UserInfo> li = new ArrayList<UserInfo>();
 
    UserThread(Socket serverSocket, List li) {
        this.serverSocket = serverSocket;
        this.li = li;
    }
    public void sendmsg(String data, Socket soc) {
		try {
			byte[] sendByteArray = data.getBytes();
			OutputStream outputStream = soc.getOutputStream();
			outputStream.write(sendByteArray);
		} catch (Exception e) {
		}
	}
    @Override
    public void run() {
        try {
            while (true) {
                //
                InputStream inputStream = serverSocket.getInputStream();
                byte[] byteArray = new byte[256];
                int size = inputStream.read(byteArray);
                
                if (size == -1) {
                    break;
                }
                
                String sendMessage = new String(byteArray, 0, size, "UTF-8");
				System.out.println(sendMessage);
				String[] inputdata;
				inputdata = sendMessage.split("/");
				System.out.println(inputdata[0]+" "+inputdata[1]);
				String[] data = inputdata[1].split("-");
				for(int i=0;i<data.length;i++)
					System.out.print(data[i]+" ");
				System.out.println();
				JdbcConnect jc = new JdbcConnect("smartstay");

				if (inputdata[0].equals("L")) {
					System.out.println(" login");
					String loginData = jc.Login(data[0], data[1]);
					sendmsg(loginData,serverSocket);
				} else if (inputdata[0].equals("R")) {
					String RegistData;
					if (jc.isUniqueID(data[0])) {
						if (jc.registUser(data[0], data[1], data[2], data[3]))
							RegistData = "success";
						else
							RegistData = "failed";
					} else {
						RegistData = "duplication";
					}
					sendmsg(RegistData, serverSocket);
				} else if (inputdata[0].equals("D")) {
					String ip = jc.search_ip(data[0], data[1]);
					String doorData;
					if (ip.equals("-")) {
						doorData = "failed";
					} else {
						for (int i = 0; i < li.size(); i++) {
							if (li.get(i).serverSocket.getInetAddress().equals(ip)) {
								sendmsg("1", li.get(i).serverSocket);
								break;
							}
						}
						doorData = "success";
					}
					sendmsg(doorData, serverSocket);
				} else if (inputdata[0].equals("FP")) {
					int fcheck = 0;
					String faillist = "";
					for (int i = 1; i < data.length; i++) {
						if (jc.FriendPlus(data[i], data[0]) == false) {
							faillist += data[i];
							faillist += "/";
							fcheck++;
						}
					}
					if (fcheck > 0) {
						sendmsg(faillist, serverSocket);
					} else {
						sendmsg("success", serverSocket);
					}
				} else if (inputdata[0].equals("ROC")) {
					sendmsg(jc.ROC(data[0], data[1]), serverSocket);
				} else if (inputdata[0].equals("ARC")) {
					sendmsg(jc.RSC(data[0]), serverSocket);
				} else if (inputdata[0].equals("RCC")) {
					String Ridx = jc.CheckRidx(data[0], data[1]);
					if (!Ridx.equals("-")) {
						sendmsg("failed", serverSocket);
					} else {
						if (jc.RCC(Ridx))
							sendmsg("success", serverSocket);
						else
							sendmsg("failed", serverSocket);
					}
				} else if (inputdata[0].equals("RSV")) {
					String officeCode = jc.CheckOfficeCode(data[0]);
					if (officeCode.equals("-"))
						sendmsg("failed", serverSocket);
					else {
						if (jc.registReservation(data[2], data[3], data[4], officeCode, data[1]))
							sendmsg("success", serverSocket);
						else
							sendmsg("failed", serverSocket);
					}
				} else if (inputdata[0].equals("RSC")) {
					sendmsg(jc.RSC(data[0]), serverSocket);
				} else if (inputdata[0].equals("Klist")) {
					sendmsg(jc.Klist(data[0]), serverSocket);
				}
				jc.closeDB();
			}
        } catch (Exception e) {
            System.out.println("Client 접속종료");
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
                System.out.println("-Client 접속");
 
                li.add(new UserInfo(serverSocket));
                UserThread userThread = new UserThread(serverSocket, li);
                userThread.start();
 
            }
        } catch (Exception e) {}
    }
}//
 
public class Server {
    public static void main(String[] args) {        
        System.out.println("-SERVER 시작");
        
        try {
            ServerSocket mainServerSocket = null;
            mainServerSocket = new ServerSocket();
            mainServerSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 4040));
            
            ConnectThread connectThread = new ConnectThread(mainServerSocket);
            connectThread.start();
            
            Scanner input = new Scanner(System.in);
            int temp = input.nextInt();
        } catch (Exception e) {}
        
        System.out.println("-SERVER 종료");
    }
}//