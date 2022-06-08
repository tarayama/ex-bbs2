package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

@Controller
@RequestMapping("/bbs")
public class InsertArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@RequestMapping("/createArticle")
	public String createArticle(
			@Validated ArticleForm articleForm,
			BindingResult result,
			Model model
			) {
		
//		表示メソッド実装後にエラーの遷移処理を実装する
		if(result.hasErrors()) {
//			TODO: コントローラ作成後に一覧表示画面のメソッドを返すようにする
			return null;
		}
		
		Article article = new Article();
		BeanUtils.copyProperties(articleForm, article);
		articleRepository.insert(article);
		
		return "redirect:/bbs/toindex";
	}
	
	/**
	 * 初期画面表示のメソッドを呼ぶ.
	 * 
	 * @return 初期画面を表示するメソッド
	 */
	@RequestMapping("/toindex")
	public String toindex() {
		return "forward:/bbs";
	}
}
