package com.cooksys.xml_file_transfer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ClientHandler implements Runnable {

	private Socket socket;
	private JAXBContext context = null;
	Student student;
	Unmarshaller um;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			context = JAXBContext.newInstance(Student.class);
			um = context.createUnmarshaller();
			student = (Student) um.unmarshal(socket.getInputStream());
			String directoryName = student.getUserName();
			String date = student.getFormattedDate();
			File directory = new File(directoryName);
			File dateDirectory = new File(directoryName + "/" + date);
			File file = new File(directoryName + "/" + date + "/" + student.getFileName());
			directory.mkdir();
			dateDirectory.mkdir();

			if (!file.exists()) {
				try (FileOutputStream fos = new FileOutputStream(file);) {
					fos.write(student.getContents());
				} catch (IOException e) {

				}
			}
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}

	}

}
