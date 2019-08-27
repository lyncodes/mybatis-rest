package spring.boot.mybatisrest.service.Diet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.mybatisrest.dao.Diet.DietMapper;
import spring.boot.mybatisrest.entity.Diet;

import java.util.List;

@Service
public class DietServiceImpl implements DietService {

    @Autowired
    private DietMapper dietMapper;

    @Override
    public List<Diet> getDiets(int user_id) {
        return dietMapper.getDiets(user_id);
    }

    @Override
    public DietService addDiet(int user_id) {
        return null;
    }

    @Override
    public DietService deleteDiet(int Diet_id) {
        return null;
    }

    @Override
    public DietService editDiet(int Diet_id) {
        return null;
    }
}
