package com.example.domain;

/**
 * コメント情報操作用ドメイン.
 * 
 * @author keisuke.isoda
 *
 */
public class Comment {
	/* id */
	private Integer id;
	/* 名前 */
	private String name;
	/* 内容 */
	private String content;
	/* 記事id */
	private Integer articleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}

}
