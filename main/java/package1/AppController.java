package package1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
	@Autowired
	private PocztyDao dao;
	@Autowired
	private AdresDao daoAdres;
	@Autowired
	private BiuroDAO daoBiura;
	@Autowired
	private PracownikDao daoPracownik;
	@Autowired
	private WynagrodzenieDao daoWynag;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		return "index";
	}
	@RequestMapping("/poczty")
	public String viewPocztyPage(Model model) {
		List<Poczta> listPoczty = dao.list();
		model.addAttribute("listPoczty", listPoczty);
		return "poczty";
	}
	
	@RequestMapping("/poczty/new")
	public String showNewForm(Model model) {
		Poczta poczta = new Poczta();
		model.addAttribute("poczta", poczta);
		return "new_form";
	}
	
	@RequestMapping(value = "/poczty/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("poczta") Poczta poczta) {
		dao.save(poczta);
		return "redirect:/poczty";
	}
	
	@RequestMapping("/poczty/edit/{nr_poczty}")
	public ModelAndView showEditForm(@PathVariable(name = "nr_poczty") int nr_poczty) {
		ModelAndView mav = new ModelAndView("edit_form");
		Poczta poczta = dao.get(nr_poczty);
		mav.addObject("poczta",poczta);
		return mav;
	}
	
	@RequestMapping(value = "/poczty/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("poczta") Poczta poczta) {
		dao.update(poczta);
		return "redirect:/poczty";
	}
	
	@RequestMapping("/poczty/delete/{nr_poczty}")
	public String delete(@PathVariable(name = "nr_poczty") int nr_poczty) {
		dao.delete(nr_poczty);
		return "redirect:/poczty";
	}
	
	@RequestMapping("/adresy")
	public String viewAdresyPage(Model model) {
		List<Adres> listAdresy = daoAdres.list();
		model.addAttribute("listAdresy", listAdresy);
		return "adresy";
	}
	
	@RequestMapping("/adresy/new")
	public String showNewAdresForm(Model model) {
		Adres adres = new Adres();
		model.addAttribute("adres", adres);
		return "new_adres_form";
	}
	
	@RequestMapping(value = "/adresy/save", method = RequestMethod.POST)
	public String saveAdres(@ModelAttribute("adres") Adres adres) {
		daoAdres.save(adres);
		return "redirect:/adresy";
	}
	
	@RequestMapping("/adresy/delete/{nr_adresu}")
	public String deleteAdres(@PathVariable(name = "nr_adresu") int nr_adresu) {
		daoAdres.delete(nr_adresu);
		return "redirect:/adresy";
	}
	
	@RequestMapping("/adresy/edit/{nr_adresu}")
	public ModelAndView showAdresEditForm(@PathVariable(name = "nr_adresu") int nr_adresu) {
		ModelAndView mav = new ModelAndView("edit_adres_form");
		Adres adres = daoAdres.get(nr_adresu);
		mav.addObject("adres",adres);
		return mav;
	}
	
	@RequestMapping(value = "/adresy/update", method = RequestMethod.POST)
	public String updateAdres(@ModelAttribute("adres") Adres adres) {
		daoAdres.update(adres);
		return "redirect:/adresy";
	}
	
	@RequestMapping("/biura")
	public String viewBiuraPage(Model model) {
		List<Biuro> listBiura = daoBiura.list();
		model.addAttribute("listBiura", listBiura);
		return "biura";
	}
	
	@RequestMapping("/biura/new")
	public String showNewBiuraForm(Model model) {
		Biuro biuro = new Biuro();
		model.addAttribute("biuro", biuro);
		return "new_biuro";
	}
	
	@RequestMapping(value = "/biura/save", method = RequestMethod.POST)
	public String saveBiuro(@ModelAttribute("biuro") Biuro biuro) {
		daoBiura.save(biuro);
		return "redirect:/biura";
	}
	
	@RequestMapping("/biura/edit/{nr_biura}")
	public ModelAndView showEditBiuroForm(@PathVariable(name = "nr_biura") int nr_biura) {
		ModelAndView mav = new ModelAndView("edit_biuro");
		Biuro biuro = daoBiura.get(nr_biura);
		String newDate = biuro.getData_zalozenia().split(" ")[0];
		biuro.setData_zalozenia(newDate);
		mav.addObject("biuro",biuro);
		return mav;
	}
	
	@RequestMapping(value = "/biura/update", method = RequestMethod.POST)
	public String updateBiuro(@ModelAttribute("biuro") Biuro biuro) {
		daoBiura.update(biuro);
		return "redirect:/biura";
	}
	
	@RequestMapping("/biura/delete/{nr_biura}")
	public String deleteBiuro(@PathVariable(name = "nr_biura") int nr_biura) {
		daoBiura.delete(nr_biura);
		return "redirect:/biura";
	}
	
	@RequestMapping("/pracownicy")
	public String viewPracownikPage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean hasUserRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
		if(hasUserRole) {
			Pracownik pracownik = daoPracownik.get(2);
			model.addAttribute("pracownik", pracownik);
			return "pracownicy";
		}
		else {
			List<Pracownik> listPracownicy = daoPracownik.list();
			model.addAttribute("listPracownicy", listPracownicy);
			return "pracownicy";
		}
	}
	
	@RequestMapping("/pracownicy/new")
	public String showNewPracownikForm(Model model) {
		Pracownik pracownik = new Pracownik();
		model.addAttribute("pracownik", pracownik);
		return "new_pracownik";
	}
	
	@RequestMapping(value = "/pracownicy/save", method = RequestMethod.POST)
	public String savePracownik(@ModelAttribute("pracownik") Pracownik pracownik) {
		daoPracownik.save(pracownik);
		return "redirect:/pracownicy";
	}
	
	@RequestMapping("/pracownicy/edit/{nr_pracownika}")
	public ModelAndView showEditPracownikForm(@PathVariable(name = "nr_pracownika") int nr_pracownika) {
		ModelAndView mav = new ModelAndView("edit_pracownik");
		Pracownik pracownik = daoPracownik.get(nr_pracownika);
		String newDate = pracownik.getData_urodzenia().split(" ")[0];
		pracownik.setData_urodzenia(newDate);
		mav.addObject("pracownik",pracownik);
		return mav;
	}
	
	@RequestMapping(value = "/pracownicy/update", method = RequestMethod.POST)
	public String updatePracownik(@ModelAttribute("pracownik") Pracownik pracownik) {
		daoPracownik.update(pracownik);
		return "redirect:/pracownicy";
	}
	
	@RequestMapping("/pracownicy/delete/{nr_pracownika}")
	public String deletePracownik(@PathVariable(name = "nr_pracownika") int nr_pracownika) {
		daoPracownik.delete(nr_pracownika);
		return "redirect:/pracownicy";
	}
	
	/* ------------------ Wynagrodzenia ------------------*/
	@RequestMapping("/wynagrodzenia")
	public String viewWynagrodzeniaPage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean hasUserRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
		if(hasUserRole) {
			List<Wynagrodzenie> listWynagrodzenia = daoWynag.getWy(2);
			model.addAttribute("listWynagrodzenia", listWynagrodzenia);
			return "wynagrodzenia";
		}
		else {
			List<Wynagrodzenie> listWynagrodzenia = daoWynag.list();
			model.addAttribute("listWynagrodzenia", listWynagrodzenia);
			return "wynagrodzenia";
		}
	}
	
	@RequestMapping("/wynagrodzenia/new")
	public String showNewWynagrodzeniaForm(Model model) {
		Wynagrodzenie wynagrodzenie = new Wynagrodzenie();
		model.addAttribute("wynagrodzenie", wynagrodzenie);
		return "new_wynagrodzenie";
	}
	
	@RequestMapping(value = "/wynagrodzenia/save", method = RequestMethod.POST)
	public String saveWynagrodzenie(@ModelAttribute("wynagrodzenie") Wynagrodzenie wynagrodzenie) {
		daoWynag.save(wynagrodzenie);
		return "redirect:/wynagrodzenia";
	}
	
	@RequestMapping("/wynagrodzenia/edit/{nr_wynagrodzenia}")
	public ModelAndView showEditWynagrodzenieForm(@PathVariable(name = "nr_wynagrodzenia") int nr_wynagrodzenia) {
		ModelAndView mav = new ModelAndView("edit_wynagrodzenie");
		Wynagrodzenie wynagrodzenie = daoWynag.get(nr_wynagrodzenia);
		String newDate = wynagrodzenie.getData().split(" ")[0];
		wynagrodzenie.setData(newDate);
		mav.addObject("wynagrodzenie",wynagrodzenie);
		return mav;
	}
	
	@RequestMapping(value = "/wynagrodzenia/update", method = RequestMethod.POST)
	public String updateWynagrodzenie(@ModelAttribute("wynagrodzenie") Wynagrodzenie wynagrodzenie) {
		daoWynag.update(wynagrodzenie);
		return "redirect:/wynagrodzenia";
	}
	
	@RequestMapping("/wynagrodzenia/delete/{nr_wynagrodzenia}")
	public String deleteWynagrodzenie(@PathVariable(name = "nr_wynagrodzenia") int nr_wynagrodzenia) {
		daoWynag.delete(nr_wynagrodzenia);
		return "redirect:/wynagrodzenia";
	}
	
	@RequestMapping("/wynagrodzenia/pracownik/{nr_pracownika}")
	public String showWynagPrac(@PathVariable(name = "nr_pracownika") int nr_pracownika,Model model) {
		List<Wynagrodzenie> listWynagrodzenia = daoWynag.getWy(nr_pracownika);
		model.addAttribute("listWynagrodzenia", listWynagrodzenia);
		return "wynagrodzenia";
	}
	
	@RequestMapping("/brak_dostepu")
	public String deleteDostep() {
		return "brak_dostepu";
	}
	
}
