import Vue from 'vue'
import Router from 'vue-router'

// import HelloWorld from './components/page/HelloWorld'
import LoginPage from './components/page/LoginPage'
import RegisterPage from './components/page/RegisterPage'
import UserLayout from './components/page/UserLayout'
import AdminLayout from './components/page/AdminLayout'
import BoardView from './components/board/BoardView'
import PostsView from './components/post/PostsView'
import DetailView from './components/post/DetailView'
import IntroView from './components/test/IntroView'
import TestView from './components/test/TestView'
import TalkView from './components/talk/TalkView'
// admin
import UserView from './components/admin/user/UserView'
import UserAddView from './components/admin/user/UserAddView'
import AdminBoardView from './components/admin/board/AdminBoardView'
import AdminPostView from './components/admin/post/AdminPostView'
import AdminPostAddView from './components/admin/post/AdminPostAddView'
import AdminTalkView from './components/admin/talk/AdminTalkView'
import AdminTestView from './components/admin/test/AdminTestView'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'index',
      redirect: '/board',
      component: UserLayout,
      children: [
        {
          path: '/board',
          component: BoardView
        },
        {
          path: '/posts',
          component: PostsView
        },
        {
          path: '/post/:id',
          component: DetailView
        },
        {
          path: '/test',
          component: TestView
        },
        {
          path: '/test/intro',
          component: IntroView
        },
        {
          path: 'talk',
          component: TalkView
        }
        
      ]
    },
    {
      path: '/admin',
      component: AdminLayout,
      redirect: '/admin/board',
      children: [
        {
          path: 'user',
          component: UserView
        },
        {
          path: 'user/add',
          component: UserAddView
        },
        {
          path: 'board',
          component: AdminBoardView
        },
        {
          path: 'post',
          component: AdminPostView
        },
        {
          path: 'post/add',
          component: AdminPostAddView
        },
        {
          path: 'talk',
          component: AdminTalkView
        },
        {
          path: 'test',
          component: AdminTestView
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPage
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterPage
    }
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (about.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    // }
  ]
})