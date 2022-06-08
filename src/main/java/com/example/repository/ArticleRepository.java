package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.comments.CommentLine;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * 
 * articlesテーブルのレポジトリ.
 * 
 * @author takuya.matsura
 *
 */
@Repository
public class ArticleRepository {
	
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) ->{
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		
		return article;
	};
	
	
	private static final ResultSetExtractor<List<Article>> ARTICLE_RESULTSET_EXTRACTOR = rs -> {
		int prevArticleId = -1;
		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = null;
		
		while (rs.next()) {
			// 前回のidとは異なるidになった時
			if (prevArticleId != rs.getInt("id")) {
				
				Article article = new Article();
				article.setId(rs.getInt("id"));
				article.setName(rs.getString("name"));
				article.setContent(rs.getString("content"));
				articleList.add(article);
				
				// 空のコメントリストを作成してariticleにセット
				commentList = new ArrayList<>();
				article.setCommentList(commentList);
				
				prevArticleId = article.getId();
			}
			if (rs.getInt("com_id") != 0) {
				
				Comment comment = new Comment();
				comment.setId(rs.getInt("com_id"));
				comment.setName(rs.getString("com_name"));
				comment.setContent(rs.getString("com_content"));
				comment.setArticleId(rs.getInt("article_id"));
				commentList.add(comment);
			}
			
		}
		
			
		return articleList;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 記事一覧を返す.
	 * 
	 * @return 記事一覧
	 */
	public List<Article> findAll(){
		String sql = "SELECT a.id, a.name, a.content, c.id as com_id, c.name as com_name, c.content as com_content, c.article_id FROM articles as a LEFT OUTER JOIN comments as c on a.id=c.article_id ORDER BY a.id DESC, com_id DESC;";
		List<Article> articleList = template.query(sql, ARTICLE_RESULTSET_EXTRACTOR);
		
		return articleList;
	}
	
	/**
	 * 新しい記事の挿入.
	 * 
	 * @param article 新しい記事
	 */
	public void insert(Article article) {
		String sql = "INSERT INTO articles(name, content) values(:name, :content);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		
		template.update(sql, param);
	}
	
	/**
	 * 記事の削除
	 * 
	 * @param id　記事ID
	 */
	public void deleteById(int id) {
		String sql = "DELETE FROM articles WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		template.update(sql, param);
	}

}
