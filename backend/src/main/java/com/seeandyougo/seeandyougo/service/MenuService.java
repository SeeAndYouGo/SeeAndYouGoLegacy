package com.seeandyougo.seeandyougo.service;

import com.seeandyougo.seeandyougo.dto.MenuResponse;
import com.seeandyougo.seeandyougo.repository.MenuRepository;
import com.seeandyougo.seeandyougo.table.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final CashService cashService;

    public List<MenuResponse> getRestaurantMenu(String name, String date){
        String changeRestaurantName = changeRestaurantName(name);
        List<Menu> menusByNameAndDate = menuRepository.findMenusByNameAndDate(changeRestaurantName, date);
        return cashService.processMenus(menusByNameAndDate);
    }

    public String changeRestaurantName(String name){
        String res = "NULL";
        if(name.contains("1")) res= "1학생회관";
        else if(name.contains("2")) res= "2학생회관";
        else if(name.contains("3")) res= "3학생회관";
        else if(name.contains("4")) res= "상록회관";
        else if(name.contains("5")) res= "생활과학대";
        return res;
    }
}
