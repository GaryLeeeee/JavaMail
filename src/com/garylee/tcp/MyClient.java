package com.garylee.tcp;

import com.garylee.domain.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MyClient implements Protocol{
    ObjectOutputStream objectOutputStream;
	ObjectInputStream objectInputStream;
	public MyClient() {
		try {
			//服务器公网ip
			Socket socket = new Socket("120.79.24.211", 1024);
//			Socket socket = new Socket("127.0.0.1", 1024);
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectInputStream = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public User login(String name, String password){
		User user = null;
		try {
			objectOutputStream.writeInt(LOGIN);
			objectOutputStream.flush();
			objectOutputStream.writeUTF(name);
			objectOutputStream.flush();
			objectOutputStream.writeUTF(password);
			objectOutputStream.flush();
			user = (User) objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public static void main(String[] args) {
		new MyClient();
	}
}