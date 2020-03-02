<template>
  <div>
    <div class="title">发布文章</div>
    <a-spin :spinning="spinning" :delay="delayTime" size="large">
      <label for="9g3er">文章标题</label>
      <a-input id="9g3er" placeholder="输入文章标题" v-model="title" style="width: 400px" />
      <label for="9g3er">文章封面图片</label>
      <a-input
        id="9g3er"
        placeholder="图片网络地址 http://... 或者 https://..."
        v-model="cover"
        style="width: 400px"
      />
      <div id="editor"></div>
    </a-spin>
    <a-button type="primary" @click="submit" style="margin-top: 16px"
      >发布</a-button
    >
  </div>
</template>

<script>
import api from "@/api/index";
import axios from "axios";
import E from "wangeditor";

export default {
  name: "editor",
  data() {
    return {
      spinning: false,
      delayTime: 300,
      title: "",
      cover: "",
      editorContent: ""
    };
  },
  mounted() {
    var editor = new E("#editor");
    editor.customConfig.onchange = html => {
      this.editorContent = html;
    };
    editor.create();
  },
  methods: {
    submit() {
      this.spinning = true
      axios.post(api.Post, {
        title: this.title,
        content: this.editorContent,
        cover: this.cover
      })
      .then(response => {
        if (response.data.code == 1) {
          this.$router.push({path: '/admin/post'})
        }
      })
    }
  }
};
</script>

<style scoped>
label {
  display: block;
  margin: 8px 0;
}

#editor {
  margin-top: 16px;
}
</style>
