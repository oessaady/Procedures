package procedures.ma.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import procedures.ma.dao.ProceduresRepository;
import procedures.ma.entities.Procedures;
import procedures.ma.export.GeneratePdfReport;

@Controller
public class ExportController {
	
	@Autowired
	public ProceduresRepository procedureRep;
	
	 @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_PDF_VALUE)
	    public ResponseEntity<InputStreamResource> citiesReport(Model model,
				@RequestParam(value="procedure") Long codeProcedure) throws IOException {

	     
           Procedures p=procedureRep.findOne(codeProcedure);
	        ByteArrayInputStream bis = GeneratePdfReport.citiesReport(p);

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
	        
	    }
	

}
