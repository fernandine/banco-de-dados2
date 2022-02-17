package aplicacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Dados;

public class Programa {

	public static void main(String[] args) {

		Dados d1 = new Dados(null, "joao", "joao@gmail.com");
		Dados d2 = new Dados(null, "maria", "maria@gmail.com");
		Dados d3 = new Dados(null, "beto", "beto@gamil.com");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(d1);
		em.persist(d2);
		em.persist(d3);
		em.getTransaction().commit();

		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);

		// Atualizando uma Dados com entityManager.merge;

		d2 = em.find(Dados.class, 1L);
		d2.setNome("Maria Antônia");
		d2.setEmail("mariaa@yahoo.com.br");

		em.getTransaction().begin();
		em.merge(d2);
		em.getTransaction().commit();

		// Removendo uma Dados com EntityManager.remove;

		em.getTransaction().begin();
		d3 = em.find(Dados.class, 1l);
		em.remove(d3);
		em.getTransaction().commit();

		// Fazendo uma busca na tabela;

		List<Dados> dados = null;
		dados = em.createQuery("teste").getResultList();

		if (dados != null) {
			dados.forEach(System.out::println);
		}
	}
}
