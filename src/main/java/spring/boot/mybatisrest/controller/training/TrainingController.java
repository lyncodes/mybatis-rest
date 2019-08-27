package spring.boot.mybatisrest.controller.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.mybatisrest.entity.Training;
import spring.boot.mybatisrest.service.Training.TrainingService;

import java.util.List;


@RestController
public class TrainingController {

    @Autowired
    private TrainingService trainingService;


    @GetMapping("/training")
    public List<Training> getTraining() {
        return trainingService.getTrainings(1);
    }

}
