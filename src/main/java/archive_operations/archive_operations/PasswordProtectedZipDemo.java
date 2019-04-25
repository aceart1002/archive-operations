package archive_operations.archive_operations;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class PasswordProtectedZipDemo {

 public static void main(String[] args) {
  
  PasswordProtectedZipDemo pzip = new PasswordProtectedZipDemo();
  try {
   pzip.compressWithPassword("G:\\files");
  } catch (ZipException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  /*try {
   pzip.unCompressPasswordProtectedFiles("G:\\files.zip");
  } catch (ZipException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }*/
 }
 
 /**
  * Method for creating password protected zip file
  * @param sourcePath
  * @throws ZipException
  */
 private void compressWithPassword(String sourcePath) throws ZipException{
 
  String destPath = sourcePath + ".zip";
  System.out.println("Destination " + destPath);
  ZipFile zipFile = new ZipFile(destPath);
  // Setting parameters
  ZipParameters zipParameters = new ZipParameters();
  zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
  zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
  zipParameters.setEncryptFiles(true);
  zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
  zipParameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
  // Setting password
  zipParameters.setPassword("pass@123");
       
  zipFile.addFolder(sourcePath, zipParameters);
 }
 
 /**
  * Method for unzipping password protected file
  * @param sourcePath
  * @throws ZipException
  */
 private void unCompressPasswordProtectedFiles(String sourcePath) throws ZipException{
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