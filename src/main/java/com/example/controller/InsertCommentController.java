package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Comment;
import com.example.form.CommentForm;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("/bbs")
public class InsertCommentController {
	@Autowired
	private ShowBbsController showBbsController;
	
	@Autowired
	private CommentRepository commentRepository;
	/**
	 * 新しいコメントを挿入する.
	 * 
	 * @param form 新しいコメントの入力フォーム
	 * @return　記事一覧画面
	 */
	@RequestMapping("/insertComment")
	public String insertComment(@Validated CommentForm form, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("errorArticleID", Integer.parseInt(form.getArticleId()));
			System.out.println("errorArticleID" + form.getArticleId());
			System.out.println(result);
			return showBbsController.index(model);
		}
		
		Comment comment = new Comment();
		comment.setName(form.getName());
		comment.setContent(form.getContent());
		comment.setArticleId(form.getIntArticleId());
		
		commentRepository.insert(comment);
		return "redirect:/bbs";
	}
}
