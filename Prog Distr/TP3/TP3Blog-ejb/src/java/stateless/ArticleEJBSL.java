/**
 *
 * @author pasinozavr
 */

package stateless;

import entities.Article;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ArticleEJBSL implements ArticleEJBSLLocal {

    @PersistenceContext(unitName = "TP3BlogPU")
    private EntityManager em;

    @Override
    public void saveArticle(Article article) {
        em.persist(article);
    }

    @Override
    public List<Article> getArticles() {
        return em.createQuery("SELECT a FROM Article a", Article.class).getResultList();
    }

    @Override
    public Article getArticle(Long id) {
        return em.find(Article.class, id);
    }

    @Override
    public void majArticle(Article article) {
        em.persist(em.merge(article));
    }
}
