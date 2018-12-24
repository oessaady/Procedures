package procedures.ma.export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import procedures.ma.entities.CadreLegal;
import procedures.ma.entities.Deroulement;
import procedures.ma.entities.Objectif;
import procedures.ma.entities.Operation;
import procedures.ma.entities.Procedures;
import procedures.ma.entities.RegleGestion;
import procedures.ma.entities.VueArchivage;
import procedures.ma.entities.VueComptable;
import procedures.ma.entities.VueMatrice;
import procedures.ma.entities.VueSysteme;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class GeneratePdfReport {
	

	public static ByteArrayInputStream citiesReport(Procedures p) {
		
		Objectif o=p.getObjectif();
		Collection<Deroulement> deroulements=p.getDeroulements();
		Collection<VueSysteme> vueSystemes=p.getVueSystemes();
		Collection<VueArchivage> vueArchivages=p.getVueArchivages();
		Collection<VueMatrice> vueMatrices=p.getVueMatrices();
		Collection<VueComptable> vueComptables=p.getVueComptables();

		 Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        try {
	        	Font bold = new Font(Font.FontFamily.HELVETICA, 11f, Font.BOLD);
	            Font normal = new Font(Font.FontFamily.HELVETICA, 11f, Font.NORMAL);
	            BaseColor myColor = WebColors.getRGBColor("#8FBC8F");
	            
	            
	            PdfPTable tableObjectif = new PdfPTable(2);
	            tableObjectif.setWidthPercentage(100);
	            tableObjectif.setWidths(new int[]{8, 4});

	            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	             Font titleFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.ORANGE);

	            PdfPCell hcell;
	            hcell = new PdfPCell(new Phrase("Objectif", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            hcell.setBackgroundColor(myColor);
	            tableObjectif.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Intervenants", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            hcell.setBackgroundColor(myColor);
	            tableObjectif.addCell(hcell);
	            
	            
                PdfPCell cellObjectif;

                cellObjectif = new PdfPCell(new Phrase(o.getDescription()));
                cellObjectif.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cellObjectif.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableObjectif.addCell(cellObjectif);

                 String internes = "\n"+o.getIntervenantInternes();
                String externes = "\n"+o.getIntervenantExternes()+"\n";
                Chunk chunk1 = new Chunk("Internes : ", bold);
                Phrase ph1 = new Phrase(chunk1);

                Chunk chunk2 = new Chunk(internes, normal);
                Phrase ph2 = new Phrase(chunk2);

                Chunk chunk3 = new Chunk("\nExternes : ", bold);
                Phrase ph3 = new Phrase(chunk3);

                Chunk chunk4 = new Chunk(externes, normal);
                Phrase ph4 = new Phrase(chunk4);

                Paragraph ph = new Paragraph();
                ph.add(ph1);
                ph.add(ph2);
                ph.add(ph3);
                ph.add(ph4);

                tableObjectif.addCell(ph);
 
	            
	            PdfPTable tableVueS = new PdfPTable(2);
	            tableVueS.setWidthPercentage(100);
	            tableVueS.setWidths(new int[]{4, 8});

 
	            PdfPCell VueScell;
	            VueScell = new PdfPCell(new Phrase("Application", headFont));
	            VueScell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            VueScell.setBackgroundColor(myColor);

	            tableVueS.addCell(VueScell);

	            VueScell = new PdfPCell(new Phrase("Traitements Informatique/ Fonctionnalités", headFont));
	            VueScell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            VueScell.setBackgroundColor(myColor);

	            tableVueS.addCell(VueScell);
 

             for (VueSysteme vs : vueSystemes) {
 
                    PdfPCell cell;
 
 	                cell = new PdfPCell(new Phrase(vs.getApplication()));
 	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
 	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 	                 tableVueS.addCell(cell);
 
	                cell = new PdfPCell(new Phrase(vs.getFonctionnalite()));
	                cell.setPaddingLeft(5);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                tableVueS.addCell(cell);
 
 	               
 	            }
             
             // vue Matrice de control

             PdfPTable tableVueC = new PdfPTable(6);
             tableVueC.setWidthPercentage(100);
             tableVueC.setWidths(new int[]{6,4,6,6,6,6});


	            PdfPCell VueCcell;
	            VueCcell = new PdfPCell(new Phrase("Contrôles", headFont));
	            VueCcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            VueCcell.setBackgroundColor(myColor);

	            tableVueC.addCell(VueCcell);

	            VueCcell = new PdfPCell(new Phrase("Auteur", headFont));
	            VueCcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            VueCcell.setBackgroundColor(myColor);

	            tableVueC.addCell(VueCcell);
	            
	            VueCcell = new PdfPCell(new Phrase("Support", headFont));
	            VueCcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            VueCcell.setBackgroundColor(myColor);

	            tableVueC.addCell(VueCcell);
	            VueCcell = new PdfPCell(new Phrase("Matérialisation", headFont));
	            VueCcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            VueCcell.setBackgroundColor(myColor);

	            tableVueC.addCell(VueCcell);
	            VueCcell = new PdfPCell(new Phrase("Périodicité", headFont));
	            VueCcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            VueCcell.setBackgroundColor(myColor);

	            tableVueC.addCell(VueCcell);
                 
	             
	            VueCcell = new PdfPCell(new Phrase("Finalité", headFont));
	            VueCcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            VueCcell.setBackgroundColor(myColor);

	            tableVueC.addCell(VueCcell);
	            
	            for (VueMatrice vc : vueMatrices) {
	            	 
                    PdfPCell cell;
 
 	                cell = new PdfPCell(new Phrase(vc.getControl()));
 	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
 	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 	               tableVueC.addCell(cell);
 
	                cell = new PdfPCell(new Phrase(vc.getAuteur()));
	                cell.setPaddingLeft(5);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                tableVueC.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(vc.getSupport()));
	                cell.setPaddingLeft(5);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                tableVueC.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(vc.getMaterialisation()));
	                cell.setPaddingLeft(5);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                tableVueC.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(vc.getPeriodicite()));
	                cell.setPaddingLeft(5);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                tableVueC.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase(vc.getFinalite()));
	                cell.setPaddingLeft(5);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                tableVueC.addCell(cell);
 
 	               
 	            }
	            
	            // vue Archivage
	            
	            PdfPTable tableVueA = new PdfPTable(7);
	            tableVueA.setWidthPercentage(100);
	            tableVueA.setWidths(new int[]{6,6,4,6,6,6,6});


		            PdfPCell VueAcell;
		            VueAcell = new PdfPCell(new Phrase("Documents", headFont));
		            VueAcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            VueAcell.setBackgroundColor(myColor);
		            VueAcell.setRowspan(2);
		            tableVueA.addCell(VueAcell);

		            VueAcell = new PdfPCell(new Phrase("Emet", headFont));
		            VueAcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            VueAcell.setBackgroundColor(myColor);
		            VueAcell.setRowspan(2);
		            tableVueA.addCell(VueAcell);
		            
		            VueAcell = new PdfPCell(new Phrase("Nbre d’exemp", headFont));
		            VueAcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            VueAcell.setBackgroundColor(myColor);
		            VueAcell.setRowspan(2);
		            tableVueA.addCell(VueAcell);
		            
		            VueAcell = new PdfPCell(new Phrase("Dest", headFont));
		            VueAcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            VueAcell.setBackgroundColor(myColor);
		            VueAcell.setRowspan(2);
		            tableVueA.addCell(VueAcell);
		            
		            VueAcell = new PdfPCell(new Phrase("Archivage", headFont));
		            VueAcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            VueAcell.setBackgroundColor(myColor);
		            VueAcell.setColspan(3);
		            tableVueA.addCell(VueAcell);
		            
		            VueAcell = new PdfPCell(new Phrase("Mode", headFont));
		            VueAcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            VueAcell.setBackgroundColor(myColor);
		            tableVueA.addCell(VueAcell);
	                 
		             
		            VueAcell = new PdfPCell(new Phrase("Fréquence", headFont));
		            VueAcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            VueAcell.setBackgroundColor(myColor);

		            tableVueA.addCell(VueAcell);
		            
		            VueAcell = new PdfPCell(new Phrase("Lieu", headFont));
		            VueAcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            VueAcell.setBackgroundColor(myColor);

		            tableVueA.addCell(VueAcell);
             // remplir tableau
		            for (VueArchivage va : vueArchivages) {
		            	 
	                    PdfPCell cell;
	 
	 	                cell = new PdfPCell(new Phrase(va.getDocument() ));
	 	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	               tableVueA.addCell(cell);
	 
		                cell = new PdfPCell(new Phrase( va.getEmeteur()));
		                cell.setPaddingLeft(5);
		                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		                tableVueA.addCell(cell);
		                
		                cell = new PdfPCell(new Phrase( va.getNbrExp()));
		                cell.setPaddingLeft(5);
		                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		                tableVueA.addCell(cell);
		                
		                cell = new PdfPCell(new Phrase( va.getDestinataire()));
		                cell.setPaddingLeft(5);
		                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		                tableVueA.addCell(cell);
		                
		                cell = new PdfPCell(new Phrase( va.getMode()));
		                cell.setPaddingLeft(5);
		                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		                tableVueA.addCell(cell);
		                
		                cell = new PdfPCell(new Phrase( va.getFrequence()));
		                cell.setPaddingLeft(5);
		                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		                tableVueA.addCell(cell);
		                
		                cell = new PdfPCell(new Phrase( va.getLieu()));
		                cell.setPaddingLeft(5);
		                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		                tableVueA.addCell(cell);
	 
	 	               
	 	            }
             
		            // deroulement
		            PdfPTable tableDeroul = new PdfPTable(5);
		            tableDeroul.setWidthPercentage(100);
		            tableDeroul.setWidths(new int[]{4,6,10,6,4});


			            PdfPCell VueDcell;
			            VueDcell = new PdfPCell(new Phrase("N°", headFont));
			            VueDcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            VueDcell.setBackgroundColor(myColor);
			            VueDcell.setRowspan(2);
			            tableDeroul.addCell(VueDcell);

			            VueDcell = new PdfPCell(new Phrase("Evénement Déclencheur", headFont));
			            VueDcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            VueDcell.setBackgroundColor(myColor);
			            tableDeroul.addCell(VueDcell);
			            
			            VueDcell = new PdfPCell(new Phrase("Etapes/Opérations", headFont));
			            VueDcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            VueDcell.setBackgroundColor(myColor);
			            tableDeroul.addCell(VueDcell);
			            
			            VueDcell = new PdfPCell(new Phrase("SI", headFont));
			            VueDcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            VueDcell.setBackgroundColor(myColor);
			            tableDeroul.addCell(VueDcell);
			            
			            VueDcell = new PdfPCell(new Phrase("Documents associés", headFont));
			            VueDcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            VueDcell.setBackgroundColor(myColor);
			            VueDcell.setRowspan(2);
			            tableDeroul.addCell(VueDcell);
			            PdfPCell VueActcell;
			            VueActcell = new PdfPCell(new Phrase("Acteurs", headFont));
			            VueActcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            VueActcell.setBackgroundColor(myColor);
			            tableDeroul.addCell(VueActcell);
		                 
			             
			            VueActcell = new PdfPCell(new Phrase("Descriptif de l’opération", headFont));
			            VueActcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            VueActcell.setBackgroundColor(myColor);
			            tableDeroul.addCell(VueActcell);
			            
			            VueActcell = new PdfPCell(new Phrase("Délai", headFont));
			            VueActcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            VueActcell.setBackgroundColor(myColor);
			            tableDeroul.addCell(VueActcell);
			          
			            // remplir tableau deroulement
			            
			            for (Deroulement dr : deroulements) {
			            	 
		                    PdfPCell cell;
		 
		 	                cell = new PdfPCell(new Phrase( dr.getNumero()));
		 	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		 	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	                cell.setRowspan(2);

		 	               tableDeroul.addCell(cell);
		 
			                cell = new PdfPCell(new Phrase( dr.getEvenementDeclencheur() ));
			                cell.setPaddingLeft(5);
			                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			                tableDeroul.addCell(cell);
			                
			                cell = new PdfPCell(new Phrase(dr.getOperation() ));
			                cell.setPaddingLeft(5);
			                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			                tableDeroul.addCell(cell);
			                
			                cell = new PdfPCell(new Phrase(  dr.getSi()));
			                cell.setPaddingLeft(5);
			                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			                tableDeroul.addCell(cell);
			                
			                cell = new PdfPCell(new Phrase(dr.getDocumentAssocie()  ));
			                cell.setPaddingLeft(5);
			                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			                cell.setRowspan(2);
			                tableDeroul.addCell(cell);
			                
			                PdfPCell cell2;
			                
			                cell2 = new PdfPCell(new Phrase(  dr.getActeurs()));
			                cell2.setPaddingLeft(5);
			                cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			                cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			                tableDeroul.addCell(cell2);
			                
			                cell2 = new PdfPCell(new Phrase( dr.getDescription()));
			                cell2.setPaddingLeft(5);
			                cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			                cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			                tableDeroul.addCell(cell2);
			                
			                cell2 = new PdfPCell(new Phrase( dr.getDelai()));
			                cell2.setPaddingLeft(5);
			                cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			                cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			                tableDeroul.addCell(cell2);
		 
		 	               
		 	            }
			            
			            
			            
			            
	            PdfWriter.getInstance(document, out);
	            document.open();
	            document.addTitle(p.getNom());
	            document.addCreationDate();
	            document.bottomMargin();
	            document.setPageCount(1);
	            document.addHeader(p.getProcessus().getDomaine().getNom(), p.getProcessus().getDomaine().getNom());
	            Phrase titre=new Phrase(p.getNom(),titleFont);
	            
	            document.add(titre );

	            document.add(new Phrase("\n"));

	            document.add(new Phrase("\n"));

 	            document.add(new Phrase("I.	Objectifs, Intervenants, contexte et règles de gestion",headFont));
	            document.add(tableObjectif);
	            document.add(new Phrase("\n"));
	            document.add(new Phrase("Cadre légal et réglementaire :",headFont));
	            document.add(new Phrase("\n"));
	            if(p.getCadresLegales().isEmpty()){
            		document.add(new Phrase("N/A"));
		            document.add(new Phrase("\n"));
            	}
	            else{
	            for(CadreLegal cl:p.getCadresLegales()){
	            	
	            	
	            	
		            document.add(new Phrase(cl.getDescription()));
		            document.add(new Phrase("\n"));
	            	}
	            }
	            
	            document.add(new Phrase("Règles de gestion :",headFont));
	            document.add(new Phrase("\n"));
	            
	            for(RegleGestion regle:p.getReglesGestions()){
		            document.add(new Phrase(regle.getDescription()));
		            document.add(new Phrase("\n"));

	            }
	            
	             document.newPage();
	             document.add(new Phrase("I.	Déroulement de la procédure",headFont));
		         document.add(new Phrase("\n"));
		         document.add(tableDeroul);
	            
	            
	             document.newPage();
	             document.add(new Phrase("Vue système de la procédure :",headFont));
		         document.add(new Phrase("\n"));
		         document.add(tableVueS);
	            
	            
	             document.newPage();
	             document.add(new Phrase("Vue archivage de la procédure :",headFont));
		         document.add(new Phrase("\n"));
	             document.add(tableVueA);
	            
	             
	            

	            
	            
	              document.newPage();
	             document.add(new Phrase("Vue comptable de la procédure :",headFont));
		         document.add(new Phrase("\n"));
		         if(vueComptables.isEmpty()){
	            		document.add(new Phrase("N/A"));
			            document.add(new Phrase("\n"));
	            	}
		         else{
	            for (VueComptable vc : vueComptables) {
	            	Phrase phrase=new Phrase(vc.getDescription());
	            	document.add(phrase);
	            	document.add(new Phrase("\n"));
                     
	            	for(Operation op:vc.getOperations()){
	            		  
	            	
	            	 
	            	 PdfPTable tableVueCompt = new PdfPTable(4);
	            	 tableVueCompt.setWidthPercentage(100);
	            	 tableVueCompt.setWidths(new int[]{4,6,10,4});


				            PdfPCell Vuecell;
				            Vuecell = new PdfPCell(new Phrase(op.toString()));
				            Vuecell.setHorizontalAlignment(Element.ALIGN_CENTER);
				            Vuecell.setBackgroundColor(myColor);
				            
				            tableVueCompt.addCell(Vuecell);

				            Vuecell = new PdfPCell(new Phrase(op.getNumeroCompte()));
				            Vuecell.setHorizontalAlignment(Element.ALIGN_CENTER);
 				            tableVueCompt.addCell(Vuecell);
				            
				        
				            Vuecell = new PdfPCell(new Phrase(op.getLibelle()));
				            Vuecell.setHorizontalAlignment(Element.ALIGN_CENTER);
  				            tableVueCompt.addCell(Vuecell);

				            Vuecell = new PdfPCell(new Phrase(op.getMontant()));
				            Vuecell.setHorizontalAlignment(Element.ALIGN_CENTER);
 				            tableVueCompt.addCell(Vuecell);
 				            
 				           document.add(tableVueCompt);
	            	}
	            	
	            	document.add(new Phrase("\n"));
	            	document.add(new Phrase("\n"));
	            
	              }
		         } 
	            document.newPage();
	            document.add(new Phrase("Vue matrice de contrôle :",headFont));
	            document.add(new Phrase("\n"));
	            document.add(tableVueC);
	            document.close();
	            
	        } catch (DocumentException ex) {
	        
	            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
	        }

	        return new ByteArrayInputStream(out.toByteArray());
	}

}
