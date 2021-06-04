package com.cj4dplex.socket.intergrated;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Print {

	public static void main(String[] args) {
		final File targetDirectory = new File("D:\\NetworkingExample\\IntergratedServer\\src");
		
		final List<File> files = new ArrayList<File>();
		allFiles(files, targetDirectory);
		
		for(File file : files) {
			if(!"package-info.java".equals(file.getName())) {
//				System.out.println(file.getAbsolutePath());
				fileToConsole(file);
			}
		}
	}

	public static void allFiles(final List<File> files, final File targetDirectory) {
		final File[] fileList = targetDirectory.listFiles();
		for(File file : fileList) {
			if(file.isDirectory()) {
				allFiles(files, file);
			}else {
				files.add(file);
			}
		}
	}
	
	public static void fileToConsole(final File trgtFile) {
		System.out.println("***************************************************************");
		System.out.println(trgtFile.getAbsolutePath());
		
		String line = null;
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		final File kynFile = new File("d:\\", "kyn.txt");
		
		RandomAccessFile randomAccessFile = null;
		try {
			if(!kynFile.exists()) {
				kynFile.createNewFile();
			}
			randomAccessFile = new RandomAccessFile(kynFile, "rw");
			randomAccessFile.seek(randomAccessFile.length());
			randomAccessFile.writeBytes("***********************************\n");
			randomAccessFile.writeBytes(trgtFile.getAbsolutePath() + "\n");
			
			fileReader = new FileReader(trgtFile);
			bufferedReader = new BufferedReader(fileReader);
			
			while (null != (line = bufferedReader.readLine())) {
				System.out.println(line);
				randomAccessFile.writeBytes(line + "\n");
			}
			randomAccessFile.writeBytes("\n");
			randomAccessFile.writeBytes("\n");
			randomAccessFile.writeBytes("\n");
			randomAccessFile.writeBytes("\n");
			randomAccessFile.writeBytes("\n");
			
			randomAccessFile.close();
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
