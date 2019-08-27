package spring.boot.mybatisrest.service.Training;

import spring.boot.mybatisrest.entity.Training;

import java.util.List;

public interface TrainingService {

    List<Training> getTrainings(int user_id);

    TrainingService addTraining(int user_id);

    TrainingService deleteTraining(int training_id);

    TrainingService editTraining(int training_id);
}
