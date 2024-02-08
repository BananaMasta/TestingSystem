package com.example.testingsystem.models;

import com.example.testingsystem.repositories.GroupsRepository;
import com.example.testingsystem.repositories.TestRepository;
import com.example.testingsystem.repositories.UserRepository;
import com.example.testingsystem.repositories.UserStatRepository;
import com.example.testingsystem.services.GroupService;
import com.example.testingsystem.services.TestService;
import com.example.testingsystem.services.UserService;
import com.example.testingsystem.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserStatRepository userStatRepository;
    @Autowired
    TestRepository testRepository;
    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    GroupService groupService;
    @Autowired
    TestService testService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/mainpage")
    public String mainPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user.getId());
        return "mainpage";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @GetMapping("/usercreate")
    public String getUserCreate(Model model) {
        model.addAttribute("user", new User());
        return "usercreate";
    }

    @GetMapping("/personalarea")
    public String getUserStatistic(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userName", user.getName());
        model.addAttribute("userGroup", user.getGroup());
        model.addAttribute("userStatList", userStatRepository.findByUser(user));
        return "personalarea";
    }

    @GetMapping("/addstudentintogroup")
    public String addStudentIntoGroup(Model model) {
        List<User> userList = userRepository.findByUserRolesContaining(Roles.STUDENT);
        List<Groups> groupsList = groupsRepository.findAll();
        model.addAttribute("users", userList);
        model.addAttribute("groups", groupsList);
        return "studentcreatepage";
    }

    @PostMapping("/registration")
    public String createUser(@RequestParam(name = "userrole") Roles role, @RequestParam(name = "firstname") String userLogin, @RequestParam(name = "password") String userPassword) {
        User user = new User(userLogin);
        user.setPassword(passwordEncoder.encode(userPassword));
        if (!userService.registrationUser(user, role)) {
            return "redirect:/registration";
        } else {
            return "login";
        }
    }

    @PostMapping("/usercreate")
    public String postUserCreate(@RequestParam(name = "userlogin") String userLogin, @RequestParam(name = "userpass") String userPassword, @RequestParam(name = "userrole") Roles role) {
        User user = new User(userLogin);
        user.setPassword(passwordEncoder.encode(userPassword));
        if (!userService.registrationUser(user, role)) {
            return "redirect:/usercreate";
        } else {
            return "mainpage";
        }
    }

    @PostMapping("/groupcreate")
    public String groupCreate(@RequestParam(name = "groupName") String group) {
        groupsRepository.save(groupService.groupCreate(group));
        return "mainpage";
    }

    @PostMapping("/addstudentintogroup")
    public String addStudentIntoGroup(@RequestParam(name = "studentId") String studentId, @RequestParam(name = "groupSelect") String groupName) {
        Groups group = groupsRepository.findByGroupName(groupName);
        User user = userRepository.findById(Long.parseLong(studentId));
        List<User> userList = group.getStudentList();
        userList.add(user);
        groupsRepository.save(group);
        return "studentcreatepage";
    }

    @PostMapping("/personalarea")
    public String postUserStatistic(@AuthenticationPrincipal User user, Model model) {
        Map<String, Double> a = new HashMap<>();
        for (int i = 0; i < userStatRepository.findByUser(user).size(); i++) {
            a.put(userStatRepository.findByUser(user).get(i).getTest().getTestName(), userStatRepository.findByUser(user).get(i).getUserTestScore());
        }
        model.addAttribute("userStatList", testService.userStat(a));
        model.addAttribute("userStatList", userStatRepository.findByUser(user));

        return "personalarea";
    }
}
