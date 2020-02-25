<template>
     <div class="left-msg-item">
        <a-avatar id="msg-item-avatar" slot="avatar" :src="avatar"/>
        <div class="msg-item-text">{{content}}</div>
      </div>
</template>

<script>
export default {
    name: "LeftMsgItem",
    props: {
        avatar: {
            type: String,
            default: "https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png",
            required: true
        },
        content: {
            type: String,
            default: '',
            required: true
        }
    }
}
</script>

<style>
.left-msg-item {
  margin: 8px 0 8px 0;
  display: block;
  height: 36px;
}

.left-msg-item .msg-item-avatar {
  height: 36px;
}

.left-msg-item .msg-item-text {
   display: inline-block;
   height: 36px;
   
   line-height: 36px;
   margin-left: 8px;
   margin-right: 8px;
   padding-left: 8px;
   padding-right: 8px;
   
   background-color: rgba(252, 221, 127, 0.03);
   border: 1px solid rgba(211, 209, 224, 0.35);
   border-radius: 5px;
}
</style>