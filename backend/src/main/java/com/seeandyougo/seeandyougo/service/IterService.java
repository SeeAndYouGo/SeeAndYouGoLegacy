package com.seeandyougo.seeandyougo.service;

import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IterService {
    private final RawMenuService rawMenuService;
    private final CashService cashService;
    private final RawWifiService rawWifiService;

    @Transactional
    @Scheduled(fixedRate = 300000, initialDelay = 1000)
    public void repeatCallWifi() throws Exception { // 하루에 한번씩 갱신하느건??
        System.out.println("hello");
        rawWifiService.saveRawWifiData();
        cashService.wifiCashing();
//        rawMenuService.saveTodayMenu();
    }

    @Transactional
    @Scheduled(fixedRate = 3000000, initialDelay = 1000)
    public void repeatCallMenu() throws Exception { // 하루에 한번씩 갱신하느건??
        rawMenuService.saveTodayMenu();
        cashService.menuTodayCashing();
    }
}
