<template>
  <div class="main user-layout-register">
    <div class="title">添加用户</div>
    <a-input
      size="large"
      type="text"
      placeholder="邮箱"
      v-model="inputEmail"
      style="width: 300px; margin: 8px 0"
    ></a-input>

    <a-input
      size="large"
      type="password"
      autocomplete="false"
      placeholder="密码"
      v-model="inputPassword"
      style="width: 300px; margin: 8px 0; display: block"
    ></a-input>

    <a-alert
      v-if="isRegisterError"
      type="error"
      showIcon
      style="margin-bottom: 24px;"
      message="该邮箱已存在"
    />

    <a-button
      size="large"
      type="primary"
      htmlType="submit"
      class="register-button"
      :loading="registerBtn"
      @click="doRegister"
      :disabled="registerBtn"
      style="width: 300px; margin: 16px 0; display: block"
      >添加
    </a-button>
  </div>
</template>

<script>
import api from '@/api/index'
import axios from "axios";

export default {
  name: "Register",
  components: {},
  data() {
    return {
      inputEmail: "",
      inputPassword: "",
      isRegisterError: false,

      registerBtn: false
    };
  },
  methods: {
    doRegister() {
      axios
        .post(api.Register, {
          username: this.inputEmail,
          password: this.inputPassword
        }, {
          headers: { Authorization: localStorage.token }
        })
        .then(response => {
          var code = response.data.code;
          if (code == 1) {
            this.$message.success('用户添加成功')
          } else if (code == 0) {
            this.isRegisterError = true;
          }
        })
    },
  }
};
</script>
<style lang="less">
.user-register {
  &.error {
    color: #ff0000;
  }

  &.warning {
    color: #ff7e05;
  }

  &.success {
    color: #52c41a;
  }
}

.user-layout-register {
  .ant-input-group-addon:first-child {
    background-color: #fff;
  }
}
</style>
<style lang="less" scoped>
.user-layout-register {
  & > h3 {
    font-size: 16px;
    margin-bottom: 20px;
  }

  .register-button {
    width: 50%;
  }

}

</style>
