package net.madicorp.autodoc.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;


/**
 * @author Diop Sega
 *
 */
public class FileEditer {
	
	private File file;
	private ArrayList<String> args;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
	
	public ArrayList<String> getArgs() {
		return args;
	}

	public void setArgs(List<String> args) {
		this.args = (ArrayList<String>) args;
	}

	public ArrayList<String> readFile() {
		this.args = new ArrayList<String>();
		BufferedReader br = null;
		 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(this.file));
 
			while ((sCurrentLine = br.readLine()) != null) {
				String [] values = sCurrentLine.split("}\\s+");
				if(values.length > 1)
				{
					if(values[0].startsWith("${"))
					{
						values[0] = values[0].replaceAll(".*\\{|\\}.*", "");
						this.args.add(values[0]+"/__/"+values[1]);
					}
				}
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return this.args;
	}
	public boolean saveFile(){
		try {
			  
			File file = this.file;
			String tpath = file.getAbsolutePath().replace(file.getName(),"");
			File dir = new File(tpath);
			if(! file.exists())
			{
				if(! dir.exists())
				{
					dir.mkdirs();
				}
				file.createNewFile();
			}
 
			String limites ="/__/";
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(String arg : this.args)
			{
			String [] targs = arg.split(limites);
			bw.write("${"+targs[0]+"} "+targs[1]+"\n");
			}
			bw.close();
 
			System.out.println("Done");
			return true;
 
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int getFolderItems(File folder)
	{
		int items = 0;
	    for (File file : folder.listFiles()) {
	        if (file.isFile())
	        	items ++;
	        else
	        	items +=getFolderItems(file);
	    }
	    return items;
	}
	
	public long getFolderLength(File folder)
	
	{
		long length = 0;
	    for (File file : folder.listFiles()) {
	        if (file.isFile())
	            length += file.length();
	        else
	            length += getFolderLength(file);
	    }
	    return length;
	}
	
	
	public static String getFileNameFromPart(final Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}

}
