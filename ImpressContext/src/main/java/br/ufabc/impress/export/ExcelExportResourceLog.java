package br.ufabc.impress.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.ufabc.impress.facade.EvalSdpFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.model.EvalSdp;
import br.ufabc.impress.model.ResourceLog;

public class ExcelExportResourceLog {
	
	private static ResourceLogFacade resourceLogFacade;
	
    public static void main( String[] args ){
    	
    	HSSFWorkbook wb = new HSSFWorkbook();
    	
    	int count = 0;
    	int col = 8;

        HSSFSheet sheet1 = wb.createSheet("Result");
        
        HSSFRow row = sheet1.createRow(0);
        //HSSFRow row = null;
        
        //List<EvalSdp> results = getEvalSdpFacade().listAll();
        List<ResourceLog> results = getResourceLogFacade().listAll();
        
        row.createCell(count).setCellValue("ID");
        count++;
        row.createCell(count).setCellValue("Resource");
        count++;
        row.createCell(count).setCellValue("Value");
        count++;
        row.createCell(count).setCellValue("Date");
        count++;
        
        
        for (int i = 0; i < results.size(); i++){
        	row = sheet1.createRow(i + 1);
        	
        	count = 0;
        	
            row.createCell(count).setCellValue(results.get(i).getId());
            count++;
            row.createCell(count).setCellValue(results.get(i).getResource().getId());
            count++;
            row.createCell(count).setCellValue(results.get(i).getResourceLogValue());
            count++;
                        
            row.createCell(count).setCellValue(results.get(i).getCreationDate());
            count++;            
            

        	
        }
        count = 0;
        
        
        //auto size columns
        for(int i = 0; i < col; i++){
        	sheet1.autoSizeColumn(i);
        }

        FileOutputStream stream = null;

        try {
        	//stream = new FileOutputStream("c:/planilha.xls");
        	stream = new FileOutputStream("/home/fabrizio/excelResourceLog.xls");
			wb.write(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   	
    }
	
    
    private static ResourceLogFacade getResourceLogFacade(){
    	if (resourceLogFacade == null){
    		resourceLogFacade = new ResourceLogFacade();
    	}
    	return resourceLogFacade;
    }

}
