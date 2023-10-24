import styled from "@emotion/styled";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAngleLeft } from "@fortawesome/free-solid-svg-icons";
import { faClock } from "@fortawesome/free-regular-svg-icons";
import { faMapLocationDot } from "@fortawesome/free-solid-svg-icons";
import { useState, useEffect } from "react";
import Modal from "./Modal";
import ModalLocation from "./ModalLocation";
import { Link } from "react-router-dom";

const CafeteriaName = styled.div`
	display: flex;
	align-items: center;
	text-align: center;
	font-size: 26px;
	font-weight: bold;
`;

const TimeInfo = styled.div`
	font-weight: 400;
	font-size: 11px;
	text-align: center;
	margin-left: -15px;
	padding-top: 5px;
	color: #777777;
`;

const Congestion = styled.div`
	text-align: center;
	padding-top: 15px;
	padding-left: 5px;
`;

const Location = styled.div`
	width: 50px;
	height: 50px;
	margin-top: 5px;
	margin-left: 90px;
	padding: 5px;
	text-align: center;
	background: white;
	border-radius: 5px;
	cursor: pointer;
`;

const restaurantArray = [
	"",
	"1학생회관",
	"2학생회관",
	"3학생회관",
	"상록회관",
	"생활과학대",
];

const operatingTime = "11:30-14:00";

const DetailHeader = ({ idx, rate }) => {
	const [visible1, setVisible1] = useState(false);
	const [status, setStatus] = useState("원활");

	useEffect(() => {
		if (rate >= 66) {
			setStatus("혼잡");
		} else if (rate >= 33) {
			setStatus("보통");
		} else {
			setStatus("원활");
		}
	}, [rate]);

	return (
		<div style={{ display: "flex" }}>
			<div style={{ width: 160 }}>
				<CafeteriaName>
					<Link to={`/`}>
						<FontAwesomeIcon icon={faAngleLeft} />
					</Link>
					<p style={{ margin: "0px 0px 0px 5px" }}>
						{restaurantArray[idx]}
					</p>
				</CafeteriaName>

				<TimeInfo>
					<FontAwesomeIcon icon={faClock} />
					<label style={{ marginLeft: 5 }}>
						운영 시간 {operatingTime}
					</label>
				</TimeInfo>
			</div>
			<Congestion>
				<img src={"/assets/images/People.png"} alt={"Loading..."} />
				<p style={{ margin: 0, fontSize: 10 }}>{status}</p>
			</Congestion>

			<Location onClick={() => setVisible1(true)}>
				<FontAwesomeIcon icon={faMapLocationDot} />
				<p style={{ margin: 0, fontSize: 10 }}>식당위치</p>
			</Location>
			<Modal visible={visible1} onClose={() => setVisible1(false)}>
				<ModalLocation restaurant={2} />
			</Modal>
		</div>
	);
};

export default DetailHeader;
