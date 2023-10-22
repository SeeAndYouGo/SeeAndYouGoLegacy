package com.seeandyougo.seeandyougo.service;

import com.seeandyougo.seeandyougo.repository.RawMenuRepository;
import com.seeandyougo.seeandyougo.table.RawMenu;
import lombok.RequiredArgsConstructor;
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
public class RawMenuService {
    private final RawMenuRepository rawMenuRepository;


    @Transactional
//    @Scheduled(fixedDelay = 5000)
    public void saveAllMenuData() throws Exception {
        Long idx = 1L;

        while (true) {
            String apiUrl = "https://api.cnu.ac.kr/svc/offcam/pub/FoodInfo?page=" + idx + "&AUTH_KEY=D6E3BE404CC745B885E81D6BD5FE90CD6A59E572"; // API 엔드포인트

            // URL 생성
            URL url = new URL(apiUrl);

            // HttpURLConnection 설정
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 응답 코드 확인
            int responseCode = connection.getResponseCode();
            String responseData = new String();

            // 응답 내용 읽기
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) response.append(line);

                reader.close();
                responseData = response.toString();
            }
            if (responseData.contains("\"MSG\":\"N\"")) {
                // MSG가 "N"이면 멈춤
                break;
            }

            RawMenu rawMenu = new RawMenu();
            rawMenu.setId(idx);
            rawMenu.setRaw(responseData);
            rawMenuRepository.save(rawMenu);
            idx++;
        }
    }

    @Transactional
    public void saveTodayMenu() throws Exception {
        String apiUrl = "https://api.cnu.ac.kr/svc/offcam/pub/FoodInfo?page=1&AUTH_KEY=D6E3BE404CC745B885E81D6BD5FE90CD6A59E572"; // API 엔드포인트

        // URL 생성
        URL url = new URL(apiUrl);

        // HttpURLConnection 설정
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // 응답 코드 확인
        int responseCode = connection.getResponseCode();
        String responseData = new String();

        // 응답 내용 읽기
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) response.append(line);

            reader.close();
            responseData = response.toString();
        }
        if (responseData.contains("\"MSG\":\"N\"")) {
            // MSG가 "N"이면 멈춤
            return;
        }
        RawMenu rawMenu = new RawMenu();
        rawMenu.setRaw(responseData);
        rawMenuRepository.save(rawMenu);
    }
}
