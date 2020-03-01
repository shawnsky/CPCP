<template>
  <div class="account-main">
    <div class="account-left"><UserNav skey="2"></UserNav></div>
    <div class="account-right">
      <div class="title">安全设置</div>
      <div class="setting" style="margin-bottom: 16px">
        <a-label>旧密码</a-label>
        <a-input-password placeholder="请输入现在的密码" v-model="oldPassword" />
      </div>

      <div class="setting" style="margin-bottom: 16px">
        <a-label>新密码</a-label>
        <a-input placeholder="建议使用安全强度高的密码" v-model="age"></a-input>
      </div>

      <a-button type="primary" style="float: right">更新设置</a-button>
    </div>
  </div>
</template>

<script>
import UserNav from "./UserNav";
export default {
  components: {
    UserNav
  },
  data() {
    return {
        oldPassword: '',
        newPassword: ''
    };
  }
};
</script>

<style lang="less" scoped>
.account-main {
  width: 100%;
  display: flex;
  height: 100%;
  overflow: auto;

  &.mobile {
    display: block;

    .account-left {
      border-right: unset;
      border-bottom: 1px solid #e8e8e8;
      width: 100%;
      height: 50px;
      overflow-x: auto;
      overflow-y: scroll;
    }
    .account-right {
      padding: 20px 40px;
    }
  }

  .account-left {
    border-right: 1px solid #e8e8e8;
    width: 224px;
  }

  .account-right {
    flex: 1 1;
    padding: 8px 40px;
    margin-left: 16px;

    .account-title {
      color: rgba(0, 0, 0, 0.85);
      font-size: 20px;
      font-weight: 500;
      line-height: 28px;
      margin-bottom: 12px;
    }
    .account-view {
      padding-top: 12px;
    }
  }
}
</style>
