<template>
    <div id="login">
        <h1>Login</h1>
        <div><input type="text" v-model="id" placeholder="아이디"></div> 
        <div><input type="password" v-model="pwd" placeholder="비밀번호"></div>
        <br>
        <div class="loginBtnContainer" v-on:click="login">
            <span class="loginBtn" >로그인</span>
        </div>
    </div>
</template>

<script>
export default {
    data: function() {
        return{
            id: '',
            pwd: ''
        }
    },
    IdCheck: function(){
        return{
            Idlist:[]
        }
    },
    methods: {
        login: function(){
        if( this.id !=='' && this.pwd !=='') { 
            var obj = {id: this.id, password: this.pwd};
            localStorage.setItem(this.id, JSON.stringify(obj));
            this.clearInput();
            }
        },
        clearInput: function(){
            this.id='';
            this.pwd='';      
        },
        checkId: function(){
            
        }
    },
    create: function(){
        if(localStorage.length >0){
            for(var i=0; i< localStorage.length; i++){
                if(localStorage.key(i)!== 'loglevel:webpack-dev-server'){
                    this.Idlist.push(localStorage.key(i));
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