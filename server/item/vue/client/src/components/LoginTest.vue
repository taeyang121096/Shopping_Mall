<template>
    <div class="login">
        <h2>Login</h2>
        <input type="text" v-model="id" placeholder="아이디">
        <input type="password" v-model="pwd" placeholder="비밀번호">
        <button v-on:click="login">로그인</button>
        <router-link to='/memberRegister'>회원가입</router-link>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data: function() {
        return{
            id: '',
            pwd: '',
        }
    },
    methods: {
        login: function(){
        if( this.id !=='' && this.pwd !=='') {
            let result;
            axios.post('/api/members/login',{userId:this.id, userPw:this.pwd})
            .then(res => {
              result = res.data.id;
              if(result!=0){
                this.$router.push({name: 'myPage',params:{id:result}});
              }else if(result==0){
                alert("로그인 실패");
              }
            })
            this.clearInput();
        }else{
            alert("로그인 실패");
          }
        },
        clearInput: function(){
            this.id='';
            this.pwd='';      
        },
    },
  //   created(){
  //   login()
  //     .then(response =>this.ask=response.data)
  //     .catch(error => console.log(error));
  // },
}
</script>

<style >
.login {
  background-color: #47473d;
  padding: 20px;
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.8);
  width: 300px;
  height: 400px;
}
.login h2 {
  margin-top: 20px;
  font-size: 2.5rem;
  color: white;
  margin-bottom: 20px;
}
.login input {
  border: none;
  background: none;
  text-align: center;
  outline: none;
  padding: 15px;
  margin: 10px;
  color: white;
  height: 25px;
  width: 60%;
  border-radius: 40px;
  transition: 0.2s ease-in;
}
.login input[type="text"],
.login input[type="password"] {
  border: solid 2px #ffc400;
}
.login button {
  margin-top:10px;
  border: solid 2px #ffc400;
  box-sizing: border-box;
  border-radius: 40px;
  background: #ec3b3b;
  height: 50px;
  width: 50%;
  cursor: pointer;
}
</style>