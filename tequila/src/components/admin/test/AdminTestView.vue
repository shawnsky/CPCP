<template>
  <div>
    <a-list size="large" bordered :dataSource="data" itemLayout="vertical">
      <a-list-item slot="renderItem" slot-scope="item, index" >
        <a-input
          :addonBefore="'问题 ' + (index+1)"
          class="test-entry-input"
          size="large"
          placeholder="请输入题目的内容。如：你经常感到焦虑吗？"
          v-model="data[index].q"
        >
        </a-input>
        <a-input
          :addonBefore="'选项 ' + (index+1)"
          class="test-entry-input"
          size="large"
          placeholder="请输入4个选项, 其间用空格分隔。如：从来没有 偶尔 经常 总是"
          v-model="data[index].a"
        ></a-input>
      </a-list-item>
      <div slot="header">编辑测试题（问题1～问题10）</div>
    </a-list>
    
    <a-button
      type="primary"
      @click="submit"
      size="large"
      style="margin-top: 16px"
      >上传</a-button
    >
  </div>
</template>

<script>
import api from "@/api/index";
import axios from "axios";
export default {
  data() {
    return {
      data: [
        {q: '', a: ''},
        {q: '', a: ''},
        {q: '', a: ''},
        {q: '', a: ''},
        {q: '', a: ''},
        {q: '', a: ''},
        {q: '', a: ''},
        {q: '', a: ''},
        {q: '', a: ''},
        {q: '', a: ''},
      ]
    };
  },
  mounted() {
    axios.get(api.Test).then(response => {
      const list = response.data.data;
      list.forEach((element,index) => {
        this.data[index].q = element.question
        this.data[index].a = element.ansA + ' ' + element.ansB + ' ' + element.ansC + ' ' + element.ansD 
      })
      // this.$forceUpdate()
    });
  },
  methods: {
    submit() {
      const questions = [];
      const choices = [];
      this.data.forEach(element => {
        questions.push(element.q);
        choices.push(element.a);
      });
      axios
        .post(api.Test, {
          questions: questions,
          choices: choices
        })
        .then(response => {
          if (response.data.code == 0) {
            this.$message.error("题目解析失败。请检查是否输入正确");
          } else if (response.data.code == 1) {
            this.$message.success("测试题目更新成功");
          }
        });
    }
  }
};
</script>

<style scoped>

.test-entry-input {
  margin-bottom: 8px;
}
</style>
