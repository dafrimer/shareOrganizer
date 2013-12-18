package shareOrganizer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String args[]){
	try{
		File filelist = new File("C:/Users/dfrimer/Desktop/list.txt");
		BufferedReader in = new BufferedReader(new FileReader(filelist));
		List<String> filenames = new ArrayList<String>();
		while((in.readLine()) != null){
		filenames.add(in.readLine());
		}
		in.close();
		System.out.println(filenames);
		MoveFiles(filenames);
		
	}
	catch (IOException e){
		e.printStackTrace();
	}

	}

	private static void MoveFiles(List<String> filenames) {
			//creating output error stream
		//final Charset ENCODING = StandardCharsets.UTF_8;
			File f = new File("C:/Users/dfrimer/Desktop/Output");
			try{
			if(f.createNewFile())  { 
					f.delete(); } else {f.createNewFile();}
			}catch(IOException b){}
			List<String> errorList = new ArrayList<String>();
			
			
		// TODO Auto-generated method stub
		for (String e: filenames){
			String filepathin= "Z:/Property Management Requests/" + e;
			String filepathout = "Z:/Supplier File/" + e;
			try{
				FileInputStream fin = new FileInputStream(filepathin);
				FileOutputStream fout = new FileOutputStream(filepathout);
			
			//Use streams to create corresponding channel objects
				FileChannel in = fin.getChannel();
				FileChannel out = fout.getChannel();
			// Allocate a low-level 8kb buffer for the copy
			
				long numbytes = in.size();  		//number of bytes in original file
				in.transferTo(0, numbytes,out);		//Transfer that amount to output channel
				in.close();
				out.close();
				fin.close();
				fout.close();
			
			
				File removeF = new File(filepathin);
				if(removeF.exists())	{ removeF.delete(); }
			}
			catch (FileNotFoundException d){
				System.err.print(d.toString());
				errorList.add(d.toString());
				continue;
			}
			catch (IOException d ){
				System.err.print(d.toString());
				errorList.add(d.toString());
				continue;
			}
		}
		
		try {
			@SuppressWarnings("resource")
			BufferedWriter out = new BufferedWriter(new FileWriter(f));
			for(String e: errorList){
			out.write(e);
			out.newLine();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
}
