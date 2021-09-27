import Vue from 'vue';
import VueRouter from 'vue-router';
import MemberRegister from '../components/MemberRegister.vue';
//import Login from '../components/Login.vue';
import Login from '../components/LoginTest.vue';
import  myPage  from '../components/MyPage.vue';

Vue.use(VueRouter);

export const router = new VueRouter({
    mode: 'history',
    routes:[
        {
            path:'/',
            redirect : '/login'
        },
        {
             // path : url 주소
             path: '/memberRegister',
            // url 주소로 갔을때 표시될 컴포넌트
             component: MemberRegister,
        },
        {
            // path : url 주소
            path: '/login',
            // url 주소로 갔을때 표시될 컴포넌트
            component: Login,
        },
        {
            path:'/myPage',
            component: myPage,
        },
    ]
});