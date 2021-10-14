package main;
import java.io.File;
/**
* Save the path to the folder which will be used to save the results
* @param  number number used to separate folders and files of results
* @param  file  path of the folder
*/
public class Save {
	public int number;
	public File file;
	
	
	public Save(File newFile){
		number=0;
		file=newFile;
		if ((!file.exists()) || (!file.isDirectory())){
			file.mkdir();
		}		
	}
	
	
	
	public void addnombre() {
		this.number++;
	}
	public File getFile() {
		return this.file;
	}
}