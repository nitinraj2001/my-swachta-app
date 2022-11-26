package com.hacknitr.wastemanagement.util;

import com.hacknitr.wastemanagement.model.Society;
import com.hacknitr.wastemanagement.model.User;
import com.hacknitr.wastemanagement.repository.SocietyRepository;
import com.hacknitr.wastemanagement.repository.UserRepository;
import com.hacknitr.wastemanagement.sevice.SocietyService;
import com.hacknitr.wastemanagement.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CalculateRewards {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SocietyRepository societyRepository;
    @Autowired
    private SocietyService societyService;

    private Map<String,User> computeMaxCreditUser(){
        Map<String,User> result=new HashMap<>();
        List<User> users=userRepository.findAll();
        List<Society> societies=societyService.allSocieties();
        //find user with max credit per society
        for(Society society:societies){
            String name=society.getName();
            List<User> theUser=new ArrayList<User>();
            for(User user:users){
                if(user.getSocietyName().equals(name))theUser.add(user);
            }
            //due to some reasons we have set 1st user
            User reqUser=theUser.get(0);
            for(User user:theUser) {
                if(user.getCredit()>reqUser.getCredit())reqUser=user;
            }
            result.put(reqUser.getSocietyName(),reqUser);
        }
        return result;
    }

    @Scheduled(fixedRate = 1000*60*60*24*7)
    public void execute(){
        Map<String,User> map=computeMaxCreditUser();
        for(Map.Entry entry:map.entrySet()) {
            User user=(User)entry.getValue();
            System.out.println("User with username"+user.getUsername()+" have won a 500 rupee vocher for waste management of society"+entry.getKey().toString());
        }
    }
}

class CreditComparator implements Comparator<User> {

    // override the compare() method
    public int compare(User s1, User s2)
    {
        if (s1.getCredit() == s2.getCredit())
            return 0;
        else if (s1.getCredit() > s2.getCredit())
            return 1;
        else
            return -1;
    }
}


