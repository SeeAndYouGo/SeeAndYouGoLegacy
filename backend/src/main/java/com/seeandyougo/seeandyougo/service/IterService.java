package com.seeandyougo.seeandyougo.service;

import com.seeandyougo.seeandyougo.repository.ConnectedRepository;
import com.seeandyougo.seeandyougo.repository.RawMenuRepository;
import com.seeandyougo.seeandyougo.repository.RawWifiRepository;
import com.seeandyougo.seeandyougo.table.RawWifi;
import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IterService {
    private final RawMenuService rawMenuService;
    private final CashService cashService;
    private final RawWifiService rawWifiService;
    private final RawWifiRepository rawWifiRepository;
    private final ConnectedRepository connectedRepository;

    @Transactional
    @Scheduled(fixedRate = 60000, initialDelay = 1000)
    public void repeatCallWifi() throws Exception { // 하루에 한번씩 갱신하느건??
//        System.out.println("hello");
        rawWifiService.saveRawWifiData();
        cashService.wifiCashing();
//        rawMenuService.saveTodayMenu();
    }

    @Transactional
    @Scheduled(cron="0 0 0 * * *", initialDelay = 1000)
    public void repeatCallMenu() throws Exception { // 하루에 한번씩 갱신하느건??
        rawMenuService.saveTodayMenu();
        cashService.menuTodayCashing();
    }
}
