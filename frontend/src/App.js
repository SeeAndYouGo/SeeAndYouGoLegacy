import React, { useEffect, useState } from "react";
import Header from "./components/Header";
import UpdateLog from "./components/UpdateLog";
import Cafeteria from "./components/Cafeteria";
import "./App.css";

// 시간 정보가 포함된 식단 인원 정보 request
function App() {
	const [restaurantData, setRestaurantData] = useState([]);

	const updateCurrentTime = () => {
		const newTime = new Date();
		CallUpdateInfo(newTime);
	};

	const CallUpdateInfo = (time) => {
		if (time.getSeconds() === 0 && time.getMinutes() % 1 === 0) {
			// 분이 1의 배수이고 초가 0일 때만 데이터를 가져옵니다.
			fetchRestaurantData();
		} else {
			const hours = time.getHours();
			const minutes = time.getMinutes();
			const seconds = time.getSeconds();
			// console.log(`현재 시간: ${hours}:${minutes}:${seconds}`);
		}
	};

	useEffect(() => {
		fetchRestaurantData();
	}, []);

	useEffect(() => {
		// 1초마다 현재 시간을 업데이트합니다.
		const intervalId = setInterval(updateCurrentTime, 1000);

		return () => {
			// 컴포넌트가 언마운트될 때 clearInterval을 호출합니다.
			clearInterval(intervalId);
		};
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	const fetchRestaurantData = () => {
		// JSON 파일 또는 API URL 주소
		const jsonFilePaths = [
			"/api/get_congestion/restaurant1",
			"/api/get_congestion/restaurant2",
			"/api/get_congestion/restaurant3",
			"/api/get_congestion/restaurant4",
			"/api/get_congestion/restaurant5",
		];
		// const jsonFilePaths = [
		// 	"assets/json/restaurant1.json",
		// 	"assets/json/restaurant2.json",
		// 	"assets/json/restaurant3.json",
		// 	"assets/json/restaurant4.json",
		// 	"assets/json/restaurant5.json",
		// ];
		// Promise.all을 사용하여 여러 JSON 파일 가져오기
		Promise.all(
			jsonFilePaths.map((path) =>
				fetch(path).then((response) => response.json())
			)
		)
			.then((dataArray) => {
				setRestaurantData(dataArray);
				console.log(dataArray);
			})
			.catch((error) => console.error("Error fetching JSON:", error));
	};

	return (
		<div className="App">
			<a href='https://docs.google.com/forms/d/e/1FAIpQLSfGeuHsAH4fXrazXBLzSn1J7z3ux8in1OQInDH2LYHQnRiU5Q/viewform?usp=sf_link'>구글 폼!</a>
			<Header />
			{restaurantData.map((val, idx) =>
				idx === 0 ? (
					<UpdateLog key={idx} updateTime={val.dateTime} />
				) : null
			)}
			{restaurantData.map((val, idx) => {
				return (
					<Cafeteria
						idx={idx}
						key={idx}
						value={(val.connected / val.capacity) * 100}
					/>
				);
			})}
		</div>
	);
}

export default App;
