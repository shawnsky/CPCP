<template>
  <div>
    <div v-for="(item, index) in questions" :key="index">
      <div class="vbnm">题目 #{{index+1}}</div>
      <a-input class="zxc6" size="large" placeholder="请输入题目的内容。如：你经常感到焦虑吗？" v-model="questions[index]"> </a-input>
      <a-input class="zxc7" size="large" placeholder="请输入4个选项, 其间用空格分隔。如：从来没有 偶尔 经常 总是" v-model="choices[index]"> </a-input>
    </div>
    <a-button type="primary" @click="show" size="large" style="margin-top: 16px">上传</a-button>
  </div>
</template>

<script>
const arr0 = new Array(10)
const arr1 = new Array(10)
export default {
  data() {
    return {
      questions: arr0,
      choices: arr1
    };
  },
  methods: {
    show() {
      console.log(this.questions)
    }
  }
};
</script>

<style scoped>
.vbnm {
  font-size: 1.125em;
  margin: 4px 0;
}

.zxc6 {
  margin: 4px 0;
}
</style>