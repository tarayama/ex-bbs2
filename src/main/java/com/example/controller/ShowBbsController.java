package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("/bbs")
public class ShowBbsController {
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * 記事一覧を表示する.
	 * 
	 * @param model リクエストスコープ
	 * @return　記事一覧画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		
		for (Article article : articleList) {
			List<Comment> commentList = commentRepository.findByArticleId(article.getId());
			article.setCommentList(commentList);
		}
		
		model.addAttribute("articleList", articleList);
		
		return "bbs.html";
	}
}
