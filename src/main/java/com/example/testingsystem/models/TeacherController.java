package com.example.testingsystem.models;

import com.example.testingsystem.repositories.*;
import com.example.testingsystem.services.TestService;
import com.example.testingsystem.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TeacherController {
    @Autowired
    TestRepository testRepository;
    @Autowired
    UserStatRepository userStatRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TestService testService;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    private GroupsRepository groupsRepository;

    @GetMapping("/studentsstatistic/{testId}")
    public String getStudentStatisticForTeacher(@PathVariable String testId, Model model) {
        Test test = testRepository.findById(Long.parseLong(testId));
        model.addAttribute("statistics", userStatRepository.findByTest(test));

        return "studentsstatforteacher";
    }

    @GetMapping("/teacherlist")
    public String teacherList(Model model) {
        List<User> teacherList = userRepository.findByUserRolesContaining(Roles.TEACHER);
        model.addAttribute("teacherList", teacherList);
        return "teacherlist";
    }

    @GetMapping("/teacherupdate/{id}")
    public String getTeacherUpdate(@PathVariable String id, Model model) {
        User user = userRepository.findById(Long.parseLong(id));
        List<StudySubject> studySubjectList = subjectRepository.findAll();
        List<Groups> groupsList = groupsRepository.findAll();
        model.addAttribute("subject", studySubjectList);
        model.addAttribute("user", user);
        model.addAttribute("teacherGroupList", user.getGroupList());
        model.addAttribute("groups", groupsList);
        return "teacherupdate";
    }

    @PostMapping("/studentsstatistic/{testId}")
    public String postStudentStatisticForTeacher(@RequestParam(name = "sortedBy") String sorting, @PathVariable String testId, Model model) {
        Test test = testRepository.findById(Long.parseLong(testId));
        Map<String, Double> resultMap = new HashMap<>();
        if (sorting.equals("A-Z")) {
            model.addAttribute("statistics", testService.sortAZ(userStatRepository.findByTest(test)));
        } else if (sorting.equals("Z-A")) {
            model.addAttribute("statistics", testService.sortZA(userStatRepository.findByTest(test)));
        } else if (sorting.equals("0-100")) {
            model.addAttribute("statistics", testService.sort0100(userStatRepository.findByTest(test)));
        } else if (sorting.equals("100-0")) {
            model.addAttribute("statistics", testService.sort1000(userStatRepository.findByTest(test)));
        }
        return "studentsstatforteacher";
    }

    @PostMapping("/teacherupdate/{id}")
    public String postTeacherUpdate(@PathVariable String id, @RequestParam(name = "teacherName", required = false) String teacherName,
                                    @RequestParam(name = "subject", required = false) String teacherSubject) {
        User user = userRepository.findById(Long.parseLong(id));
        user.setName(teacherName);
        user.setStudySubject(subjectRepository.findByName(teacherSubject));
        userRepository.save(user);
        return "redirect:/teacherlist";
    }

    @PostMapping("/teacherupdategroup/{id}")
    public String addGroupToTeacher(@PathVariable String id, @RequestParam(name = "groupId") String groupId) {
        User user = userRepository.findById(Long.parseLong(id));
        Groups groups = groupsRepository.findById(Long.parseLong(groupId));
        user.getGroupList().add(groups);
        userRepository.save(user);
        return "redirect:/teacherupdate/" + id;
    }
}
