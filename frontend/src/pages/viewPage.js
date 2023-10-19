import React, { useState, useEffect } from "react";
import "../App.css";
import { useParams } from "react-router-dom";
import DetailHeader from "../components/DetailHeader";
import TypeSelect from "../components/TypeSelect";

function View() {
	const params = useParams();
	const restaurant = parseInt(params.restaurant);
	const [rate, setRate] = useState({});

	useEffect(() => {
		const fetchRestaurantData = async () => {
			// JSON 파일 또는 API URL 주소
			const nowUrl = `/api/get_congestion/restaurant${restaurant}`;
			// const nowUrl = `/assets/json/restaurant${restaurant}.json`;
			const res = await fetch(nowUrl, {
				headers: {
					"Content-Type": "application/json",
				},
				method: "GET",
			});
			const result = await res.json();
			// console.log(result);
			return result;
		};
		fetchRestaurantData().then((data) => {
			setRate(data);
		});
	}, [restaurant]);

	return (
		<>
			<div className="App2">
				<DetailHeader
					idx={restaurant}
					rate={(rate.connected / rate.capacity) * 100}
				/>
				<TypeSelect idx={restaurant} />
			</div>
		</>
	);
}

export default View;
