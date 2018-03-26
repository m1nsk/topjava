package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
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
        } else {
            if (get(meal.getId(), userId)!=null){
                meal = em.merge(meal);
                return meal;
            }
//                Query query = em.createNamedQuery(Meal.UPDATE);
//                int result = query.setParameter("id", meal.getId())
//                        .setParameter("user_id", userId)
//                        .setParameter("calories", meal.getCalories())
//                        .setParameter("date_time", meal.getDateTime())
//                        .setParameter("description", meal.getDescription())
//                        .executeUpdate();
//                return result == 0 ? null : meal;
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        Query query = em.createNamedQuery(Meal.DELETE);
        return query.setParameter("id", id)
                .setParameter("user_id", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = em.find(Meal.class, id);
        if (meal.getUser() != null && meal.getUser().getId().equals(userId)) {
            return meal;
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        Query query = em.createNamedQuery(Meal.ALL_SORTED, Meal.class);
        return query.setParameter("user_id", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Query query = em.createNamedQuery(Meal.ALL_BETWEEN_SORTED, Meal.class);
        return query
                .setParameter("user_id", userId)
                .setParameter("start_date", startDate)
                .setParameter("end_date", endDate)
                .getResultList();
    }
}