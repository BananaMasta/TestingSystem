package com.example.testingsystem.services;

import com.example.testingsystem.repositories.QuestionRepository;
//import com.example.testingsystem.repositories.StudentAnsweresRepository;
import com.example.testingsystem.repositories.StudentAnsweresRepository;
import com.example.testingsystem.repositories.TestRepository;
import com.example.testingsystem.repositories.UserRepository;
import com.example.testingsystem.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private StudentAnsweresRepository studentAnsweresRepository;

    public Model isNull(Question findQuestion, Model model) {
        if (findQuestion != null) {
            return model.addAttribute("Error", "Вопрос уже существует");
        }
        return model.addAttribute("null");
    }

    public List<Question> removeQuestionFromList(List<Question> questionList, Test test) {
        questionList.removeAll(test.getQuestionList());
        return questionList;
    }

    public List<Question> addingSameStudySubject(List<Question> questionList, Test test) {
        List<Question> questionsNew = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getStudySubject().equals(test.getStudySubjectList())) {
                questionsNew.add(questionList.get(i));
            }
        }
        return questionsNew;
    }

    public StudentAnswers addingToResult(List<String> studentResultsList, String testId, long userId, String result) {
        studentResultsList.add(result);
        StudentAnswers studentAnswers = new StudentAnswers();
        studentAnswers.setTest(testRepository.findById(Long.parseLong(testId)));
        studentAnswers.setUser(userRepository.findById(userId));
        studentAnswers.setResultList(studentResultsList);
        studentAnsweresRepository.save(studentAnswers);
        return studentAnswers;
    }

    public boolean checkStudentAnswers(String questionId, String studentAnswersId) {
        Question question = questionRepository.findById(Long.parseLong(questionId));
        StudentAnswers studentAnswers = studentAnsweresRepository.findById(Long.parseLong(studentAnswersId));
        List<String> a = new ArrayList<>(studentAnswers.getResultList());
        Collections.reverse(a);
        return question.getAnswers().equals(a.get(0));
    }

    public List<UserStatistic> sortAZ(List<UserStatistic> userStatistics) {
        userStatistics.sort(new Comparator<UserStatistic>() {
            @Override
            public int compare(UserStatistic o1, UserStatistic o2) {
                return o1.getUser().getUsername().compareTo(o2.getUser().getUsername());
            }
        });
        return userStatistics;
    }

    public List<UserStatistic> sortZA(List<UserStatistic> userStatistics) {
        userStatistics.sort(new Comparator<UserStatistic>() {
            @Override
            public int compare(UserStatistic o1, UserStatistic o2) {
                return o2.getUser().getUsername().compareTo(o1.getUser().getUsername());
            }
        });
        return userStatistics;
    }
    public List<UserStatistic> sort0100(List<UserStatistic> userStatistics) {
        userStatistics.sort(new Comparator<UserStatistic>() {
            @Override
            public int compare(UserStatistic o1, UserStatistic o2) {
                return (int) (o2.getUserTestScore() - o1.getUserTestScore());
            }
        });
        return userStatistics;
    }
    public List<UserStatistic> sort1000(List<UserStatistic> userStatistics) {
        userStatistics.sort(new Comparator<UserStatistic>() {
            @Override
            public int compare(UserStatistic o1, UserStatistic o2) {
                return (int) (o1.getUserTestScore() - o2.getUserTestScore());
            }
        });
        return userStatistics;
    }

    public List<Map.Entry<String, Double>> userStat(Map<String, Double> map) {
        Set<Map.Entry<String, Double>> setMap = map.entrySet();
        List<Map.Entry<String, Double>> setList = new ArrayList<>(setMap);
        setList.sort(new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Map<String, Double> newMap = new LinkedHashMap<>();
        for (int i = 0; i < setList.size(); i++) {
            newMap.put(setList.get(i).getKey(), setList.get(i).getValue());
        }
        for (Map.Entry<String, Double> entry : newMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        return new ArrayList<>(newMap.entrySet());
    }

}
