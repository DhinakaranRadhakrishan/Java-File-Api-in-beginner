package com.java.handle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileOpration {

	private byte[] contend;
	private String fileName;
	private String path;

	private File file;
	private FileInputStream readFile;
	private FileOutputStream writeContent;

	public FileOpration() {
		this.fileName = new String();
		this.contend = null;
		this.file = null;
		this.readFile = null;
		this.writeContent = null;
	}

	private void creatFile() {
		this.file = new File(path);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createFile(String fileName, String path) {
		this.path = path;
		this.fileName = fileName;
		this.path += "\\" + this.fileName;
		creatFile();
	}

	public void writeContent(String content) throws FileNotFoundException {
		this.contend = content.getBytes();
		writeContent = new FileOutputStream(file);
		try {
			writeContent.write(contend);
			writeContent.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readFileContent(String filePath) throws IOException {
		if (file != null) {
			file = new File(filePath);
		}
		file= new File(filePath);
		File file = this.file;
		String fileContent = "";
		if (file.canExecute()) {
			try {
				readFile = new FileInputStream(file);
				int bytes;
				for (; (bytes = readFile.read()) != -1;) {
					fileContent += (char) bytes;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		return fileContent;
	}
	public void appendExContent(String filePath,String content) throws IOException {
		file = new File(filePath);
		String readContent ="";
		if(file.canRead()) {
			int bytes;
			readFile = new FileInputStream(file);
			for(;(bytes = readFile.read())!=-1;) {
				readContent +=(char)bytes;
			}
		}
		readContent+=" "+content;
		appendExContent(readContent);
	}
	private void appendExContent(String content) throws IOException {
		RandomAccessFile random = new RandomAccessFile(file, "rw");
		random.setLength(0);
		random.close();
		
		writeContent = new FileOutputStream(file);
		writeContent.write(content.getBytes());
	}
	public void appendConPosition(int index,String filePath,String content) throws IOException {
		file = new File(filePath);
		String readContent ="";
		if(file.canRead()) {
			int bytes;
			readFile = new FileInputStream(file);
			int i =0;
			for(i =0;(bytes = readFile.read())!=-1;i++) {
				readContent += (char)bytes;
			}
		}
		StringBuffer appConten = new StringBuffer(readContent);
		appConten.insert(index, content+" ");
		readContent = appConten.toString();
		appendExContent(readContent);

	}
	public void deleteFile(String Path) {
		file = new File(path);
		file.delete();
	}

}
