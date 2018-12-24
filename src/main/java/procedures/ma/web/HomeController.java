package procedures.ma.web;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import procedures.ma.dao.CadreLegalRepository;
import procedures.ma.dao.CommentaireRepository;
import procedures.ma.dao.DeroulementRepository;
import procedures.ma.dao.DomaineRepository;
import procedures.ma.dao.ObjectifRepository;
import procedures.ma.dao.OperationRepository;
import procedures.ma.dao.ProceduresRepository;
import procedures.ma.dao.ProcessusRepository;
import procedures.ma.dao.RegleGestionRepository;
import procedures.ma.dao.RoleRepository;
import procedures.ma.dao.UserRepository;
import procedures.ma.dao.VueArchivageRepository;
import procedures.ma.dao.VueComptableRepository;
import procedures.ma.dao.VueMatriceRepository;
import procedures.ma.dao.VueSystemeRepository;
import procedures.ma.entities.CadreLegal;
import procedures.ma.entities.Commentaire;
import procedures.ma.entities.Credit;
import procedures.ma.entities.Debit;
import procedures.ma.entities.Deroulement;
import procedures.ma.entities.Domain;
import procedures.ma.entities.Objectif;
import procedures.ma.entities.Procedures;
import procedures.ma.entities.Processus;
import procedures.ma.entities.RegleGestion;
import procedures.ma.entities.Role;
import procedures.ma.entities.User;
import procedures.ma.entities.VueArchivage;
import procedures.ma.entities.VueComptable;
import procedures.ma.entities.VueMatrice;
import procedures.ma.entities.VueSysteme;

@Controller
public class HomeController {
	@Autowired
	private DomaineRepository domaineRepository;
	@Autowired
	private ProcessusRepository processusRepo;
	@Autowired
	private ProceduresRepository procedureRepo;
	@Autowired
	private DeroulementRepository deroulementRep;
	@Autowired
	private ObjectifRepository objectifRepo;
	@Autowired
	private CadreLegalRepository cadreRep;
	@Autowired
	private RegleGestionRepository regleRep;
	@Autowired
	private VueSystemeRepository vueSystemeRep;
	@Autowired
	private VueMatriceRepository vueMatriceRep;
	@Autowired
	private VueArchivageRepository vueArchivageRep;
	@Autowired
	private VueComptableRepository vueComptableRep;
	@Autowired
	private OperationRepository operationRep;
	@Autowired
	private CommentaireRepository commentaireRep;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRep;

	@RequestMapping(value={"/","/acceuil"},method=RequestMethod.GET)
	public String index(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails   userDetails = (UserDetails) auth.getPrincipal();
	    User user=userRepo.findOne(userDetails.getUsername());
	    Role role=roleRep.findOne("DBA");
	      if (user.getRoles().contains(role)) {
	    	  return "pagedba";
		}
	      else{
	    	  return "acceuil";
	      }
	    
	    
		
	}
	@RequestMapping(value="/403",method=RequestMethod.GET)
    public String error403() {
        return "error/403";
    }
	@Secured({"ROLE_ADMIN","ROLE_Consultant"})
	@RequestMapping(value={"/domaines"},method=RequestMethod.GET)
	public String getAllDomaines(Model model,@RequestParam(value="message",defaultValue="")String message){
		model.addAttribute("domaines", domaineRepository.findAll());
		model.addAttribute("successMessage", message);
		return "domaines";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjouterDomaine"},method=RequestMethod.GET)
	public String addDomaine(Model model){
		model.addAttribute("domaine", new Domain());
		return "domaines";

	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjouterDomaine"},method=RequestMethod.POST)
	public String saveDomaine(@Valid Domain d,BindingResult bindingResult,Model model){

	 
		if (bindingResult.hasErrors()) {
			return "domaines";
		} else {
			 domaineRepository.save(d);
			return "redirect:domaines?message='Domaine est bien ajouter'";

		}	
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/SupprimerDomaine"},method=RequestMethod.GET)
	public String supprimerDomaine(Model model){
		model.addAttribute("domainesAsupprimer",domaineRepository.findAll() );
		return "domaines";

	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/SupprimerDomaine"},method=RequestMethod.POST)
	public String deleteDmaine(Model model,@RequestParam("domaine") Long code){
        domaineRepository.delete(code);
        return "redirect:domaines?message='Domaine est bien supprimer'";
	}
	@Secured({"ROLE_ADMIN","ROLE_Consultant"})
	@RequestMapping(value={"/domaine"},method=RequestMethod.GET)
	public String getDomaine(Model model,@RequestParam("domain") String domain,
			@RequestParam("codeDomain") Long code){


		Domain d=domaineRepository.findOne(code);
		model.addAttribute("domain", d);

		return "domaine";
	}
	//AjoutProcessus
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjoutProcessus"},method=RequestMethod.GET)
	public String addProcessus(Model model,@RequestParam("domain") Long code
			,@RequestParam(value="message",defaultValue="")String message){
		model.addAttribute("domain", domaineRepository.findOne( code));
		model.addAttribute("successMessage", message);
		Processus p=new Processus();
		model.addAttribute("processus", p);
		return "domaine";

	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjoutProcessus"},method=RequestMethod.POST)
	public String saveProcessus(@Valid Processus p,BindingResult bindingResult,Model model){

		Long code=p.getDomaine().getCodeDomain();
		if (bindingResult.hasErrors()) {
            model.addAttribute("domain", p.getDomaine());
			return "domaine";
		} else {
			processusRepo.save(p);
			return "redirect:ListProcessus?message='Processus est ajoute'&domain="+code;

		}	
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/SupprimerProcessus"},method=RequestMethod.GET)
	public String supprimerProcessus(Model model,@RequestParam("domain") Long code){
		model.addAttribute("domain", domaineRepository.findOne( code));
		model.addAttribute("processusAsupprimer",domaineRepository.findOne(code).getProcessus() );
		return "domaine";

	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/SupprimerProcessus"},method=RequestMethod.POST)
	public String deleteProcessus(Model model,@RequestParam("processus") Long code,
			@RequestParam("domaine") Long codeDomaine){
        processusRepo.delete(code);
        return "redirect:ListProcessus?message='Domaine est bien supprimer'&domain="+codeDomaine;
	}
	// ListProcessus
	@Secured({"ROLE_ADMIN","ROLE_Consultant"})
	@RequestMapping(value={"/ListProcessus"},method=RequestMethod.GET)
	public String getALlProcessus(Model model,@RequestParam("domain") Long code,
			@RequestParam(value="message",defaultValue="")String message){
		// recuperer tous les processus du domaine
		Domain d=domaineRepository.findOne(code);
		model.addAttribute("domain", d);
		model.addAttribute("listProcessus",d.getProcessus());
		model.addAttribute("successMessage", message);
		return "domaine";

	}
	//.....................................//
	@Secured({"ROLE_ADMIN","ROLE_Consultant"})
	@RequestMapping(value={"/processus"},method=RequestMethod.GET)
	public String getProcessus(Model model,@RequestParam("processus") String processus,
			@RequestParam("codeProcessus") Long code){
		Processus p=processusRepo.findOne(code);
		model.addAttribute("processus", p);
		model.addAttribute("domain", p.getDomaine().getCodeDomain());

		return "procedures";
	}
	// ListProcedures
	    @Secured({"ROLE_ADMIN","ROLE_Consultant"})
		@RequestMapping(value={"/ListProcedures"},method=RequestMethod.GET)
		public String getALlProcedures(Model model,@RequestParam("processus") Long code,
				@RequestParam(value="message",defaultValue="")String message){
			// recuperer tous les processus du domaine
			Processus p=processusRepo.findOne(code);
			model.addAttribute("processus", p);
			model.addAttribute("successMessage", message);
			model.addAttribute("listProcedures",p.getProcedures());
			model.addAttribute("domain", p.getDomaine().getCodeDomain());
			return "procedures";

		}
	//AjoutProcedure
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjoutProcedure"},method=RequestMethod.GET)
	public String addProcedure(Model model,@RequestParam("processus") Long code,
			@RequestParam(value="message",defaultValue="")String message){
		model.addAttribute("processus", processusRepo.findOne( code));
		model.addAttribute("successMessage", message);

		Procedures p=new Procedures();
		model.addAttribute("procedure", p);
		return "procedures";

	}
	
		
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjoutProcedure"},method=RequestMethod.POST)
	public String saveProcedure(@Valid Procedures p,BindingResult bindingResult,Model model){

		 
		if (bindingResult.hasErrors()) {
			model.addAttribute("processus", p.getProcessus());
			return "procedures";
		} else {
			procedureRepo.save(p);
			return "redirect:ListProcedures?message='Procedure est ajoute'&processus="+p.getProcessus().getCodeProcessus();

		}	
	} 
		@Secured("ROLE_ADMIN")
		@RequestMapping(value={"/SupprimerProcedure"},method=RequestMethod.GET)
		public String supprimerProcedure(Model model,@RequestParam("processus") Long code){
			model.addAttribute("processus", processusRepo.findOne( code));
			model.addAttribute("proceduresAsupprimer",processusRepo.findOne( code).getProcedures() );
			return "procedures";

		}
		@Secured("ROLE_ADMIN")
		@RequestMapping(value={"/SupprimerProcedure"},method=RequestMethod.POST)
		public String deleteProcedure(Model model,@RequestParam("processus") Long code,@RequestParam("procedure") Long codeProcedure){
	        procedureRepo.delete(codeProcedure);
			return "redirect:ListProcedures?message='Procedure est bien supprimer'&processus="+code;


		}

	//	ajouterObjectif
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/ajouterObjectif"},method=RequestMethod.GET)
	public String addObjectif(Model model,
			@RequestParam(value="procedure") Long codeProcedure){
		Procedures p=procedureRepo.findOne(codeProcedure);
		model.addAttribute("procedure", p);
		model.addAttribute("objectif", new Objectif());
		return "modifierObjectif";

	}
	//AjouterObjectif
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjouterObjectif"},method=RequestMethod.POST)
	public String saveObjectif(@Valid Objectif o,BindingResult bindingResult,Model model){

		if (bindingResult.hasErrors()) {

			model.addAttribute("procedure", o.getProcedure());
			return "modifierObjectif";
		} else {
			objectifRepo.save(o);
			Procedures p=procedureRepo.findOne(o.getProcedure().getCodeProcedure());
			p.setObjectif(o);
			procedureRepo.saveAndFlush(p);
			return "redirect:element?procedure="+o.getProcedure().getCodeProcedure()+"&element=objectif";
		}
	}

	//ajouterCadre
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/ajouterCadre"},method=RequestMethod.GET)
	public String addCadre(Model model,
			@RequestParam(value="procedure") String codeProcedure){
		Procedures p=procedureRepo.findOne(Long.parseLong(codeProcedure));
		model.addAttribute("procedure", p);
		model.addAttribute("cadreLegal", new CadreLegal());
		return "ajouterCadre";

	}
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjouterCadre"},method=RequestMethod.POST)
	public String saveCadre(@Valid CadreLegal c,BindingResult bindingResult,Model model){

		if (bindingResult.hasErrors()) {

			model.addAttribute("procedure", c.getProcedure());
			return "ajouterCadre";
		} else {

			// save cadre
			cadreRep.saveAndFlush(c);
	
			return "redirect:element?procedure="+c.getProcedure().getCodeProcedure()+"&element=objectif";
		}
	}
	//ajouterRegle
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/ajouterRegle"},method=RequestMethod.GET)
	public String addRegle(Model model,
			@RequestParam(value="procedure") Long codeProcedure){
		Procedures p=procedureRepo.findOne(codeProcedure);
		model.addAttribute("procedure", p);
		model.addAttribute("regle", new RegleGestion());
		return "ajouterRegle";

	}
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjouterRegle"},method=RequestMethod.POST)
	public String saveRegle(@Valid RegleGestion r,BindingResult bindingResult,Model model){

		if (bindingResult.hasErrors()) {

			model.addAttribute("procedure", r.getProcedure());
			return "ajouterRegle";
		} else {

			// save regle de gestion
			 regleRep.save(r);
			return "redirect:element?procedure="+r.getProcedure().getCodeProcedure()+"&element=objectif";
		}
	}
	//ajouterDeroulement
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/ajouterDeroulement"},method=RequestMethod.GET)
	public String addDeroulement(Model model,
			@RequestParam(value="procedure") Long codeProcedure){
		Procedures p=procedureRepo.findOne(codeProcedure);
		model.addAttribute("procedure", p);
		model.addAttribute("deroulement", new Deroulement());
		return "modifierDeroulement";

	}
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjouterDeroulement"},method=RequestMethod.POST)
	public String saveDeroulement(@Valid Deroulement d,BindingResult bindingResult,Model model){

		if (bindingResult.hasErrors()) {

			model.addAttribute("procedure", d.getProcedure());
			return "modifierDeroulement";
		} else {

			  deroulementRep.save(d);
			return "redirect:element?procedure="+d.getProcedure().getCodeProcedure()+"&element=deroulement";
		}
	}
	//ajouterVueSysteme
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/ajouterVueSysteme"},method=RequestMethod.GET)
	public String addVueSysteme(Model model,
			@RequestParam(value="procedure") Long codeProcedure){
		Procedures p=procedureRepo.findOne(codeProcedure);
		model.addAttribute("procedure", p);
		model.addAttribute("vueSysteme", new VueSysteme());
		return "modifierVueSysteme";

	}
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjouterVueSysteme"},method=RequestMethod.POST)
	public String saveVueSysteme(@Valid VueSysteme vs,BindingResult bindingResult,Model model){

		if (bindingResult.hasErrors()) {

			model.addAttribute("procedure", vs.getProcedure());
			return "modifierVueSysteme";
		} else {
            vueSystemeRep.save(vs);
			return "redirect:element?procedure="+vs.getProcedure().getCodeProcedure()+"&element=vue Systeme";
		}
	}
	//ajouterVueComptable
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/ajouterVueComptable"},method=RequestMethod.GET)
	public String addVueComptable(Model model,
			@RequestParam(value="procedure") Long codeProcedure){
		Procedures p=procedureRepo.findOne(codeProcedure);
		model.addAttribute("procedure", p);
		model.addAttribute("vueComptable", new VueComptable());
		return "modifierVueComptable";

	}
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjouterVueComptable"},method=RequestMethod.POST)
	public String saveVueComptable(@Valid VueComptable vc,BindingResult bindingResult,Model model){

		if (bindingResult.hasErrors()) {

			model.addAttribute("procedure", vc.getProcedure());
			return "modifierVueComptable";
		} else {
            vueComptableRep.save(vc);
			return "redirect:element?procedure="+vc.getProcedure().getCodeProcedure()+"&element=vue Comptable";
		}
	}
	// ajouterCredit
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/ajouterCredit"},method=RequestMethod.GET)
	public String addCredit(Model model,
			@RequestParam(value="vueComptable") Long codeVC){
		VueComptable vc=vueComptableRep.findOne(codeVC);
		model.addAttribute("vueComptable", vc);
		model.addAttribute("credit", new Credit());
		return "ajouterCredit";

	}
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjouterCredit"},method=RequestMethod.POST)
	public String saveCredit(@Valid Credit c,BindingResult bindingResult,Model model){

		if (bindingResult.hasErrors()) {

			model.addAttribute("vueComptable", c.getVueComptable());
			return "ajouterCredit";
		} else {
            operationRep.save(c);
			return "redirect:element?procedure="+c.getVueComptable().getProcedure().getCodeProcedure()+"&element=vue Comptable";
		}
	}
	// ajouterDebit
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/ajouterDebit"},method=RequestMethod.GET)
	public String addDebit(Model model,
			@RequestParam(value="vueComptable") Long codeVC){
		VueComptable vc=vueComptableRep.findOne(codeVC);
		model.addAttribute("vueComptable", vc);
		model.addAttribute("debit", new Debit());
		return "ajouterDebit";

	}
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjouterDebit"},method=RequestMethod.POST)
	public String saveDebit(@Valid Debit d,BindingResult bindingResult,Model model){

		if (bindingResult.hasErrors()) {

			model.addAttribute("vueComptable", d.getVueComptable());
			return "ajouterDebit";
		} else {
            operationRep.save(d);
			return "redirect:element?procedure="+d.getVueComptable().getProcedure().getCodeProcedure()+"&element=vue Comptable";
		}
	}
	//ajouterVueMatrice
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/ajouterVueMatrice"},method=RequestMethod.GET)
	public String addVueMatrice(Model model,
			@RequestParam(value="procedure") Long codeProcedure){
		Procedures p=procedureRepo.findOne(codeProcedure);
		model.addAttribute("procedure", p);
		model.addAttribute("vueMatrice", new VueMatrice());
		return "modifierVueMatrice";

	}
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjouterVueMatrice"},method=RequestMethod.POST)
	public String saveVueMatrice(@Valid VueMatrice vm,BindingResult bindingResult,Model model){

		if (bindingResult.hasErrors()) {

			model.addAttribute("procedure", vm.getProcedure());
			return "modifierVueMatrice";
		} else {
 
		    vueMatriceRep.save(vm);
			return "redirect:element?procedure="+vm.getProcedure().getCodeProcedure()+"&element=vue Matrice";
		}
	}
	//ajouterArchivage
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/ajouterVueArchivage"},method=RequestMethod.GET)
	public String addVueArchivage(Model model,
			@RequestParam(value="procedure") Long codeProcedure){
		Procedures p=procedureRepo.findOne(codeProcedure);
		model.addAttribute("procedure", p);
		model.addAttribute("vueArchivage", new VueArchivage());
		return "modifierVueArchivage";

	}
		@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/AjouterVueArchivage"},method=RequestMethod.POST)
	public String saveVueArchivage(@Valid VueArchivage va,BindingResult bindingResult,Model model){

		if (bindingResult.hasErrors()) {

			model.addAttribute("procedure", va.getProcedure());
			return "modifierVueArchivage";
		} else {
			vueArchivageRep.save(va);
			return "redirect:element?procedure="+va.getProcedure().getCodeProcedure()+"&element=vue Archivage";
		}
	}

	
	@Secured({"ROLE_ADMIN","ROLE_Consultant"})
	@RequestMapping(value={"/procedure"},method=RequestMethod.GET)
	public String getProcedure(Model model,@RequestParam("procedure") String procedure,
			@RequestParam("codeProcedure") Long code){
		
		Procedures p=procedureRepo.findOne(code);
		model.addAttribute("procedure", p);
		model.addAttribute("processus", p.getProcessus().getCodeProcessus());
		model.addAttribute("codeProcedure",code);
		return "procedure";
	}
	@Secured({"ROLE_ADMIN","ROLE_Consultant"})
	@RequestMapping(value={"/element"},method=RequestMethod.GET)
	public String getElement(Model model,@RequestParam("element") String element,
			@RequestParam("procedure")Long codeProcedure){
		String vue="";
		Procedures p=procedureRepo.findOne(codeProcedure);

		model.addAttribute("procedure", p);
		if (element.equals("objectif")) {
			vue="objectif";
		}
		if (element.equals("deroulement")) {
			model.addAttribute("deroulements", deroulementRep.findAll());
			vue="deroulement";	
		}
		if (element.equals("vue Systeme")) {
			vue="vueSysteme";	
		}
		if (element.equals("vue Matrice")) {
			vue="vueMatrice";	
		}
		if (element.equals("vue Archivage")) {
			vue="vueArchivage";	
		}
		if (element.equals("vue Comptable")) {
			vue="vueComptable";
		}

		return vue;
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/modifierObjectif"},method=RequestMethod.GET)
	public String updateObjectif(Model model,@RequestParam("procedure") Long procedure,
                                                    @RequestParam("id") Long id){
		model.addAttribute("procedure", procedureRepo.findOne(procedure));
		model.addAttribute("objectif", objectifRepo.findOne(id));
		return "modifierObjectif";
	}
	//modifierCadre
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/modifierCadre"},method=RequestMethod.GET)
	public String updateCadre(Model model,@RequestParam("procedure") Long procedure,
			                              @RequestParam("id") Long id){
		model.addAttribute("procedure", procedureRepo.findOne(procedure));
		model.addAttribute("cadreLegal", cadreRep.findOne(id));
		return "ajouterCadre";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/modifierRegle"},method=RequestMethod.GET)
	public String updateRegle(Model model,@RequestParam("procedure") Long procedure,
			                              @RequestParam("id") Long id){
		model.addAttribute("procedure", procedureRepo.findOne(procedure));
		model.addAttribute("regle", regleRep.findOne(id));
		return "ajouterRegle";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/modifierVueSysteme"},method=RequestMethod.GET)
	public String updateVueSysteme(Model model,@RequestParam("procedure") Long procedure,
                                                @RequestParam("id") Long id){
		model.addAttribute("procedure", procedureRepo.findOne(procedure));
		model.addAttribute("vueSysteme", vueSystemeRep.findOne(id));
		return "modifierVueSysteme";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/modifierVueMatrice"},method=RequestMethod.GET)
	public String updateVueMatrice(Model model,@RequestParam("procedure") Long procedure,
                                              @RequestParam("id") Long id){
		model.addAttribute("procedure", procedureRepo.findOne(procedure));
		model.addAttribute("vueMatrice", vueMatriceRep.findOne(id));
		return "modifierVueMatrice";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/modifierVueArchivage"},method=RequestMethod.GET)
	public String updateVueArchivage(Model model,@RequestParam("procedure") Long procedure,
                                       @RequestParam("id") Long id){
		model.addAttribute("procedure", procedureRepo.findOne(procedure));
		model.addAttribute("vueArchivage", vueArchivageRep.findOne(id));
		return "modifierVueArchivage";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/modifierVueComptable"},method=RequestMethod.GET)
	public String updateVueComptable(Model model,@RequestParam("procedure") Long procedure,
            @RequestParam("id") Long id){
		model.addAttribute("procedure", procedureRepo.findOne(procedure));
		model.addAttribute("vueComptable", vueComptableRep.findOne(id));
		return "modifierVueComptable";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/modifierOperation"},method=RequestMethod.GET)
	public String updateOperation(Model model,@RequestParam("vueComptable") Long vueComptable,
                                                 @RequestParam("id") Long id,
                                                 @RequestParam("type") String type){
		model.addAttribute("vueComptable", vueComptableRep.findOne(vueComptable));
		if (type.equals("Credit")) {
			model.addAttribute("credit", operationRep.findOne(id));
			return "ajouterCredit";
		}
		else{
			model.addAttribute("debit", operationRep.findOne(id));
			return "ajouterDebit";
			 
		}
		
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/modifierDeroulement"},method=RequestMethod.GET)
	public String updateDeroulement(Model model,@RequestParam("procedure") Long procedure,
            @RequestParam("id") Long id){
		model.addAttribute("procedure", procedureRepo.findOne(procedure));
		model.addAttribute("deroulement", deroulementRep.findOne(id));
		return "modifierDeroulement";
	}

// delete
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/deleteObjectif"},method=RequestMethod.GET)
	public String deleteObjectif(@RequestParam("id") Long id,
			@RequestParam("procedure") Long codeP ){
 
		objectifRepo.delete(id);
		return "redirect:element?procedure="+codeP+"&element=objectif";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/deleteCadreLegal"},method=RequestMethod.GET)
	public String deleteCadreLegal(@RequestParam("id") Long id,
			@RequestParam("procedure") Long codeP ){
 
		cadreRep.delete(id);
		return "redirect:element?procedure="+codeP+"&element=objectif";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"/deleteRegle"},method=RequestMethod.GET)
	public String deleteRegle(@RequestParam("id") Long id,
			@RequestParam("procedure") Long codeP ){
 
		regleRep.delete(id);
		return "redirect:element?procedure="+codeP+"&element=objectif";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"deleteDeroulement"},method=RequestMethod.GET)
	public String deleteDeroulement(@RequestParam("id") Long id,
			@RequestParam("procedure") Long codeP ){
 
		deroulementRep.delete(id);
		return "redirect:element?procedure="+codeP+"&element=deroulement";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"deleteVueSysteme"},method=RequestMethod.GET)
	public String deleteVueSysteme(@RequestParam("id") Long id,
			@RequestParam("procedure") Long codeP ){
 
		vueSystemeRep.delete(id);
		return "redirect:element?procedure="+codeP+"&element=vue Systeme";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"deleteVueMatrice"},method=RequestMethod.GET)
	public String deleteVueMatrice(@RequestParam("id") Long id,
			@RequestParam("procedure") Long codeP ){
 
		vueMatriceRep.delete(id);
		return "redirect:element?procedure="+codeP+"&element=vue Matrice";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"deleteVueArchivage"},method=RequestMethod.GET)
	public String deleteVueArchivage(@RequestParam("id") Long id,
			@RequestParam("procedure") Long codeP ){
 
		vueMatriceRep.delete(id);
		return "redirect:element?procedure="+codeP+"&element=vue Archivage";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(value={"deleteOperation"},method=RequestMethod.GET)
	public String deleteOperation(@RequestParam("id") Long id,
			@RequestParam("vueComptable") Long codeVc ){
 
		operationRep.delete(id);
		VueComptable vc=vueComptableRep.findOne(codeVc);
		return "redirect:element?procedure="+vc.getProcedure().getCodeProcedure()+"&element=vue Comptable";
	}
	@Secured({"ROLE_ADMIN","ROLE_Consultant"})
	@RequestMapping(value={"commentaires"},method=RequestMethod.GET)
	public String getCommentaires(Model model,
			            @RequestParam("procedure") Long code){
		
		Procedures p=procedureRepo.findOne(code);
		model.addAttribute("processus", p.getProcessus().getCodeProcessus());
		model.addAttribute("procedure", p);
		model.addAttribute("codeProcedure",code);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails   userDetails = (UserDetails) auth.getPrincipal();
	    User user=userRepo.findOne(userDetails.getUsername());
    	model.addAttribute("user", user);
	    Role role=roleRep.findOne("Consultant");
	    
	      if (user.getRoles().contains(role)) {
	    	  model.addAttribute("commentairesUser",commentaireRep.gelAllCommetaireByUser(user.getUsername(), p.getCodeProcedure()));
		  }
	      else{
	    	  model.addAttribute("commentaires",p.getCommentaires());
	      }
		
		return"procedure";
	}
	@Secured("ROLE_Consultant")
	@RequestMapping(value={"/ajouterCommentaire"},method=RequestMethod.GET)
	public String addCommentaire(Model model,
			@RequestParam(value="procedure") Long codeProcedure,
			@RequestParam(value="message",defaultValue="") String message){
		Procedures p=procedureRepo.findOne(codeProcedure);
		model.addAttribute("processus", p.getProcessus().getCodeProcessus());
		model.addAttribute("procedure", p);
		model.addAttribute("codeProcedure",codeProcedure);
		model.addAttribute("message",message);
		model.addAttribute("commentaire", new Commentaire());
		return "procedure";

	}
	@Secured("ROLE_Consultant")
	@RequestMapping(value={"/AjouterCommentaire"},method=RequestMethod.POST)
	public String saveCommentaire(@Valid Commentaire c,BindingResult bindingResult,Model model){

		if (bindingResult.hasErrors()) {
			model.addAttribute("processus", c.getProcedure().getProcessus().getCodeProcessus());
			model.addAttribute("procedure", c.getProcedure().getNom());
			model.addAttribute("codeProcedure", c.getProcedure().getCodeProcedure());
			return "procedure";
		} else {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails   userDetails = (UserDetails) auth.getPrincipal();
		    User user=userRepo.findOne(userDetails.getUsername());
			c.setUser(user);
			c.setDate(new Date());
			 commentaireRep.save(c);
			 String message="commentaire est bien ajouter";
			return "redirect:ajouterCommentaire?procedure="+c.getProcedure().getCodeProcedure()+"&message="+message;
		}
	}
		@Secured("ROLE_ADMIN")
		@RequestMapping(value={"/repondre"},method=RequestMethod.GET)
		public String addReponse(Model model,
				@RequestParam(value="codeCommentaire") Long codeCommentaire){
		 
			model.addAttribute("codeCommentaire", codeCommentaire);
			return "reponse";

		}
		@Secured("ROLE_ADMIN")
		@RequestMapping(value={"/Repondre"},method=RequestMethod.POST)
		public String saveReponse(@RequestParam(value="codeCommentaire") Long codeCommentaire,
				@RequestParam(value="reponse") String reponse,Model model){
                Commentaire c=commentaireRep.findOne(codeCommentaire);
                c.setReponse(reponse);
				 commentaireRep.saveAndFlush(c);
				return "redirect:commentaires?procedure="+c.getProcedure().getCodeProcedure();
		 
		}
		@Secured("ROLE_ADMIN")
		@RequestMapping(value={"/supprimer"},method=RequestMethod.GET)
		public String deleteReponse(@RequestParam(value="codeCommentaire") Long codeCommentaire,Model model){
                Commentaire c=commentaireRep.findOne(codeCommentaire);
                Procedures p=procedureRepo.findOne(c.getProcedure().getCodeProcedure());
				 commentaireRep.delete(codeCommentaire);
				return "redirect:commentaires?procedure="+p.getCodeProcedure();
		 
		}
		
		@GetMapping("/profile")
	    public String showProfile(Model model){
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails   userDetails = (UserDetails) auth.getPrincipal();
		    User user=userRepo.findOne(userDetails.getUsername());
	    	model.addAttribute("user", user);
		    Role role=roleRep.findOne("DBA");
		    
		      if (user.getRoles().contains(role)) {
		    	  return "profileDBA";
			}
	    	return "profile";
	    }
		
		@GetMapping("/modifieMotDePasse")
	    public String modifierPassword(Model model){
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails   userDetails = (UserDetails) auth.getPrincipal();
		    User user=userRepo.findOne(userDetails.getUsername());
	    	model.addAttribute("user", user);
		    Role role=roleRep.findOne("DBA");
		    
		      if (user.getRoles().contains(role)) {
		    	  return "modifierMotPasseDBA";
			}
		      else{
		    	  return "modifierMotPasse";
		      }
	    	
	    }
	    
	    @PostMapping("/modifieMotDePasse")
	    public String MoodiferMotPass(@RequestParam("ancienMotPass") String ancienMotPass,
	    		@RequestParam("username") String username,
	    		@RequestParam("nouveauMotPass") String nouveauMotPass,Model model
	    		){
	    	User user=userRepo.findOne(username);
	    	if (!user.getPassword().equals(ancienMotPass)) {
	    	    model.addAttribute("error", "mot de passe est  incorrect");
		    	model.addAttribute("user", user);
			    Role role=roleRep.findOne("DBA");
			    
			      if (user.getRoles().contains(role)) {
			    	  return "modifierMotPasseDBA";
				}
			      else{
			    	  return "modifierMotPasse";
			      }
			}
	    	else{
	    		user.setPassword(nouveauMotPass);
	    		userRepo.save(user);
	    	}
	    	return "redirect:/profile";
	    }
		
}
