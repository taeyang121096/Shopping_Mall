<template>
  <div id="app">
    <Login></Login>
    <br><br>
    <MemberRegister></MemberRegister> 
  </div>
</template>

<script>
import Login from './components/Login.vue'
import MemberRegister from './components/MemberRegister'
export default {
  components: {
    Login: Login,
    MemberRegister: MemberRegister
  }
}
</script>

<style>
body{
  text-align: center;
  background-color: #f6f6F6;
}
input{
  border-style: groove;
  width:200px;
}
button{
  border-style:groove;
}
.shadow{
  box-shadow: 5px 10px 10px rgba(0,0,0,0.03);
}
</style>
