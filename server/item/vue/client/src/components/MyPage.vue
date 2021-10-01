<template>
  <div class="mypage">
    <h2>My Page</h2>  
    <table>
      <tr>
        <th>아이디</th>
        <th><input type="text" v-model="userId" disabled></th>
      </tr>
      
      <tr>
          <th>비밀번호</th>
          <th><input type="password" v-model="userPw"></th>
      </tr>
      <tr>
          <th>닉네임</th>
          <th><input type="text" v-model="nickName"></th>
      </tr>
      <tr>
          <th>이름</th>
          <th><input type="text" v-model="userName" disabled></th>
      </tr>
      <tr>
          <th>전화번호</th>
          <th><input type="text" v-model="number" ></th>
      </tr>
      <tr>
          <th>시</th>
          <th><input type="text" v-model="city"></th>
      </tr>
      <tr>
          <th>도시</th>
          <th><input type="text" v-model="street"></th>
      </tr>
      <tr>
          <th>집코드</th>
          <th><input type="text" v-model="zipcode"></th>
      </tr>
      
    </table>
    <button v-on:click="modifyMypage">수정</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
    name:'myPage',
  
    data: function(){
        return{
            userId: '',
            userPw: '',
            nickName:'',
            userName:'',
            number:'',
            city:'',
            street:'',
            zipcode:'',
        }
    },
    methods:{
    modifyMypage: function(){
        axios.put('/api/members/'+this.$route.params.id,{
            userId:this.userId,
            userPw:this.userPw,
            nickName:this.nickName,
            userNmae:this.userName,
            number:this.number,
            city:this.city,
            street:this.street,
            zipcode:this.zipcode
        }).then(res=>{
            console.log(res.data);
        });
    }
    },    
    created: function(){
        axios.get('/api/members/'+this.$route.params.id).then(res=>{
            console.log(res.data);
            this.userId=res.data.userId;
            this.userPw=res.data.userPw;
            this.nickName=res.data.nickName;
            this.userName=res.data.userName;
            this.number=res.data.number;
            this.city=res.data.address.city;
            this.street=res.data.address.street;
            this.zipcode=res.data.address.zipcode;          
        });
    }
}
</script>
 
<style>
.mypage{  
  padding: 20px;
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.8);
  width: 300px;
  height: 400px; 
}
.mypage table{
    margin-top : 20px;
}
.mypage button{
    margin-top:10px;
    box-sizing: border-box;
    border-radius: 5px;
    height: 50px;
    width: 15%; 
}
td, th {
  padding: 10px;
}
</style>