package archive_operations.archive_operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
 
public class Jar {
 
	public static void unzipJar(String path, String destinationDir) throws IOException {
		File file = new File(path);
		JarFile jar = new JarFile(file);
 
		// fist get all directories,
		// then make those directory on the destination Path
		for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();) {
			JarEntry entry = (JarEntry) enums.nextElement();
 
			String fileName = destinationDir + File.separator + entry.getName();
			File f = new File(fileName);
 
			if (fileName.endsWith("/")) {
				f.mkdirs();
			}
 
		}
 
		//now create all files
		for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();) {
			JarEntry entry = (JarEntry) enums.nextElement();
 
			String fileName = destinationDir + File.separator + entry.getName();
			File f = new File(fileName);
 
			if (!fileName.endsWith("/")) {
				InputStream is = jar.getInputStream(entry);
				FileOutputStream fos = new FileOutputStream(f);
 
				// write contents of 'is' to 'fos'
				while (is.available() > 0) {
					fos.write(is.read());
				}
 
				fos.close();
				is.close();
			}
		}
	}
	
	void createJarArchive(String archiveFile, String jarPath) {
		int BUFFER_SIZE = 10240;
		
		File singleFolder = new File(jarPath);
		File[] tobeJared = new File[1];
		tobeJared[0] = singleFolder;
		
		try {
	      byte buffer[] = new byte[BUFFER_SIZE];
	      // Open archive file
	      FileOutputStream stream = new FileOutputStream(archiveFile);
	      JarOutputStream out = new JarOutputStream(stream, new Manifest());

	      for (int i = 0; i < tobeJared.length; i++) {
	        if (tobeJared[i] == null || !tobeJared[i].exists()
	            || tobeJared[i].isDirectory())
	          continue; // Just in case...
	        System.out.println("Adding " + tobeJared[i].getName());

	        // Add archive entry
	        JarEntry jarAdd = new JarEntry(tobeJared[i].getName());
	        jarAdd.setTime(tobeJared[i].lastModified());
	        out.putNextEntry(jarAdd);

	        // Write file to archive
	        FileInputStream in = new FileInputStream(tobeJared[i]);
	        while (true) {
	          int nRead = in.read(buffer, 0, buffer.length);
	          if (nRead <= 0)
	            break;
	          out.write(buffer, 0, nRead);
	        }
	        in.close();
	      }

	      out.close();
	      stream.close();
	      System.out.println("Adding completed OK");
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      System.out.println("Error: " + ex.getMessage());
	    }
	  }
}