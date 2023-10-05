package com.seeandyougo.seeandyougo.service;

import com.seeandyougo.seeandyougo.dto.CongestionResponse;
import com.seeandyougo.seeandyougo.repository.ConnectedRepository;
import com.seeandyougo.seeandyougo.table.Connected;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConnectedService {
    private final ConnectedRepository connectedRepository;

    public Connected getRecentConnected(String restaurantName){
        String changeRestaurantName = changeRestaurantName(restaurantName);
        Connected result = connectedRepository.findRecent(changeRestaurantName);

        return result;
    }

    public String changeRestaurantName(String name){
        String res = name;
        if(name.contains("1")) res= "1학생회관";
        else if(name.contains("2")) res= "2학생회관";
        else if(name.contains("3")) res= "3학생회관";
        else if(name.contains("4")) res= "상록회관";
        else if(name.contains("5")) res= "생활과학대";
        return res;
    }
}
