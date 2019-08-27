// spring.boot.mybatisrest.dao.Training.TrainingMapper.java
package spring.boot.mybatisrest.dao.Training;

import org.springframework.stereotype.Repository;
import spring.boot.mybatisrest.entity.Training;

import java.util.List;

@Repository
public interface TrainingMapper {
    List<Training> getTrainings(int user_id);
}
