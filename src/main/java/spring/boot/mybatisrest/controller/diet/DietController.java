package spring.boot.mybatisrest.controller.diet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.mybatisrest.entity.Diet;
import spring.boot.mybatisrest.service.Diet.DietService;

import java.util.List;


@RestController
public class DietController {

    @Autowired
    private DietService dietService;

    @GetMapping("/diet")
    public List<Diet> getDiets() {
        return dietService.getDiets(1);
    }
}
