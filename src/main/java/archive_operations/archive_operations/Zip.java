package archive_operations.archive_operations;
import java.io.File;
import java.util.ArrayList;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class Zip {

	String destination;
	ZipFile zipFile;
	ZipParameters zipParameters;
	
	public Zip(String zipPath, String destination) throws ZipException {
		
		this.destination = destination;
		zipFile = new ZipFile(zipPath);
		zipParameters = new ZipParameters();
	}
	
	private void compressFiles() throws ZipException {
		ZipFile zipFile = new ZipFile("G:\\test.zip");
		ArrayList<File> fileList = new ArrayList<File>();
		fileList.add(new File("G:\\abc.txt"));
		fileList.add(new File("G:\\files\\bus.txt"));
		// Setting parameters
		ZipParameters zipParameters = new ZipParameters();
		zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
		zipParameters.setEncryptFiles(true);
		zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
		zipParameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		// Setting password
		zipParameters.setPassword("pass@123");

		zipFile.addFiles(fileList, zipParameters);
	}


	/**
	 * Method for creating password protected zip file
	 * @param sourcePath
	 * @throws ZipException
	 */
	void compressWithPassword(String password) throws ZipException{
		
		System.out.println("Destination " + destination);
		
		// Setting parameters
		zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
		zipParameters.setEncryptFiles(true);
		zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
		zipParameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		setPassword(password);

		zipFile.addFolder(destination, zipParameters);
	}
	
	
	void setPassword(String password) {
		zipParameters.setPassword(password);
	}

	/**
	 * Method for unzipping password protected file
	 * @param sourcePath
	 * @throws ZipException
	 */
	private void uncompressPasswordProtectedFiles(String sourcePath) throws ZipException{
		String destPath = getFileName(sourcePath);
		System.out.println("Destination " + destPath);
		ZipFile zipFile = new ZipFile(sourcePath);
		// If it is encrypted then provide password
		if(zipFile.isEncrypted()){
			zipFile.setPassword("pass@123");
		}
		zipFile.extractAll(destPath);
	}

	private String getFileName(String filePath){
		// Get the folder name from the zipped file by removing .zip extension
		return filePath.substring(0, filePath.lastIndexOf("."));
	}
}