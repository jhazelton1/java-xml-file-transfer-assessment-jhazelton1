package com.cooksys.xml_file_transfer;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Client {

	public static void main(String[] args) throws IOException {
		JAXBContext context = null;
		Marshaller m;
		
		File file = new File("HAI.txt");
		
		String name = "Jamil";
		Date date = new Date();
		byte[] bytes = null;

		try (FileInputStream fis = new FileInputStream(file);) {
			bytes = new byte[fis.available()];
			fis.read(bytes);
		} catch (Exception e) {
			System.out.println("File Input Not Found");
		}

		Student test = new Student(name, date, file.getName(), bytes);


		try (FileOutputStream fos = new FileOutputStream("newText.xml")) {
			context = context.newInstance(Student.class);
			m = context.createMarshaller();
			m.setProperty(m.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(test, fos);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		try (Socket client = new Socket("localhost", 8083);
				OutputStream bos = new BufferedOutputStream(new DataOutputStream(client.getOutputStream()));) {
			File xmlFile = new File("newText.xml");
			Files.copy(xmlFile.toPath(), client.getOutputStream());
		}

	}

}
