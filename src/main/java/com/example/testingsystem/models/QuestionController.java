package com.example.testingsystem.models;

import com.example.testingsystem.repositories.QuestionRepository;
import com.example.testingsystem.repositories.SubjectRepository;
import com.example.testingsystem.users.Question;
import com.example.testingsystem.users.QuestionDifficult;
import com.example.testingsystem.users.StudySubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/updatequestion/{id}")
    public String updateQuestion(@PathVariable(name = "id") String questionId, Model model) {
        Question question = questionRepository.findById(Long.parseLong(questionId));
        List<StudySubject> studySubjectList = subjectRepository.findAll();
        model.addAttribute("id", question.getId());
        model.addAttribute("questionName", question.getQuestName());
        model.addAttribute("answer1", question.getAnswerVariants().get(0));
        model.addAttribute("answer2", question.getAnswerVariants().get(1));
        model.addAttribute("answer3", question.getAnswerVariants().get(2));
        model.addAttribute("answer4", question.getAnswerVariants().get(3));
        model.addAttribute("rightAnswer", question.getAnswers());
        model.addAttribute("studysubject", studySubjectList);
        return "updatequestion";
    }

    @PostMapping("/questionscreate")
    public String testCreateController(@RequestParam(name = "questName") String questName, @RequestParam(name = "answerVar1") String answerVar1,
                                       @RequestParam(name = "radio1", required = false) String radio1, @RequestParam(name = "radio2", required = false) String radio2,
                                       @RequestParam(name = "radio3", required = false) String radio3, @RequestParam(name = "radio4", required = false) String radio4,
                                       @RequestParam(name = "answerVar2") String answerVar2, @RequestParam(name = "answerVar3") String answerVar3,
                                       @RequestParam(name = "answerVar4") String answerVar4, @RequestParam(name = "subject") String subject, @RequestParam(name = "questionDifficulty") String questionDiff, Model model) {
        String answer = null;
        List<StudySubject> studySubjectList = subjectRepository.findAll();
        List<String> answers = new ArrayList<>();
        QuestionDifficult questionDifficulty = QuestionDifficult.valueOf(questionDiff);
        answers.add(answerVar1);
        answers.add(answerVar2);
        answers.add(answerVar3);
        answers.add(answerVar4);
        if (radio1 != null) {
            answer = answerVar1;
        } else if (radio2 != null) {
            answer = answerVar2;
        } else if (radio3 != null) {
            answer = answerVar3;
        } else if (radio4 != null) {
            answer = answerVar4;
        }
        Question question = new Question(questName, answers, subjectRepository.findByName(subject), answer, questionDifficulty);
        Question findQuestion = questionRepository.findByQuestName(questName);
        if (findQuestion != null) {
            model.addAttribute("Error", "Вопрос уже существует");
        }
        questionRepository.save(question);
        model.addAttribute("subject", studySubjectList);
        return "redirect:/create";
    }

    @PostMapping("/updatequestion")
    public String updateQuestionPost(@RequestParam(name = "id") String questionId, @RequestParam(name = "name") String questName,
                                     @RequestParam(name = "ans1") String answerVar1, @RequestParam(name = "ans2") String answerVar2,
                                     @RequestParam(name = "ans3") String answerVar3, @RequestParam(name = "ans4") String answerVar4,
                                     @RequestParam(name = "rghtAnswer") String answer, @RequestParam(name = "subject") String subject) {
        Question question = questionRepository.findById(Long.parseLong(questionId));
        List<String> answerList = new ArrayList<>();
        question.setQuestName(questName);
        answerList.add(answerVar1);
        answerList.add(answerVar2);
        answerList.add(answerVar3);
        answerList.add(answerVar4);
        question.setAnswerVariants(answerList);
        question.setAnswers(answer);
        question.setStudySubject(subjectRepository.findByName(subject));
        questionRepository.save(question);
        return "redirect:/updatequestion/" + questionId;
    }
}
