package services;

import models.Achievement;
import services.repository.AchievementRepository;

import java.sql.Connection;
import java.util.Collection;

public class AchievementService {

    private final AchievementRepository achievementRepository;

    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public void addUserAchievements(Connection connection, int userId){
        achievementRepository.addUserAchievements(connection, userId);
    }

    public void updateAch(Connection connection, int userId, int achId){
        achievementRepository.updateAch(connection, userId, achId);
    }

    public Collection<Achievement> getUserAchievements(Connection connection, int userId){
        return achievementRepository.getUserAchievements(connection, userId);
    }
}
