package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 記事追加の情報を受け取る.
 * 
 * @author keisuke.isoda
 *
 */
public class ArticleForm {
	/* 名前 */
	@NotBlank(message = "投稿者名を入力してください")
	@Size(min = 0, max = 50, message = "名前は50文字以内で入力してください")
	private String name;
	/* 内容 */
	@NotBlank(message = "投稿内容を入力してください")
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
	}

}
