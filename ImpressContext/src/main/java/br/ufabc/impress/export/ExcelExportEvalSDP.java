package br.ufabc.impress.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.ufabc.impress.facade.EvalSdpFacade;
import br.ufabc.impress.model.EvalSdp;

public class ExcelExportEvalSDP {
	
	private static EvalSdpFacade evalSdpFacade;
	
    public static void main( String[] args ){
    	
    	HSSFWorkbook wb = new HSSFWorkbook();
    	
    	int count = 0;
    	int col = 8;

        HSSFSheet sheet1 = wb.createSheet("Result");
        
        HSSFRow row = sheet1.createRow(0);
        //HSSFRow row = null;
        
        //List<EvalSdp> results = getEvalSdpFacade().listAll();
        List<EvalSdp> results = getEvalSdpFacade().listExcelSearch(0, null, null, null, null, null, null);
        
        row.createCell(count).setCellValue("ID");
        count++;
        row.createCell(count).setCellValue("Name");
        count++;
        row.createCell(count).setCellValue("Start");
        count++;
        row.createCell(count).setCellValue("Finish");
        count++;
        row.createCell(count).setCellValue("Duration");
        count++;
        row.createCell(count).setCellValue("Duration Mil");
        count++;
        row.createCell(count).setCellValue("Module");
        count++;
        row.createCell(count).setCellValue("Description");
        count++;
        
        for (int i = 0; i < results.size(); i++){
        	row = sheet1.createRow(i + 1);
        	
        	count = 0;
        	
            row.createCell(count).setCellValue(results.get(i).getId());
            count++;
            row.createCell(count).setCellValue(results.get(i).getName().split(" ")[1]);
            count++;
            row.createCell(count).setCellValue(results.get(i).getStart().toString());
            count++;
            
			if (results.get(i).getFinish() == null) {
				row.createCell(count).setCellValue("");
			} else {
				row.createCell(count).setCellValue(
						results.get(i).getFinish().toString());
			}
            count++;
            
			if (results.get(i).getDuration() == null) {
				row.createCell(count).setCellValue("");
			} else {
				row.createCell(count).setCellValue(
						results.get(i).getDuration().toString());
			}
            
            count++;
            
            row.createCell(count).setCellValue(results.get(i).getDurationMil());
            count++;            
            row.createCell(count).setCellValue(results.get(i).getModule());
            count++;
            row.createCell(count).setCellValue(results.get(i).getDescription());
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
        	stream = new FileOutputStream("/home/fabrizio/excel.xls");
			wb.write(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   	
    }
	
    
    private static EvalSdpFacade getEvalSdpFacade(){
    	if (evalSdpFacade == null){
    		evalSdpFacade = new EvalSdpFacade();
    	}
    	return evalSdpFacade;
    }

}
