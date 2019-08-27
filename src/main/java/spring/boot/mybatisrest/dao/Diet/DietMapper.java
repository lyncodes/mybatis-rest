package spring.boot.mybatisrest.dao.Diet;

import org.springframework.stereotype.Repository;
import spring.boot.mybatisrest.entity.Diet;

import java.util.List;


@Repository
public interface DietMapper {
    List<Diet> getDiets(int user_id);
}
