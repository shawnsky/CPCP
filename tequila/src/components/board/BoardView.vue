<template>
  <div id="b">
    <div class="title"><a-icon type="notification" /> 系统公告</div>
    <div class="main">
      {{ content }}
    </div>
    <div class="appendix">更新时间 {{ readableDate }}</div>
  </div>
</template>

<script>
import api from '@/api/index'
import axios from 'axios'
export default {
  data() {
    return {
      content: "",
      updateDate: ""
    };
  },
  computed: {
    readableDate() {
      return new Date(parseInt(this.updateDate))
        .toLocaleString()
        .replace(/:\d{1,2}$/, " ");
    }
  },
  mounted() {
    axios
      .get(api.Board)
      .then(response => {
        this.content = response.data.data.content
        this.updateDate = response.data.data.createTime
      })
      .catch(error => {
        console.log(error)
      })
  }
};
</script>
