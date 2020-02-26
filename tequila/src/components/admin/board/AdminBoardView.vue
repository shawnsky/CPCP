<template>
  <div>
    <h3>在此编辑系统公告</h3>
    <a-textarea placeholder="系统公告..." :rows="6" v-model="content" />
    <a-button
      type="primary"
      style="margin: 32px 0 0 0; float: right"
      @click="commitNewContent"
      :loading="loading"
      >提交</a-button
    >
  </div>
</template>

<script>
import api from "@/api/index";
import axios from "axios";
export default {
  data() {
    return {
      content: "",
      loading: false
    };
  },
  mounted() {
    axios
      .get(api.Board)
      .then(response => {
        this.content = response.data.data.content;
      })
      .catch(error => {
        console.log(error);
      });
  },
  methods: {
    commitNewContent() {
      this.loading = true;
      axios
        .put(api.Board, {
          content: this.content
        })
        .then(response => {
          if (response.data.code == 1) {
            this.loading = false;
          }
        });
    }
  }
};
</script>
