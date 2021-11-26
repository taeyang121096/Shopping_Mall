<template>
  <div>
    <header class="app-header">
      <app-nav></app-nav>
    </header>
    <main class="container">
      <router-view></router-view>
    </main>
  </div>
</template>

<script>
//import MoveMember from './components/MoveMember.vue'
import AppNav from './components/AppNav'
export default {
  //components: { MoveMember },
  AppNav
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
body {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}
</style>