package org.nii.niis.niim.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;

@Component("csvUtil")
public class CSVUtil {

	public void writeCSV(String filePath, List<String[]> list){
		
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		CSVWriter cw = null;
		
		try{
			String folder = filePath.substring(0, filePath.lastIndexOf("/"));
			File file = new File(folder);
			
	        if(!file.exists()){
	        	file.mkdirs();
	        }
			
			fos = new FileOutputStream(filePath);
			osw = new OutputStreamWriter(fos, "EUC-KR");
			
            try{
            	cw = new CSVWriter(osw, ',', '"');
                cw.writeAll(list);
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                cw.close();
            }   
        }catch(Exception e){
            e.printStackTrace();
        }finally{
        	if(null != cw){ try{ cw.close(); }catch(Exception e){} }
        	if(null != osw){ try{ osw.close(); }catch(Exception e){} }
        	if(null != fos){ try{ fos.close(); }catch(Exception e){} }
        }
	}
}
