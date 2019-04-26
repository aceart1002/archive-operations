package archive_operations.archive_operations;

import java.io.File;
import java.io.IOException;

import net.lingala.zip4j.exception.ZipException;

public class Main {
	
	static String filesToPack = "arch\\0_1aaf02_2ef85d83_XXL.jpg";
	static String destination = "D:\\Programs\\Eclipse home\\Workstation";
	static String archivePath = destination + "\\arch.zip";
	static String password = "packing";
	
	public static void main(String[] args) {

		System.out.println("running");
		Operations.packToZipWithPassword(archivePath, destination, password);
//		Operations.addPasswordToZip(folderPath, password);
		
//		Operations.unzipJar(destination, archivePath);
//		Operations.createJarArchive(archivePath, destination + File.separator + filesToPack);
		
	}
	
	static void openExplorer(String path) throws IOException {
		Runtime.getRuntime().exec("explorer.exe /select," + path);
	}
}

class OtherMain {
	static Main m = new Main();
	
	public static void main(String[] args) throws IOException {
		m.openExplorer(m.archivePath);
	}
}

class Operations {
	
	static void packToZipWithPassword(String zipPath, String destination, String password) {
		Zip pzip;
		try {
			pzip = new Zip(zipPath, destination);
			pzip.compressWithPassword(password);
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void addPasswordToZip(String files, String password) {
//		Zip pzip;
//		try {
//			pzip = new Zip(files);
//			pzip.setPassword(password);
//		} catch (ZipException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	static void unzipJar(String dest, String jarPath) {
		Jar jar = new Jar();
		try {
			jar.unzipJar(jarPath, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void createJarArchive(String archiveFile, String tobeJared) {
		Jar jar = new Jar();
		
		jar.createJarArchive(archiveFile, tobeJared);
	}
	
}
