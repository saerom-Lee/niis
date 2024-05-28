package org.nii.niis.niim.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public abstract class FileSendClient {
	
	protected final int DEFAULT_BUFFER_SIZE = 1024 * 16;
	
	protected String serverIp;
	protected int serverPort;
	protected String serverNm;
	protected String niisIpPort;
	
	protected Socket socket;
	
	protected Map<String, Object> sendMap;
	
	public FileSendClient(String serverIp, int serverPort){
		this.serverIp   = serverIp;
		this.serverPort = serverPort;
	}
	
	public void fileSend(List<String> fileList, List<String> copyList){
		FileSender fs = new FileSender(fileList, copyList);
		fs.start();
	}
	
	public void setNiisIpPort(String niisIpPort){
		this.niisIpPort = niisIpPort;
	}
	
	public abstract void updateStatus(String approvalCde);
	public abstract void setService(Object service);
	
	protected class FileSender extends Thread {
		
		private List<String> fileList;
		private List<String> copyList;
		
		public FileSender(List<String> fileList, List<String> copyList){
			this.fileList    = fileList;
			this.copyList    = copyList;
		}
		
		@Override
		public void run(){
			try{
				//상태 업데이트
				updateStatus("9");
				
				for(int i=0; i<fileList.size(); i++){
					String filePath = fileList.get(i);
					String copyPath = copyList.get(i);
					
					File file = new File(filePath);
					
					if(!file.exists()){
						System.out.println("########### " + serverNm + " Upload " + filePath + " File Missing...");
						continue;
					}
					
					BufferedInputStream bis = null;
					DataOutputStream dos = null;
					
					DataInputStream dis = null;
					
					int cnt = 0;
					boolean retry = true;
					
					//전송이 되지 않을 경우 2회까지 재시도
					while(cnt < 2 && retry){
						try{
							double startTime = System.currentTimeMillis();
							
							long fileSize = file.length();
							long totalReads = 0;
							byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
							int reads;
							
							socket = new Socket(serverIp, serverPort);
							
							if(!socket.isConnected()){
								System.out.println("########### " + filePath + " Upload Socket Connect Error.");
							}
							
							bis = new BufferedInputStream(new FileInputStream(file));
							dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
							
							dos.writeUTF(copyPath);
							dos.writeLong(fileSize);
							dos.flush();
							
							while (totalReads < fileSize) {
								reads = bis.read(buffer);
								dos.write(buffer, 0, reads);
								dos.flush();
								totalReads += reads;
							}
							
							dis = new DataInputStream(socket.getInputStream());
							String result = dis.readUTF();
							if("ok".equalsIgnoreCase(result)){
								retry = false;
							}
							
							double endTime = System.currentTimeMillis();
							double diffTime = (endTime - startTime)/ 1000;;
							double transferSpeed = (fileSize / 1024)/ diffTime;
							
							//재시도 회수
							cnt++;
							System.out.println("susccess : " + result +" " + filePath + " upload time: " + diffTime+ " s speed: " + transferSpeed + " KB/s file: " + filePath);
						}catch(UnknownHostException e){
							e.printStackTrace();
							throw e;
						}catch(IOException e){
							e.printStackTrace();
							throw e;
						}finally{
							try { bis.close(); }catch(IOException e){ }
							try { dos.close(); }catch(IOException e){ }
							try { dis.close(); }catch(IOException e){ }
							try { socket.close(); }catch(IOException e){ }
						}
					}
					
					if(cnt >= 2 && retry){
						System.out.println("$$$$$ " + filePath + " 파일 전송에 실패하였습니다.");
					}
				}
				
				//상태 업데이트
				updateStatus("2");
			}catch(Exception e){
				//상태 업데이트
				updateStatus("8");
			}
		}
	}
}
