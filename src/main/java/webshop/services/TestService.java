package webshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.entities.Test;
import webshop.repositories.TestRepository;

import java.util.List;

@Service
public class TestService {

    private TestRepository testRepository;

    @Autowired
    TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> findAll() {
        return testRepository.findAll();
    }

    public void save(Test test) {
        testRepository.save(test);
    }
}
