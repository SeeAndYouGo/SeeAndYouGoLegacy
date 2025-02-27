import styled from "@emotion/styled";
import React from "react";

const MenuContainer = styled.div`
	width: 100%;
	background-color: white;
	border-radius: 20px;
	margin-top: 15px;
	padding-bottom: 25px;
`;

const TypeName = styled.p`
	font-size: 15px;
	margin-left: 15px;
	font-weight: 700;
	color: #555555;

	::after {
		content: "";
		display: block;
		width: 50px;
		border-bottom: 3px solid #000000;
		margin: 0px 10px;
		align-items: center;
		text-align: center;
		padding-top: 2px;
	}
`;

const Price = styled.p`
	width: 60px;
	margin: 20px 10px;
	text-align: center;
	background-color: #555555;
	color: white;
	border-radius: 5px;
	font-size: 12px;
	text-align: center;
	font-weight: 400;
`;

const Menu = ({ value }) => {
	return (
		<MenuContainer>
			<div style={{ display: "flex" }}>
				<TypeName>{value.dept}</TypeName>
				<Price>{value.price}</Price>
			</div>
			<div style={{ textAlign: "center" }}>
				{value.menu.map((menu, index) => (
					<p
						key={index}
						style={{ margin: 0, fontSize: 15, fontWeight: 400 }}
					>
						{menu}
					</p>
				))}
			</div>
		</MenuContainer>
	);
};

export default Menu;
