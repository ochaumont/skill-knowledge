package com.ochaumont.demo.skillknowledge.pres.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.taglibs.standard.lang.jstl.EnumeratedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ochaumont.demo.skillknowledge.dao.ConsultantService;
import com.ochaumont.demo.skillknowledge.dao.ConsultantSkillDAO;
import com.ochaumont.demo.skillknowledge.dao.ExpertiseDAO;
import com.ochaumont.demo.skillknowledge.dao.SkillDAO;
import com.ochaumont.demo.skillknowledge.dao.SkillTypeDAO;
import com.ochaumont.demo.skillknowledge.domain.ConsultantSkill;
import com.ochaumont.demo.skillknowledge.domain.Expertise;
import com.ochaumont.demo.skillknowledge.domain.Skill;
import com.ochaumont.demo.skillknowledge.domain.SkillExpertise;
import com.ochaumont.demo.skillknowledge.domain.SkillType;
import com.ochaumont.demo.skillknowledge.util.IPredicate;
import com.ochaumont.demo.skillknowledge.util.PredicateManager;


@Controller
public class ConsultantController {
			
	private static String FORWARD_TO_RADAR = "consultantSkills_radar";
	private static String FORWARD_TO_BAR = "consultantSkills_bar";
	private static String FORWARD_TO_EDIT_PROFIL = "editProfil";
	private static String FORWARD_TO_ACCUEIL= "accueil";
	
	
	@Autowired
	private SkillDAO skillDAO;
	
	@Autowired
	private SkillTypeDAO skillTypeDAO;
	
	@Autowired
	private ConsultantSkillDAO consultantSkillDAO;
	
	@Autowired
	private ConsultantService consultantService;
	
	@Autowired
	private ExpertiseDAO expertiseDAO;
		
	public ConsultantController() {
		
	}
	
	@RequestMapping(value="/editProfil",method=RequestMethod.GET)
	public String editProfil(ModelMap modelMap,@RequestParam(value="skillType", required=false) String skillType) {
			
		List<SkillType> skillTypes = skillTypeDAO.getAll();
		modelMap.put("skillTypes", skillTypes);
		
		
		 modelMap.put("skills", new ArrayList<Skill>());
		
		if(!skillType.equals("default")) {
		  SkillType selectedSkillType = skillTypeDAO.getBy("name", skillType).get(0);		 
		  modelMap.put("selectedSkillType", selectedSkillType);		
		  		 
			
		  List<Skill> skills = skillDAO.getBy("type", selectedSkillType);
		  modelMap.put("skills",skills);
		  
		  
		  Map<String, Integer> levelMap = new HashMap<String, Integer>();
		  ConsultantSkill consultantSkill =  consultantSkillDAO.getBy("name", "chaumont").get(0);
		  for(SkillExpertise skillExp : consultantSkill.getSkillExps()) {
			  if(skillExp.getSkill().getType().getName().equals(skillType)) {
				  levelMap.put(skillExp.getSkill().getName(), skillExp.getLevel());
			  }
		  }
		  
		
		  modelMap.put("levelMap",levelMap);
		  
		}
					
		return FORWARD_TO_EDIT_PROFIL;
	}
	
		
	
	@RequestMapping(value="/updateProfil",method=RequestMethod.POST)	
	public String updateProfil(ModelMap modelMap,HttpServletRequest request) {
		
		ConsultantSkill consultantSkill =  consultantSkillDAO.getBy("name", "chaumont").get(0);
	
		
		Map<String,Integer> dtos = new HashMap<String,Integer>();
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			if(param.indexOf("level-") != -1) {
				String skillName = param.replaceAll("level-", "");
				int level = Integer.parseInt(request.getParameter(param));
				dtos.put(skillName,level);								
			}
		}
		
		consultantService.save(consultantSkill,dtos);
		
		return FORWARD_TO_ACCUEIL;
	}
	
	private Map<String,Collection<SkillExpertise>> skillFilter(String skillFilter, Collection<SkillExpertise> skillExps,Comparator<SkillExpertise> comparator) {
		Map<String,Collection<SkillExpertise>> result = new HashMap<String,Collection<SkillExpertise>>();
		
		if(skillFilter == null || skillFilter.equals("all")) {
			Collection<SkillExpertise> set = new TreeSet<SkillExpertise>(comparator);
			set.addAll(skillExps);
			result.put("all",set);			
		}else if(skillFilter.equals("bytype")){			
			for (SkillType skillType : skillTypeDAO.getAll()) {
				IPredicate<SkillExpertise> predicate = new PredicateSkillByType(skillType);
				result.put(skillType.getLabel(),PredicateManager.filter(skillExps, predicate,comparator));
			}						
		}else if(skillFilter.equals("byexpertise")) {
			for (Expertise expertise : expertiseDAO.getAll()) {
				IPredicate<SkillExpertise> predicate = new PredicateSkillByExpertise(expertise);
				result.put(expertise.getLabel(),PredicateManager.filter(skillExps, predicate,comparator));
			}	
		}
		return result;		
		
	}
	
	@RequestMapping("/consultantSkills")
	public String consultantSkills(ModelMap modelMap, HttpServletRequest request) {
		
		String skillFilter = request.getParameter("skillFilter");
		
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		 
		ConsultantSkill consultantSkill =  consultantSkillDAO.getBy("name", "chaumont").get(0);
					
		StringBuffer buffCategories = new StringBuffer("[");
		StringBuffer buffData = new StringBuffer("[");
		//int nbrMaxElts = 1000;
		//int nbrMin = 3;
		//int nbrElts = 0;
		
		
		Map<String,Collection<SkillExpertise>> skillExpsMap = skillFilter(skillFilter,consultantSkill.getSkillExps(),new SkillExpertiseComparator());
		
		for (Entry<String, Collection<SkillExpertise>> entry : skillExpsMap.entrySet()) {
			String chartLabel = entry.getKey();
			Collection<SkillExpertise>	skillExps = entry.getValue();
			if(skillExps.size() > 0) {
				for (SkillExpertise skillExp : skillExps) {
					buffCategories.append("\""+skillExp.getSkill().getLabel()+"\",");
					buffData.append(""+skillExp.getLevel()+",");				
				}
				
				createData(chartLabel, buffCategories,buffData,result,skillExps.size());	
				buffCategories = new StringBuffer("[");
				buffData = new StringBuffer("[");
			}
			
		}
		
		/*		
		int ecart = skillExps.size()%nbrMaxElts;
		boolean mustAddItem = ecart<nbrMin;
	
		for (SkillExpertise skillExp : skillExps) {
			nbrElts++;
			
			buffCategories.append("\""+skillExp.getSkill().getLabel()+"\",");
			buffData.append(""+skillExp.getLevel()+",");
			
			if(nbrElts>=nbrMaxElts) {
				if(!mustAddItem) {
					createData(buffCategories,buffData,result);		
					buffCategories = new StringBuffer("[");
					buffData = new StringBuffer("[");
					nbrElts = 0;	
					if(ecart >0) {mustAddItem = true;}
				}else {
					ecart--;
					mustAddItem = false;					
				}
			}
			
	    }
		*/
		
		//if(nbrElts > 0) {createData(buffCategories,buffData,result);}		
		
		modelMap.put("consultantSkill", consultantSkill);
		modelMap.put("result", result);
		
				
		return FORWARD_TO_BAR;
	}
	
	
		
	
	private void createData(String label,StringBuffer buffCategories, StringBuffer buffData, List<Map<String,Object>> result, int nbrSkills) {
			
		Map<String,Object> map = new HashMap<String,Object>();
		
		String categories = buffCategories.append("]").toString().replaceAll(",]", "]");
		String data = buffData.append("]").toString().replaceAll(",]", "]");
		
		int size = nbrSkills*40;
		int min = 100;
		if(size<min) {
			size = min;
		}
		
		map.put("categories", categories);
		map.put("data", data);
		map.put("label", label);
		map.put("size", size);
		result.add(map);					
	}
		
}
