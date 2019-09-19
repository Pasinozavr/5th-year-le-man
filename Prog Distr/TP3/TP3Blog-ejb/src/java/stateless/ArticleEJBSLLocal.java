package stateless;

import entities.Article;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ArticleEJBSLLocal {
    public void saveArticle(Article article);
    public List<Article> getArticles();
    public Article getArticle(Long id);
    public void majArticle(Article article);
}
