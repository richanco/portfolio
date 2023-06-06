package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.examle.demo.model.AccountData;
import com.examle.demo.model.LoginData;
import com.examle.demo.model.TweetData;

@RequestMapping("hello/*")
@Controller
public class HelloController {
	@GetMapping("world")
	  public String open(Model model) {
	    String str = "PocoTsubu(仮)";
	    model.addAttribute("value", str);
	    return "hello";
	  }
	
	///ログイン画面を表示
	@PostMapping("login")
	  public String login(@ModelAttribute LoginData loginData,Model model, RedirectAttributes attributes) {
		  //nullPointerExceptionにならないように
		if(loginData.getId() == 12345 && ( "chanrihomaromaro".equals(loginData.getPassword()))) { 
		 ///特定のID及びパスワードであれば別にフォワードする
			return "profile";	
		}else { ///特定のID及びパスワードでなければログイン画面にフォワードする
		    attributes.addFlashAttribute("errorMessage", "入力内容にエラーがあります。");
			return "redirect:/hello/world";
		}
	}
	
	///tweet画面を表示
	@PostMapping("account")
	  public String account(@ModelAttribute AccountData accountData, Model model) {
		 return "redirect:/hello/pocotsubu";
	}
	
    ///サーバー接続時のテーブルデータの表示
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@GetMapping("/pocotsubu")
	public String pocotsubu(Model model) {
		String sql = "SELECT * FROM tweet_table";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        model.addAttribute("testList", list);
		return "pocotsubu";
	}
	
	@RequestMapping(value="/hello/tweet",method = RequestMethod.POST)
	public String tweet(@ModelAttribute TweetData tweetData ,Model model) {
		return "redirect:/hello/tweet" ;
	}
	
	
}
