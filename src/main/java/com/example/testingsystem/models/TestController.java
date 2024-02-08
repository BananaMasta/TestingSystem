package com.example.testingsystem.models;

import com.example.testingsystem.repositories.*;
import com.example.testingsystem.services.TestService;
import com.example.testingsystem.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class TestController {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    TestRepository testRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    TestService testService;
    @Autowired
    StudentAnsweresRepository studentAnsweresRepository;
    @Autowired
    UserStatRepository userStatRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/create")
    public String testCreatePage(Model model) {
        List<StudySubject> studySubjectList = subjectRepository.findAll();
        model.addAttribute("subject", studySubjectList);
        model.addAttribute("questionDifficult", QuestionDifficult.values());
        return "createpage";
    }

    @GetMapping("/tests")
    public String testsList(@AuthenticationPrincipal User user, Model model) {
        List<Test> testList = testRepository.findAll();
        model.addAttribute("testList", testList);
        model.addAttribute("userRole", user.getUserRoles().get(0).toString());
        model.addAttribute("userId", user.getId());
        return "testList";
    }

    @GetMapping("/testpage/{id}")
    public String testPage(@PathVariable String id, Model model) {
        Test test = testRepository.findById(Long.parseLong(id));
        List<Question> questionList = questionRepository.findAll();
        testService.removeQuestionFromList(questionList, test);
        model.addAttribute("questionsInTest", test.getQuestionList());
        model.addAttribute("questions", testService.addingSameStudySubject(questionList, test));
        model.addAttribute("testid", test.getId());
        return "testpage";
    }

    @GetMapping("/studentesting/{testId}")
    public String studentTesting(@PathVariable String testId, Model model) {
        Test test = testRepository.findById(Long.parseLong(testId));
        model.addAttribute("question1", test.getQuestionList().get(0));
        model.addAttribute("question1Id", test.getQuestionList().get(0).getId());
        model.addAttribute("questionName", test.getQuestionList().get(0).getQuestName());
        model.addAttribute("questionAnswer1", test.getQuestionList().get(0).getAnswerVariants().get(0));
        model.addAttribute("questionAnswer2", test.getQuestionList().get(0).getAnswerVariants().get(1));
        model.addAttribute("questionAnswer3", test.getQuestionList().get(0).getAnswerVariants().get(2));
        model.addAttribute("questionAnswer4", test.getQuestionList().get(0).getAnswerVariants().get(3));
        model.addAttribute("questionNumber", "0");
        model.addAttribute("testId", testId);
        model.addAttribute("testName", test.getTestName());
        model.addAttribute("questions", test.getQuestionList());
        model.addAttribute("questionSize", test.getQuestionList().size());
        return "studentesting";
    }

    @GetMapping("/testcomplete/{testId}")
    public String getTestDone(@PathVariable String testId, Model model) {
        Test test = testRepository.findById(Long.parseLong(testId));
        List<StudentAnswers> studentAnswers = studentAnsweresRepository.findByTest(test);
        Collections.reverse(studentAnswers);
        List<Question> a = new ArrayList<>(test.getQuestionList());
        Collections.reverse(a);
        model.addAttribute("testId", test.getId());
        model.addAttribute("questionList", a);
        model.addAttribute("studentAnswersList", studentAnswers);
        return "testcomplete";
    }

    @PostMapping("/testcreate")
    public String testCreate(@AuthenticationPrincipal User user, @RequestParam(name = "testName") String testName, @RequestParam(name = "subject") String subject) {
        Test test = new Test();
        test.setTestName(testName);
        test.setUser(user);
        test.setStudySubjectList(subjectRepository.findByName(subject));
        testRepository.save(test);
        return "redirect:/create";
    }

    @PostMapping("/studsubjectcreate")
    public String studSubjectCreatePost(@RequestParam(name = "subjectName") String subjectName) {
        StudySubject studySubject = new StudySubject(subjectName);
        subjectRepository.save(studySubject);
        return "redirect:/create";
    }

    @PostMapping("/addingquestiontotest/{id}")
    public String questionsAddingToTestList(@PathVariable(name = "id") String questionId, @RequestParam(name = "testId") String testId) {
        Test test = testRepository.findById(Long.parseLong(testId));
        Question question = questionRepository.findById(Long.parseLong(questionId));
        test.getQuestionList().add(question);
        testRepository.save(test);
        return "redirect:/testpage/" + testId;
    }

    @PostMapping("/testpage/{id}")
    public String deleteQuestion(@PathVariable(name = "id") String testId, @RequestParam(name = "questId") String questionId) {
        Question question = questionRepository.findById(Long.parseLong(questionId));
        Test test = testRepository.findById(Long.parseLong(testId));
        List<Question> questionList = test.getQuestionList();
        List<Question> allQuestionsList = new ArrayList<>();
        allQuestionsList.add(question);
        testService.addingSameStudySubject(allQuestionsList, test);
        questionList.remove(question);
        testRepository.save(test);
        return "redirect:/testpage/" + testId;
    }

    @PostMapping("/studentesting/{testId}")
    public String studentTestingPost(@AuthenticationPrincipal User user, @PathVariable(name = "testId") String testId,
                                     @RequestParam(name = "answer") String result, @RequestParam(name = "questionNum") String questionNumber, Model model) {
        List<String> studentResultsList = new ArrayList<>();
        Test test = testRepository.findById(Long.parseLong(testId));
        if (test.getQuestionList().size() - 1 == Integer.parseInt(questionNumber)) {
            testService.addingToResult(studentResultsList, testId, user.getId(), result);
            return "redirect:/testcomplete/" + testId;
        } else {
            int a = Integer.parseInt(questionNumber) + 1;
            model.addAttribute("question1", test.getQuestionList().get(a));
            model.addAttribute("question1Id", test.getQuestionList().get(a).getId());
            model.addAttribute("questionName", test.getQuestionList().get(a).getQuestName());
            model.addAttribute("questionAnswer1", test.getQuestionList().get(a).getAnswerVariants().get(0));
            model.addAttribute("questionAnswer2", test.getQuestionList().get(a).getAnswerVariants().get(1));
            model.addAttribute("questionAnswer3", test.getQuestionList().get(a).getAnswerVariants().get(2));
            model.addAttribute("questionAnswer4", test.getQuestionList().get(a).getAnswerVariants().get(3));
            model.addAttribute("questionNumber", a);
            model.addAttribute("questionSize", test.getQuestionList().size());
            testService.addingToResult(studentResultsList, testId, user.getId(), result);
            return "studentesting";
        }
    }

    @PostMapping("/testcomplete/{testId}")
    public String testComplete(@AuthenticationPrincipal User user, @PathVariable String testId, @RequestParam(name = "questionId") String questionId,
                               @RequestParam(name = "studentAnswersId") String studAnswersId, Model model) {
        Test test = testRepository.findById(Long.parseLong(testId));
        String[] a = questionId.split(",");
        String[] b = studAnswersId.split(",");
        List<String> trueOrFalse = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (testService.checkStudentAnswers(a[i], b[i])) {
                trueOrFalse.add("true");
            } else {
                trueOrFalse.add("false");
            }
        }
        Collections.reverse(trueOrFalse);
        int count = 0;
        for (int i = 0; i < trueOrFalse.size(); i++) {
            if (trueOrFalse.get(i).equals("true")) {
                count++;
            }
        }
        double finalScore = (float) (100 / test.getQuestionList().size()) * count;
        model.addAttribute("questionList1", test.getQuestionList());
        model.addAttribute("trueOrFalse", trueOrFalse);
        model.addAttribute("finalscore", finalScore);
        if (finalScore >= 40) {
            model.addAttribute("pass", "Вы сдали экзамен");
            UserStatistic userStatistic = new UserStatistic(user, test, "Passed", finalScore);
            userStatRepository.save(userStatistic);
        } else {
            model.addAttribute("pass", "Вы не сдали экзамен");
            UserStatistic userStatistic = new UserStatistic(user, test, "Not passed", finalScore);
            userStatRepository.save(userStatistic);
        }
        return "testcomplete";
    }

}