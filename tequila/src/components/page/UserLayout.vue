<template>
  <a-layout id="user-layout" class="layout">
    <a-layout-header>
      <a-menu
        v-model="current"
        mode="horizontal"
        theme="dark"
        :style="{ lineHeight: '64px' }"
      >
        <a-menu-item><Logo title="高校心理咨询平台" /> </a-menu-item>

        <a-menu-item key="board">
          <router-link to="/board"><a-icon type="mail" />公告 </router-link>
        </a-menu-item>

        <a-menu-item key="posts">
          <router-link to="/posts"><a-icon type="appstore" />文章 </router-link>
        </a-menu-item>

        <a-menu-item key="test">
          <router-link to="/test/intro"> <a-icon type="appstore" /> 在线测评</router-link>
        </a-menu-item>

        <a-menu-item key="talk">
          <router-link to="/talk"> <a-icon type="appstore" /> 留言板</router-link>
        </a-menu-item>

        <a-menu-item key="profile">
          <a-icon type="appstore" />个人中心
        </a-menu-item>
      </a-menu>
    </a-layout-header>
    <a-layout-content style="padding: 0 64px; margin: 48px 0 0 0">
      <div :style="{ background: '#fff', padding: '24px', minHeight: '280px' }">
        <router-view></router-view>
      </div>
    </a-layout-content>
    <a-layout-footer style="text-align: center">
      CPCP 高校心理资讯平台 ©2020
    </a-layout-footer>
  </a-layout>
</template>
<script>
import Logo from "../Logo";
export default {
  components: { Logo },
  data() {
      return {
        current: [''],
      };
    },
};
</script>
<style>
.title {
  font-size: 24px;
  color: rgba(0, 0, 0, 0.85);
  font-weight: 500;
  margin-bottom: 16px;
}

.main {
    font-size: 16px;
    margin: 0 8px;
    line-height: 1.75em;
}

.appendix {
  font-size: 14px;
  width: 100%;
  color: rgba(0, 0, 0, 0.55);
  font-weight: 500;
  margin: 16px 0;
}
</style>
