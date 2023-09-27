import React, { useEffect, useState } from 'react';

import "./Login.css";

export default function Login() {

    return (
        <div className="App">
            <a href="http://localhost:8080/find-id/"
               target="_blank"
               rel="noopener noreferrer">
                아이디 찾기이동</a>
            <a href="http://localhost:8080/find_pass/"
               target="_blank"
               rel="noopener noreferrer">
                비밀번호 찾기이동</a>
        </div>
       // <div>
       //     로그인 화면
       // </div>
    );
}
