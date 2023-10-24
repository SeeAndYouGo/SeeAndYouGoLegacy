import { useState, useEffect } from "react";
import styled from "@emotion/styled";
import React from "react";
import Menu from "./Menu";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleExclamation } from "@fortawesome/free-solid-svg-icons";
import Moment from "moment";
import "moment/locale/ko";

const TabMenu = styled.ul`
	color: black;
	font-weight: bold;
	display: flex;
	flex-direction: row;
	align-items: center;
	list-style: none;
	margin-top: 10px;
	border: solid 1.5px black;
	border-radius: 20px;
	padding: 5px;

	.submenu {
		padding: 5px 10px;
		margin-right: 5px;
		text-align: center;
		font-size: 10px;
		transition: 0.5s;
		border-radius: 20px;
		cursor: pointer;
	}

	.focused {
		background-color: black;
		color: white;
	}

	& div.desc {
		text-align: center;
	}
`;

const Desc = styled.div`
	text-align: center;
`;

const TypeSelect = ({ idx }) => {
	// Tab Menu 중 현재 어떤 Tab이 선택되어 있는지 확인하기 위한 currentTab 상태와 currentTab을 갱신하는 함수가 존재해야 하고, 초기값은 0.
	const [currentTab, clickTab] = useState(0);
	const [menuData, setMenuData] = useState([]);
	const myDate = Moment().format("YYYYMMDD");

	useEffect(() => {
		const fetchData = async () => {
			const nowUrl = `/api/get_menu/restaurant${idx}/${myDate}`;
			// const nowUrl = "/assets/json/myMenu2.json";
			const res = await fetch(nowUrl, {
				headers: {
					"Content-Type": "application/json",
				},
				method: "GET",
			});
			const result = await res.json();
			return result;
		};
		fetchData().then((data) => {
			setMenuData(data);
		});
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	const TabMenuUl = () => {
		return (
			<TabMenu>
				{menuData.map((nowValue, index) => (
					<li
						key={index}
						className={
							index === currentTab ? "submenu focused" : "submenu"
						}
						onClick={() => selectMenuHandler(index)}
					>
						{nowValue.dept}
					</li>
				))}
				<FontAwesomeIcon
					icon={faCircleExclamation}
					style={{ marginLeft: 15, fontSize: 12 }}
				/>
				<span style={{ fontSize: 10, marginLeft: 5, fontWeight: 400 }}>
					교직원은 학생도 이용 가능합니다.
				</span>
			</TabMenu>
		);
	};

	const selectMenuHandler = (index) => {
		// parameter로 현재 선택한 인덱스 값을 전달해야 하며, 이벤트 객체(event)는 쓰지 않는다
		// 해당 함수가 실행되면 현재 선택된 Tab Menu 가 갱신.
		clickTab(index);
	};

	return (
		<>
			<div style={{ marginTop: 30 }}>
				{idx === 2 || idx === 3 ? <TabMenuUl /> : null}
				{menuData.map((nowValue, index) => {
					return (
						<Desc key={index}>
							{currentTab === index ? (
								<Menu value={nowValue} />
							) : null}
						</Desc>
					);
				})}
			</div>
		</>
	);
};

export default TypeSelect;
