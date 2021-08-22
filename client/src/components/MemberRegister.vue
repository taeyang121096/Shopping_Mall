<template>
    <div id="memberRegister">
        <h1>Register</h1>
        <div>
            <input type="text" v-model="id" placeholder="아이디">
            <button v-on:click="nameCheck">중복확인</button>
        </div> 
        <div><input type="password" v-model="pwd" placeholder="비밀번호"></div>
        <div><input type="name" v-model="name" placeholder="이름"></div>
        <div><input type="phoneNumber" v-model="phoneNumber" placeholder="전화번호"></div>
        <br>
        <div class="clearBtnContainer" v-on:click="login">
            <span class="loginBtn" >회원가입</span>
        </div>
    </div>
</template>

<script>
export default {
    data: function() {
        return{
            id: '',
            pwd: '',
            name:'',
            phoneNumber:'',
            Idlist:[],
            exist: false,
        }
    },
   
    methods: {
        nameCheck: function(){
            console.log(this.Idlist.length);
            for(var check in this.Idlist){
                console.log(check);
                if(this.id==check){
                    this.exist = true;
                }
            }
            if(this.exist== true){
                this.id ='';
                //this.exist=false;
            }
        },
        login: function(){
        if( this.id !=='' && this.pwd !=='') { 
            var obj = {id: this.id, password: this.pwd, name: this.name, phoneNumber:this.phoneNumber};
            localStorage.setItem(this.id, JSON.stringify(obj));
            this.clearInput();
        }
        },
        clearInput: function(){
            this.id='';
            this.pwd='';
            this.name='';
            this.phoneNumber='';    
        }
    },
    create: function(){
        if(localStorage.length >0){
            for(var i=0; i< localStorage.length; i++){
                if(localStorage.key(i)!== 'loglevel:webpack-dev-server'){
                    console.log("11");
                    this.Idlist.push(JSON.parse(localStorage.getItem(localStorage.key(i))).getItem(0));
                    
                }
            }
        }
    
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
.loginBtn {
  color: white;
  /* 추가 */
  display: block;
}
</style>