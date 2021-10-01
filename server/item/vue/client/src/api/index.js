
import axios from 'axios';


const config ={
    baseUrl: 'http://localhost:8080/'
};

// 2. API 함수들을 정리
function newMember(){
    return axios.get(config.baseUrl+'api/members');
}

function checkName(){
    return axios.get(config.baseUrl+'api/members/{userId}/duplicate');
}

function login(){
    return axios.get(config.baseUrl+'api/members/login');
}


//export
export{
    newMember,
    checkName,
    login
}


// const config ={
//     baseUrl: 'https://api.hnpwa.com/v0/'
// };

// // 2. API 함수들을 정리
// function fetchNewsList(){
//     return axios.get(config.baseUrl+'news/1.json');
// }

// function fectchAskList(){
//     return axios.get(config.baseUrl+'ask/1.json');
// }

// function fectchJobsList(){
//     return axios.get(config.baseUrl+'jobs/1.json');
// }

// //export
// export{
//     fetchNewsList,
//     fectchAskList,
//     fectchJobsList
// }