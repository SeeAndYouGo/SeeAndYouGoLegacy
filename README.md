# SeeAndYouGo V1

# api 명세

#### 2학 혼잡도 가져오기

<details>
 <summary><code>GET</code> <code><b>/{BE_NAME}/get_congestion/restaurant2</b></code> <code>(2학생식당의 혼잡도를 가져온다.)</code></summary>

##### Parameters

> None

##### Responses

> | http code | content-type       | response                         |
> | --------- | ------------------ | -------------------------------- |
> | `200`     | `application/json` | {"capacity":500, "connected":13} |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/kjs/get_congestion/restaurant2
> ```

</details>

# 요구사항

## 백엔드

- 접속자 정보를 공공 API로 가져와야 한다.
- 요청받은 장소의 총 접속자 수를 집계 후 수용 가능 인원화 함께 반환한다.
- 공공 API의 쿼리 결과를 캐싱한다. 캐시의 수명은 1분으로 한다.

## 프론트엔드

- 요청받은 장소의 혼잡도를 시각화한다.

## 공통

- Windows와 MacOS를 대상으로 종속패키지 세팅부터 프로젝트 실행까지 한번에 가능한 스크립트 파일을 각각 하나씩 만든다.
