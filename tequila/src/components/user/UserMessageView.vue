<template>
  <div class="account-main">
    <div class="account-left"><UserNav skey="3"></UserNav></div>
    <div class="account-right">
      <div class="account-title">消息中心</div>
      <a-card style="height: 400px; background-color: #fafafa; overflow: auto">
        <MsgItem
        pos="left"
          content="你好"
          avatar="https://i.loli.net/2020/03/02/TP81J27RbG3yixC.jpg"
        ></MsgItem>
        <MsgItem
        pos="right"
          content="你好"
          avatar="https://i.loli.net/2020/03/02/wbq7roCxJefpun6.png"
        ></MsgItem>
        <MsgItem
        pos="left"
          content="你好"
          avatar="https://i.loli.net/2020/03/02/TP81J27RbG3yixC.jpg"
        ></MsgItem>
        <MsgItem
        pos="right"
          content="你好"
          avatar="https://i.loli.net/2020/03/02/wbq7roCxJefpun6.png"
        ></MsgItem>
        <MsgItem
          content="你好"
          avatar="https://i.loli.net/2020/03/02/TP81J27RbG3yixC.jpg"
        ></MsgItem>
        
        
      </a-card>

      <div class="action-area">
        <a-icon
          type="picture"
          style="font-size: 1.25em; margin: 8px 8px 8px 16px"
        />
        <a-icon type="paper-clip" style="font-size: 1.25em; margin: 8px" />
      </div>
      <div class="input-area">
        <textarea
          placeholder="回复一下吧～"
          style="resize: none; outline: 0; border: 0; margin: 0; width: 100%; height: 60px; background-color: #fafafa"
          v-model="msgInput"
          maxlength="500"
        ></textarea>
        <div style="float: right;">
          <span style="color: #c5c5c5">{{ msgInput.length }}/500 字</span>
          <a-button style="margin: 16px 16px; width: 80px" @click="send"
            >发送</a-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserNav from "./UserNav";
import MsgItem from './MsgItem'
export default {
  components: {
    UserNav,
    MsgItem
  },
  data() {
    return {
      msgInput: ""
    };
  },
  methods: {
    send() {
      console.log(this.msgInput);
    }
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
    .title-area {
      border-left: 1px solid #e5e5e5;
      border-right: 1px solid #e5e5e5;
      border-top: 1px solid #e5e5e5;
      height: 45px;
    }
    .input-area {
      border-left: 1px solid #e5e5e5;
      border-right: 1px solid #e5e5e5;
      border-bottom: 1px solid #e5e5e5;
      height: 150px;
      padding: 16px;
      background-color: #fafafa;
    }
    .action-area {
      border-top: 1px solid #e5e5e5;
      border-left: 1px solid #e5e5e5;
      border-right: 1px solid #e5e5e5;
      // background-color: #f1f1f1
      background-color: #fafafa;
    }
  }
}
</style>
