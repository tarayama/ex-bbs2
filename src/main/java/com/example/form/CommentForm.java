package com.example.form;

import javax.validation.constraints.NotBlank;

/**
 * 新しいコメントのためのフォーム.
 * 
 * @author takuya.matsura
 *
 */
public class CommentForm {
	/** コメント者名 */
	@NotBlank(message="名前を入力してください")
	private String name;
	/** コメント内容 */
	@NotBlank(message="コメントの内容を入力してください")
	private String content;
	/** コメント先の記事ID */
	private String articleId;

	public Integer getIntArticleId() {
		return Integer.parseInt(articleId);
	}

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

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "CommentForm [name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}

}
