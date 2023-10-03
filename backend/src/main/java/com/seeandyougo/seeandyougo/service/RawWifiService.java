package com.seeandyougo.seeandyougo.service;

import com.seeandyougo.seeandyougo.repository.RawWifiRepository;
import com.seeandyougo.seeandyougo.table.RawWifi;
import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RawWifiService { //
    private final RawWifiRepository rawWifiRepository;

    @Transactional
    public void saveRawWifiData() throws Exception{

        // String apiUrl = "http://wifi-dummy-api:8080/svc/offcam/pub/WifiAllInfo?AUTH_KEY=efefef"; // API 엔드포인트
        String apiUrl = "https://api.cnu.ac.kr/svc/offcam/pub/WifiAllInfo?AUTH_KEY=D6E3BE404CC745B885E81D6BD5FE90CD6A59E572";
        
        // URL 생성
        URL url = new URL(apiUrl);
        // HttpURLConnection 설정
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // 응답 코드 확인
        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);
        String responseData = new String();

        // 응답 내용 읽기
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            responseData = response.toString();
        }

        RawWifi rawWifi = new RawWifi();
        rawWifi.setRaw(responseData);
        rawWifiRepository.save(rawWifi);
    }

}
