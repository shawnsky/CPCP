<template>
  <div>
    <div class="title">公告管理</div>
    <h4>编辑系统公告</h4>
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
      .get(api.Board, {
        headers: { Authorization: localStorage.token }
      })
      .then(response => {
        this.content = response.data.data.content;
      })
      .catch(error => {
        if (error.response.status == 401) {
          this.$router.push({ path: "/login" });
        }
        console.log(error);
      });
  },
  methods: {
    commitNewContent() {
      this.loading = true;
      axios
        .put(
          api.Board,
          {
            content: this.content
          },
          {
            headers: { Authorization: localStorage.token }
          }
        )
        .then(response => {
          if (response.data.code == 1) {
            this.loading = false;
          }
        })
        .catch(error => {
          this.$message.error('更新失败')
          console.log(error)
        })
    }
  }
};
</script>
