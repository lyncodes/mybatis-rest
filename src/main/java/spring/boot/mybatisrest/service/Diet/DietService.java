package spring.boot.mybatisrest.service.Diet;

import spring.boot.mybatisrest.entity.Diet;

import java.util.List;

public interface DietService {
    List<Diet> getDiets(int user_id);

    DietService addDiet(int user_id);

    DietService deleteDiet(int Diet_id);

    DietService editDiet(int Diet_id);
}
