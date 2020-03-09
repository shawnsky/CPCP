<template>
  <div id="b">
    <div class="title">系统公告</div>
    <div class="main">
      {{ content }}
    </div>
    <div class="appendix">更新时间 {{ readableDate }}</div>
  </div>
</template>

<script>
import api from "@/api/index";
import axios from "axios";
export default {
  data() {
    return {
      content: "",
      updateDate: ""
    };
  },
  computed: {
    readableDate() {
      if (this.updateDate == "") {
        return "无";
      }
      return new Date(parseInt(this.updateDate))
        .toLocaleString()
        .replace(/:\d{1,2}$/, " ");
    }
  },
  mounted() {
    axios
      .get(api.Board, {
        headers: { Authorization: localStorage.token }
      })
      .then(response => {
        this.content = response.data.data.content;
        this.updateDate = response.data.data.createTime;
      })
      .catch(error => {
        if (error.response.status == 401) {
          this.$router.push({ path: "/login" });
        }
        console.log(error);
      });
  }
};
</script>
