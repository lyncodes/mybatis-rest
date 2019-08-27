package spring.boot.mybatisrest.service.Training;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.mybatisrest.dao.Training.TrainingMapper;
import spring.boot.mybatisrest.entity.Training;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {


    @Autowired
    private TrainingMapper trainingMapper;

    @Override
    public List<Training> getTrainings(int user_id) {
        return trainingMapper.getTrainings(user_id);
    }

    @Override
    public TrainingService addTraining(int user_id) {
        return null;
    }

    @Override
    public TrainingService deleteTraining(int training_id) {
        return null;
    }

    @Override
    public TrainingService editTraining(int training_id) {
        return null;
    }
}
