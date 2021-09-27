<template>
    <div class="register">
        <h2>Register</h2>
        <input type="text" v-model="userId" placeholder="아이디"> 
        <button class="idCheckBtn" v-on:click="nameCheck">중복</button>
            <div v-if="!check">
                <Modal v-if="showModal" @close="showModal = false">
                <template v-slot:header><h3>이미 존재하는 아이디입니다!
                    <i class="closeModalBtn fas fa-times" @click="showModal=false"></i>
                </h3>      
                </template>
                </Modal>    
            </div>
            <div v-if="check">
                <Modal v-if="showModal" @close="showModal = false">
                <template v-slot:header><h3>사용가능한 아이디입니다!
                    <i class="closeModalBtn fas fa-times" @click="showModal=false"></i>
                </h3>      
                </template>
                </Modal>
            </div>       
        <input type="password" v-model="userPw" placeholder="비밀번호">
        <input type="nickname" v-model="nickname" placeholder="닉네임">
        <input type="name" v-model="userName" placeholder="이름">
        <input type="address" v-model="address" placeholder="주소">
        <input type="number" v-model="number" placeholder="전화번호">
        <button class="registerBtn" v-on:click="regist">회원가입</button>
    </div>
</template>

<script>
import Modal from './Modal.vue'
export default {
    data: function() {
        return{   
            // 회원등록       
            Id,
            userId,
            userPw,
            nickName,
            userName,
            //address,
            city,
            street,
            zipcode,
            number,
            zipcode,
            Idlist:[],
            exist: false,
            showModal: false,
            check: false,
            success: false,
        }
    },
    methods: {
        nameCheck: function(){
            this.$axios.get('/api/members/'+this.userId+'/duplicate', {
            }).then((resp)=>{
                this.exist=resp;
            })
            if(this.exist){
                this.id=this.userId;
                this.check=true;
            }else{
                this.userId ='';
                this.showModal =!this.showModal;
            }
        },
        regist: function(){
        if( this.userId !=='' && this.userPw !=='' && check && this.id == this.userid) { 
            this.$axios.post('/api/members',{
                userid:this.userid, 
                userPw: this.userPw, 
                nickname: this.nickname,
                userName: this.userName,
                //address: this.address,
                number: this.number,
                city : this.city,
                street: this.street,
                zipcode : this.zipcode,
            }).then((resp)=>{
                this.success=resp;
            })
            if(resp){
                alert('회원가입 성공');
            }else{
                alert('회원가입 실패');
            }
            this.clearInput();
        }else{
            alert("아이디 중복체크를 해주세요");
        }
        },
        clearInput: function(){
            this.id='';
            this.userid='';
            this.userPw='';
            this.nickname='';
            this.userName='';
            //this.address='';
            this.number='';
            this.city='';
            this.street='';
            this.zipcode='';
            this.exist= false;
            this.showModal= false;
            this.check= false;
            this.success= false;    
        }
    },
    created: function(){
        if(localStorage.length >0){     
            for(var i=0; i< localStorage.length; i++){
                if(localStorage.key(i)!== 'loglevel:webpack-dev-server'){
                    this.Idlist.push(localStorage.key(i));
                }
            }
        }
    },
    components:{
        Modal: Modal
    }
}
</script>

<style>
.clearBtnContainer {
  width: 8.5rem;
  height: 35px;
  line-height: 35px;
  background-color: gray;
  border-radius: 5px;
  margin: 0 auto;
}
.closeModalBtn{
  color: #42b983;
}
.register {
  background-color: #47473d;
  padding: 20px;
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.8);
  width: 300px;
  height: 500px;
}
.register h2 {
  margin-top: 20px;
  font-size: 2.5rem;
  color: white;
}
.register input {
  border: none;
  background: none;
  text-align: center;
  outline: none;
  padding: 10px;
  margin: 10px;
  color: white;
  height: 30px;
  width: 70%;
  border-radius: 40px;
  transition: 0.2s ease-in;
}
.register input[type="text"],
.register input[type="password"],
.register input[type="nickname"],
.register input[type="name"],
.register input[type="address"],
.register input[type="number"] {
  border: solid 2px #ffc400;
}
.register .registerBtn{
  margin-top:10px;
  border: solid 2px #ffc400;
  box-sizing: border-box;
  border-radius: 40px;
  background: #ec3b3b;
  height: 50px;
  width: 50%;
  cursor: pointer;
}
.idCheckBtn{
    position: relative;
    left: 140px;
    bottom: 42px;
    border-radius: 5px;
    height: 40px;
    width: 40px;
    border: solid 2px #ffc400;
    background:#ec3b3b
}
</style>