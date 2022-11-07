package repositories;

import models.Achievement;

import java.sql.Connection;
import java.util.Collection;

public interface AchievementRepository {
    void addUserAchievements(Connection connection, int userId);
    void updateAch(Connection connection, int userId, int achId);
    Collection<Achievement> getUserAchievements(Connection connection, int userId);
}
