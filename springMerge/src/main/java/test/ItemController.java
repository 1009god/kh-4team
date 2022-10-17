package test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/item")
public class ItemController {

	@RequestMapping("/list")	
	public String list(){		
		
		return "list";
	}	
	
	@RequestMapping("/detail")
	public String detail(@RequestParam int itemNo){
		
		return "detail";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int itemNo) {
		return "edit";
	}
	
	@PostMapping("/edit")
	public String edit(@ModelAttribute ItemDto itemDto) {
		return "redirect:detail";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam int itemNo) {
		return "redirect:list";
	}
	
	@GetMapping("/add")
	public String add() {
		return "add";
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute ItemDto itemDto) {
		return "redirect:list";
	}
	
	
	
}
