package com.example.demo.controller;

import com.example.demo.model.Game;
import com.example.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class GameController {

	@Autowired
	private GameService service;

	@GetMapping({"/", "/games"})
	public String listGames(Model model) {
		model.addAttribute("games", service.listAll());
		return "games/list";
	}

	@GetMapping("/games/add")
	public String addForm(Model model) {
		model.addAttribute("game", new Game());
		return "games/add";
	}

	@PostMapping("/games/save")
	public String save(@ModelAttribute Game game, RedirectAttributes ra) {
		service.save(game);
		ra.addFlashAttribute("message", "บันทึกข้อมูลสำเร็จ");
		return "redirect:/games";
	}

	@GetMapping("/games/edit/{id}")
	public String editForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
		Optional<Game> g = service.get(id);
		if (g.isPresent()) {
			model.addAttribute("game", g.get());
			return "games/edit";
		}
		ra.addFlashAttribute("message", "ไม่พบข้อมูลเกมที่ระบุ");
		return "redirect:/games";
	}

	@PostMapping("/games/update/{id}")
	public String update(@PathVariable Long id, @ModelAttribute Game game, RedirectAttributes ra) {
		game.setId(id);
		service.save(game);
		ra.addFlashAttribute("message", "อัปเดตข้อมูลสำเร็จ");
		return "redirect:/games";
	}

	@GetMapping("/games/delete/{id}")
	public String confirmDelete(@PathVariable Long id, Model model, RedirectAttributes ra) {
		Optional<Game> g = service.get(id);
		if (g.isPresent()) {
			model.addAttribute("game", g.get());
			return "games/delete";
		}
		ra.addFlashAttribute("message", "ไม่พบข้อมูลเกมที่ระบุ");
		return "redirect:/games";
	}

	@PostMapping("/games/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes ra) {
		service.delete(id);
		ra.addFlashAttribute("message", "ลบข้อมูลสำเร็จ");
		return "redirect:/games";
	}
}
