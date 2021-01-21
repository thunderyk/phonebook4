package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping("/phone")
public class PhoneController {

	@Autowired
	private PhoneDao phoneDao;
	//메소드 단위로 기능을 정의
	
	@RequestMapping(value="/list",method= {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		
		List<PersonVo> personList = phoneDao.getPhoneList();
		model.addAttribute("pList",personList);
		
		
		return "list";
	}
	
	@RequestMapping(value="/writeForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String writeForm() {
		
		return "writeForm";
	}
	
	@RequestMapping(value="/write",method= {RequestMethod.GET,RequestMethod.POST})
	public String write(@RequestParam("name") String name,
						@RequestParam("hp") String hp,
						@RequestParam("company") String company) {
		
		PersonVo personVo = new PersonVo(name,hp,company);
		phoneDao.PhoneInsert(personVo);
		
		return "redirect:./list";
	}
	
	@RequestMapping(value="/delete",method= {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam("id") int id) {
		
		phoneDao.PhoneDelete(id);
		
		return "redirect:./list";
	}
	
	@RequestMapping(value="/delete2/{id}",method= {RequestMethod.GET,RequestMethod.POST})
	public String delete2(@PathVariable("id") int id) {
		
		phoneDao.PhoneDelete(id);
		
		return "redirect:../list";
	}
	
	
	@RequestMapping(value="/modifyForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(Model model, @RequestParam("id") int id) {
		
		
		PersonVo personVo = phoneDao.getPerson(id);
		model.addAttribute("personVo",personVo);
		
		return "modifyForm";
	}
	
	@RequestMapping(value="/modify",method= {RequestMethod.GET,RequestMethod.POST})
	public String modify(@RequestParam("person_id") int id,
			             @RequestParam("name") String name,
			             @RequestParam("hp") String hp,
			             @RequestParam("company") String company) {
		
		PersonVo personVo = new PersonVo(id,name,hp,company);
		phoneDao.PhoneUpdate(personVo);
		
		return "redirect:./list";
	}
	
	//modelattribute로 사용
	@RequestMapping(value="/modify2",method= {RequestMethod.GET,RequestMethod.POST})
	public String modify2(@ModelAttribute PersonVo personVo) {
		
		System.out.println(personVo.toString());
		
		phoneDao.PhoneUpdate(personVo);
		
		return "redirect:./list";
	}
	//피드
	
	//생성자
	
	//메소드 g/s
	//메소드 기능 1개씩
		//리스트
		//등록폼
		//수정
		//수정폼
		//삭제
	
	
}
