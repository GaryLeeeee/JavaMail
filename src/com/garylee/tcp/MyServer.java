package com.garylee.tcp;

import com.garylee.dao.UserDao;
import com.garylee.domain.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer implements Protocol{
    ObjectInputStream objectInputStream;
	ObjectOutputStream objectOutputStream;
	public MyServer() {
		try {
			ServerSocket serverSocket = new ServerSocket(1024);
			System.out.println("port 1024 has started!");
			int count = 0;
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println(""+(++count)+"th user visited");
				objectInputStream = new ObjectInputStream(socket.getInputStream());
				objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				if(objectInputStream.readInt() == LOGIN)
//					System.out.println("测试成功");
					login();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void login() throws IOException{
		String name = objectInputStream.readUTF();
		String password = objectInputStream.readUTF();
		User user = null;
		if(new UserDao().getAdmin(name,password))
			user = new User(name, password);
		objectOutputStream.writeObject(user);
	}
	public static void main(String[] args) {
		new MyServer();
	}
}