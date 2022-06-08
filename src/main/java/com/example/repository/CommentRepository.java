package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメント情報情報を操作するリポジトリ.
 * 
 * @author keisuke.isoda
 *
 */
@Repository
public class CommentRepository {

	/**
	 * コメントオブジェクトを生成するローマッパー
	 */
	public static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		return comment;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 記事idでコメントを検索する.
	 * idの昇順で取得
	 * 
	 * @param articleId 記事id
	 * @return コメントの検索結果
	 */
	public List<Comment> findByArticleId(Integer articleId) {
		String sql = "SELECT id, name, content, article_id FROM comments WHERE article_id = :articleId ORDER BY id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
		
		return commentList;
	}
	
	/**
	 * コメントの情報を登録する.
	 * 
	 * @param comment コメントの情報
	 */
	public void insert(Comment comment) {
		String sql = "INSERT INTO Comments (name, content, article_id) VALUES (:name, :content, :articleId)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		template.update(sql, param);
	}
	
	/**
	 * コメントを削除する.
	 * 
	 * @param articleId 記事id
	 */
	public void deleteByArticleId(Integer articleId) {
		String sql = "DELETE FROM comments WHERE article_id = :articleId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
	}
}
