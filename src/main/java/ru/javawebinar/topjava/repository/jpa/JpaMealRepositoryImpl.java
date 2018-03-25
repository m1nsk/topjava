package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User refUser = em.getReference(User.class, userId);
        meal.setUser(refUser);
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            Query query = em.createNamedQuery(Meal.UPDATE);
            int result = query.setParameter("id", meal.getId())
                    .setParameter("user", refUser)
                    .setParameter("calories", meal.getCalories())
                    .setParameter("date_time", meal.getDateTime())
                    .setParameter("description", meal.getDescription())
                            .executeUpdate();
            return result == 0 ? null : meal;
        }

    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        User refUser = em.getReference(User.class, userId);
        Query query = em.createNamedQuery(Meal.DELETE);
        return query.setParameter("id", id)
                .setParameter("user", refUser)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        User refUser = em.getReference(User.class, userId);
        Query query = em.createNamedQuery(Meal.GET);
        List<Meal> result = query.setParameter("id", id)
                .setParameter("user", refUser).getResultList();
        return DataAccessUtils.singleResult(result);
    }

    @Override
    public List<Meal> getAll(int userId) {
        User refUser = em.getReference(User.class, userId);
        Query query = em.createNamedQuery(Meal.ALL_SORTED, Meal.class);
        return query.setParameter("user", refUser).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        User refUser = em.getReference(User.class, userId);
        Query query = em.createNamedQuery(Meal.ALL_BETWEEN_SORTED, Meal.class);
        return query
                .setParameter("user", refUser)
                .setParameter("start_date", startDate)
                .setParameter("end_date", endDate)
                .getResultList();
    }
}