package ru.netology.conditional.controller;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.conditional.profile.SystemProfile;
@RestController
@RequestMapping("/")
@AllArgsConstructor
public class ProfileController {
    private SystemProfile profile;

    @GetMapping("profile")
    public String getProfile(){
        return profile.getProfile();
    }

}
