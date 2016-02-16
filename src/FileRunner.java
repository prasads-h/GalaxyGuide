import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.gg.input.executor.InputExecutor;
import com.gg.log.AppLogger;
import com.gg.utils.StringUtils;


public class FileRunner {
	
	public static void main(String[] a){
		System.out.println("Running");
		BufferedReader ibr = null;
		BufferedWriter obw = null;
		try{
			if(a[0] == null){
				System.out.println("please mention input file as first argument");
				return;
			}				
			File inputFile = new File(a[0]);
			ibr = new BufferedReader(new FileReader(inputFile));
			File outputFile = new File("output.txt");
			obw = new BufferedWriter(new FileWriter(outputFile));
			String str = "";
			InputExecutor  inputExecutor = new InputExecutor();
			while((str = ibr.readLine()) != null){
				if(StringUtils.isEmpty(str))
					continue;
				obw.write(inputExecutor.execute(str));
				obw.write("\n");			
			}
		}catch(Exception e){
			AppLogger.error(e.getMessage(), e);
		}finally{
			try{
			if(ibr!= null)
				ibr.close();
			if(obw != null)
				obw.close();
			}catch(Exception ee){}finally{}
		}
		
	}

}
